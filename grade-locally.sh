#!/bin/bash

set -e

echo "🔍 Running local grading simulation..."
echo "======================================"

# Run all Maven tests (will build all submodules)
mvn -q clean test > mvn-test-output.log

modules=(adventurer-service quest-service matching-service api-gateway)
TOTAL=0
FAILURES=0
SKIPPED=0
missing=()

for module in "${modules[@]}"; do
  report_dir="$module/target/surefire-reports"
  if ! ls "$report_dir"/TEST-*.xml >/dev/null 2>&1; then
    echo "❌ $module: no tests found"
    missing+=("$module")
    continue
  fi

  tests=$(grep -h "<testsuite" "$report_dir"/TEST-*.xml | sed -n 's/.*tests="\([0-9]\+\)".*/\1/p' | awk '{s+=$1} END {print s}')
  failures=$(grep -h "<testsuite" "$report_dir"/TEST-*.xml | sed -n 's/.*failures="\([0-9]\+\)".*/\1/p' | awk '{s+=$1} END {print s}')
  errors=$(grep -h "<testsuite" "$report_dir"/TEST-*.xml | sed -n 's/.*errors="\([0-9]\+\)".*/\1/p' | awk '{s+=$1} END {print s}')
  skipped=$(grep -h "<testsuite" "$report_dir"/TEST-*.xml | sed -n 's/.*skipped="\([0-9]\+\)".*/\1/p' | awk '{s+=$1} END {print s}')
  passed=$((tests - failures - errors - skipped))

  echo "$module: $passed / $tests tests passed"

  if [ "$tests" -eq 0 ]; then
    missing+=("$module")
  fi

  TOTAL=$((TOTAL + tests))
  FAILURES=$((FAILURES + failures + errors))
  SKIPPED=$((SKIPPED + skipped))
done

if [ ${#missing[@]} -gt 0 ]; then
  echo "--------------------------------------"
  echo "❌ Missing tests for modules: ${missing[*]}"
  exit 1
fi

PASSED=$((TOTAL - FAILURES - SKIPPED))

echo "--------------------------------------"
echo "✅ Passed: $PASSED / $TOTAL"
echo "❌ Failed: $FAILURES"
echo "⚠️ Skipped: $SKIPPED"
echo "--------------------------------------"

if [ "$TOTAL" -eq 0 ]; then
  echo "❌ No tests were run"
  exit 1
fi

# Estimate score (rough conversion)
PERCENT=$((PASSED * 100 / TOTAL))

# Rubric Mapping
GRADE=0

if (( PERCENT >= 95 )); then
  GRADE=100
elif (( PERCENT >= 85 )); then
  GRADE=90
elif (( PERCENT >= 75 )); then
  GRADE=80
elif (( PERCENT >= 65 )); then
  GRADE=70
elif (( PERCENT >= 50 )); then
  GRADE=60
else
  GRADE=50
fi

echo "🏆 Estimated Grade: $GRADE / 100"
echo "📍 Note: This is an estimate based on test success only."
echo "📋 Final score also depends on code quality and rubric categories like TDD, Gateway config, and SOLID design."
