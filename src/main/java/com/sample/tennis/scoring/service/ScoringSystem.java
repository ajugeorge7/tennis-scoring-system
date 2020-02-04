package com.sample.tennis.scoring.service;

import com.sample.tennis.scoring.model.Player;

public class ScoringSystem {

    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage ";
    private static final String HYPHEN = "-";
    private static final String COMMA = ", ";

    private static final int SETS_TO_WIN = 1;
    private static final int DEUCE_POINT = 3;
    private static final int GAME_POINT = 4;
    private static final int GAME_POINT_DURING_TIE = 6;
    private static final int GAME_POINT_DIFFERENCE = 2;
    private static final int ADVANTAGE_POINT_DIFFERENCE = 1;
    private static final int SET_POINT = 6;
    private static final int SET_POINT_DURING_TIE = 7;
    private static final int SET_POINT_DIFFERENCE = 2;
    private static final int SET_POINT_DIFFERENCE_DURING_TIE = 1;

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
        final StringBuilder scoreBuilder = new StringBuilder();
        scoreBuilder.append(playerOne.getGamesWon()).append(HYPHEN).append(playerTwo.getGamesWon());

        if (playerOne.getPointsWon() > 0 || playerTwo.getPointsWon() > 0) {
            scoreBuilder.append(COMMA).append(displayPointsWon(playerOne, playerTwo));
        }

        if (hasMatchOver(playerOne, playerTwo)) {
            scoreBuilder.append(COMMA).append(getMatchWinnerPlayerName(playerOne, playerTwo)).append(" Won");
        }

        return scoreBuilder.toString();
    }

    public boolean hasMatchOver(Player playerOne, Player playerTwo) {
        return playerOne.getSetsWon() == SETS_TO_WIN || playerTwo.getSetsWon() == SETS_TO_WIN;
    }

    private boolean hasWonGamePoint(Player playerOne, Player playerTwo) {
        int pointLimit = GAME_POINT;
        if (isTie(playerOne, playerTwo)) {
            pointLimit = GAME_POINT_DURING_TIE;
        }
        final int playerOnePoints = playerOne.getPointsWon();
        final int playerTwoPoints = playerTwo.getPointsWon();

        if (playerOnePoints >= pointLimit && playerOnePoints >= playerTwoPoints + GAME_POINT_DIFFERENCE) {
            return true;
        }
        return playerTwoPoints >= pointLimit && playerTwoPoints >= playerOnePoints + GAME_POINT_DIFFERENCE;
    }

    private boolean hasWonSetPoint(Player playerOne, Player playerTwo) {
        int gameLimit = SET_POINT;
        int gameDifference = SET_POINT_DIFFERENCE;
        if (playerOne.getGamesWon() == SET_POINT_DURING_TIE || playerTwo.getGamesWon() == SET_POINT_DURING_TIE) {
            gameLimit = SET_POINT_DURING_TIE;
            gameDifference = SET_POINT_DIFFERENCE_DURING_TIE;
        }
        final int playerOneGames = playerOne.getGamesWon();
        final int playerTwoGames = playerTwo.getGamesWon();

        if (playerOneGames >= gameLimit && playerOneGames >= playerTwoGames + gameDifference) {
            return true;
        }
        return playerTwoGames >= gameLimit && playerTwoGames >= playerOneGames + gameDifference;
    }

    private String displayPointsWon(Player playerOne, Player playerTwo) {
        if (isTie(playerOne, playerTwo)) {
            return playerOne.getPointsWon() + HYPHEN + playerTwo.getPointsWon();
        }
        if (hasAdvantage(playerOne, playerTwo)) {
            return ADVANTAGE + getHighPointsPlayerName(playerOne, playerTwo);
        }
        if (isDeuce(playerOne, playerTwo)) {
            return DEUCE;
        }

        return convertPoint(playerOne.getPointsWon()) + HYPHEN + convertPoint(playerTwo.getPointsWon());
    }

    private boolean isTie(Player playerOne, Player playerTwo) {
        final int playerOneGames = playerOne.getGamesWon();
        final int playerTwoGames = playerTwo.getGamesWon();
        return playerOneGames == GAME_POINT_DURING_TIE && playerOneGames == playerTwoGames;
    }

    private boolean isDeuce(Player playerOne, Player playerTwo) {
        final int playerOnePoints = playerOne.getPointsWon();
        final int playerTwoPoints = playerTwo.getPointsWon();
        return playerOnePoints >= DEUCE_POINT && playerOnePoints == playerTwoPoints;
    }

    private boolean hasAdvantage(Player playerOne, Player playerTwo) {
        final int playerOnePoints = playerOne.getPointsWon();
        final int playerTwoPoints = playerTwo.getPointsWon();
        if (playerOnePoints >= GAME_POINT && playerOnePoints == playerTwoPoints + ADVANTAGE_POINT_DIFFERENCE) {
            return true;
        }
        return playerTwoPoints >= GAME_POINT && playerTwoPoints == playerOnePoints + ADVANTAGE_POINT_DIFFERENCE;

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
