#!/bin/bash

echo "🔍 Running local grading simulation..."
echo "======================================"

# Run all Maven tests (will build all submodules)
mvn clean test > mvn-test-output.log

# Count total and passed tests using Maven's surefire reports
TOTAL=$(grep -r "Tests run:" . | awk -F'[:,]' '{sum+=$3} END {print sum}')
FAILURES=$(grep -r "Tests run:" . | awk -F'[:,]' '{sum+=$5} END {print sum}')
SKIPPED=$(grep -r "Tests run:" . | awk -F'[:,]' '{sum+=$7} END {print sum}')
PASSED=$((TOTAL - FAILURES - SKIPPED))

echo "✅ Passed: $PASSED / $TOTAL"
echo "❌ Failed: $FAILURES"
echo "⚠️ Skipped: $SKIPPED"
echo "--------------------------------------"

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
