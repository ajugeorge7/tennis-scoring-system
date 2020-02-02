package com.sample.tennis.scoring;

import com.sample.tennis.scoring.model.Player;
import com.sample.tennis.scoring.service.ScoringSystem;

public class Match {

    private Player playerOne;
    private Player playerTwo;

    private ScoringSystem scoringSystem = new ScoringSystem();

    public Match(String playerOneName, String playerTwoName) {
        this.playerOne = new Player(playerOneName);
        this.playerTwo = new Player(playerTwoName);
    }

    public void pointsWonBy(String playerName) {
        if (scoringSystem.hasMatchOver(playerOne, playerTwo)) {
            return;
        }

        final Player playerWon = getPlayerByName(playerName);
        playerWon.incrementPointsWon();

        scoringSystem.scoreGame(playerOne, playerTwo);
        scoringSystem.scoreSet(playerOne, playerTwo);
    }

    public String score() {
        return scoringSystem.getScore(playerOne, playerTwo);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    private Player getPlayerByName(String playerName) {
        return (playerName.equalsIgnoreCase(playerOne.getName())) ? playerOne : playerTwo;
    }
}
