package edu.ensign.cs460.questhub.matching;

public class Quest {
    private Long id;
    private String title;
    private int difficulty;
    private int reward;
    private String description;
    private String allowedClass;

    public Quest() {}

    public Quest(Long id, String title, int difficulty, int reward, String description, String allowedClass) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.reward = reward;
        this.description = description;
        this.allowedClass = allowedClass;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
    public int getReward() { return reward; }
    public void setReward(int reward) { this.reward = reward; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAllowedClass() { return allowedClass; }
    public void setAllowedClass(String allowedClass) { this.allowedClass = allowedClass; }
}
