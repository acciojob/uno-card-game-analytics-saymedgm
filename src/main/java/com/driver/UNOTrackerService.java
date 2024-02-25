package com.driver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UNOTrackerService {
    private ScoreDataRepository scoreDataRepository;

    public UNOTrackerService(ScoreDataRepository scoreDataRepository) {
        this.scoreDataRepository = scoreDataRepository;
    }

    public void storeScoreData(ScoreDTO scoreDTO) {
        Score score = new ScoreDataConverter().convertToEntity(scoreDTO);
        scoreDataRepository.storeScore(score);
    }

    public double calculateAverageScore(String playerName) {
        List<Score> scores = scoreDataRepository.getScoresByPlayer(playerName);
        if (scores.isEmpty()) {
            return 0.0;
        }
        int totalScore = scores.stream().mapToInt(Score::getScore).sum();
        return (double) totalScore / scores.size();
    }

    public String identifyTopPlayer() {
        List<Score> allScores = scoreDataRepository.getAllScores();
        if (allScores.isEmpty()) {
            return "No scores available.";
        }
        Map<String, Double> averageScoresByPlayer = allScores.stream()
                .collect(Collectors.groupingBy(Score::getPlayerName,
                        Collectors.averagingDouble(Score::getScore)));

        return averageScoresByPlayer.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No top player identified.");
    }
}