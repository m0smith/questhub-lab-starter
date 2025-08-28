package edu.ensign.cs460.questhub.adventurer;

public class Adventurer {
    private Long id;
    private String name;
    private int level;
    private String characterClass;

    public Adventurer() {
    }

    public Adventurer(Long id, String name, int level, String characterClass) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.characterClass = characterClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
}
