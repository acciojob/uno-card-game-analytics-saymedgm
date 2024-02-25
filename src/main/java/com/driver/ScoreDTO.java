package com.driver;

public class ScoreDTO {
    private String playerName;
    private int score;

    public ScoreDTO(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
