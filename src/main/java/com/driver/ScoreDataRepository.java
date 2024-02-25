package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreDataRepository {
    private Map<String, List<Score>> playerScores;

    public ScoreDataRepository() {
        this.playerScores = new HashMap<>();
    }

    public void storeScore(Score score) {
        playerScores.computeIfAbsent(score.getPlayerName(), k -> new ArrayList<>()).add(score);
    }

    public List<Score> getScoresByPlayer(String playerName) {
        return playerScores.getOrDefault(playerName, new ArrayList<>());
    }

    public List<Score> getAllScores() {
        List<Score> allScores = new ArrayList<>();
        playerScores.values().forEach(allScores::addAll);
        return allScores;
    }
}
