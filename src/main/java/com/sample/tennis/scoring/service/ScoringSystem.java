package com.sample.tennis.scoring.service;

import com.sample.tennis.scoring.model.Player;

public class ScoringSystem {

    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage ";
    private static final int SETS_TO_WIN = 1;

    public void scoreGame(Player playerOne, Player playerTwo) {
        if (hasWonGamePoint(playerOne, playerTwo)) {
            if (playerOne.getPointsWon() > playerTwo.getPointsWon()) {
                playerOne.incrementGamesWon();
            } else {
                playerTwo.incrementGamesWon();
            }
            playerOne.resetPointsWon();
            playerTwo.resetPointsWon();
        }
    }

    public void scoreSet(Player playerOne, Player playerTwo) {
        if (hasWonSetPoint(playerOne, playerTwo)) {
            if (playerOne.getGamesWon() > playerTwo.getGamesWon()) {
                playerOne.incrementSetsWon();
            } else {
                playerTwo.incrementSetsWon();
            }
        }
    }

    public String getScore(Player playerOne, Player playerTwo) {
        StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(playerOne.getGamesWon()).append("-").append(playerTwo.getGamesWon());

        if (playerOne.getPointsWon() > 0 || playerTwo.getPointsWon() > 0) {
            scoreBuilder.append(", ")
                    .append(displayPointsWon(playerOne, playerTwo));
        }

        if (hasMatchOver(playerOne, playerTwo)) {
            scoreBuilder.append(", ")
                    .append(getMatchWinnerPlayerName(playerOne, playerTwo)).append(" Won");
        }

        return scoreBuilder.toString();
    }

    public boolean hasMatchOver(Player playerOne, Player playerTwo) {
        return playerOne.getSetsWon() == SETS_TO_WIN || playerTwo.getSetsWon() == SETS_TO_WIN;
    }

    private boolean hasWonGamePoint(Player playerOne, Player playerTwo) {
        int pointLimit = 4;
        if (isTie(playerOne, playerTwo)) {
            pointLimit = 6;
        }
        int playerOnePoints = playerOne.getPointsWon();
        int playerTwoPoints = playerTwo.getPointsWon();

        if (playerOnePoints >= pointLimit && playerOnePoints >= playerTwoPoints + 2) {
            return true;
        }
        return playerTwoPoints >= pointLimit && playerTwoPoints >= playerOnePoints + 2;
    }

    private boolean hasWonSetPoint(Player playerOne, Player playerTwo) {
        int gameLimit = 6;
        int gameDifference = 2;
        if (playerOne.getGamesWon() == 7 || playerTwo.getGamesWon() == 7) {
            gameLimit = 7;
            gameDifference = 1;
        }
        int playerOneGames = playerOne.getGamesWon();
        int playerTwoGames = playerTwo.getGamesWon();

        if (playerOneGames >= gameLimit && playerOneGames >= playerTwoGames + gameDifference) {
            return true;
        }
        return playerTwoGames >= gameLimit && playerTwoGames >= playerOneGames + gameDifference;
    }

    private String displayPointsWon(Player playerOne, Player playerTwo) {
        if (isTie(playerOne, playerTwo)) {
            return playerOne.getPointsWon() + "-" + playerTwo.getPointsWon();
        }

        if (hasAdvantage(playerOne, playerTwo)) {
            return ADVANTAGE + getHighPointsPlayerName(playerOne, playerTwo);
        }
        if (isDeuce(playerOne, playerTwo)) {
            return DEUCE;
        }

        return convertPoint(playerOne.getPointsWon()) + "-" + convertPoint(playerTwo.getPointsWon());
    }

    private boolean isTie(Player playerOne, Player playerTwo) {
        int playerOneGames = playerOne.getGamesWon();
        int playerTwoGames = playerTwo.getGamesWon();

        return playerOneGames == 6 && playerOneGames == playerTwoGames;
    }

    private boolean isDeuce(Player playerOne, Player playerTwo) {
        int playerOnePoints = playerOne.getPointsWon();
        int playerTwoPoints = playerTwo.getPointsWon();
        return playerOnePoints >= 3 && playerOnePoints == playerTwoPoints;
    }

    private boolean hasAdvantage(Player playerOne, Player playerTwo) {
        int playerOnePoints = playerOne.getPointsWon();
        int playerTwoPoints = playerTwo.getPointsWon();
        if (playerOnePoints >= 4 && playerOnePoints == playerTwoPoints + 1) {
            return true;
        }
        return playerTwoPoints >= 4 && playerTwoPoints == playerOnePoints + 1;

    }

    private String getHighPointsPlayerName(Player playerOne, Player playerTwo) {
        return playerOne.getPointsWon() > playerTwo.getPointsWon() ? playerOne.getName() : playerTwo.getName();
    }

    private String getMatchWinnerPlayerName(Player playerOne, Player playerTwo) {
        return playerOne.getSetsWon() > playerTwo.getSetsWon() ? playerOne.getName() : playerTwo.getName();
    }

    private String convertPoint(int point) {
        switch (point) {
            case 0 : return "0";
            case 1 : return "15";
            case 2 : return "30";
            case 3 : return "40";
        }

        throw new IllegalArgumentException("Illegal point: " + point);
    }
}
