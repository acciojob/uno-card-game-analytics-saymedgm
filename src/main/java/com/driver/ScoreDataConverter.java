package com.driver;

public class ScoreDataConverter {
    public Score convertToEntity(ScoreDTO scoreDTO) {
        return new Score(scoreDTO.getPlayerName(), scoreDTO.getScore());
    }

    public ScoreDTO convertToDTO(Score score) {
        return new ScoreDTO(score.getPlayerName(), score.getScore());
    }
}
