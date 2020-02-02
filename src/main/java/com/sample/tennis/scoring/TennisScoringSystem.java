package com.sample.tennis.scoring;

public class TennisScoringSystem {

    private static final String PLAYER_ONE = "Roger Federer";
    private static final String PLAYER_TWO = "Novak Djokovic";

    private static Match singlesMatch = new Match(PLAYER_ONE, PLAYER_TWO);

    public static void main(String[] args) {

        playGame(PLAYER_ONE, PLAYER_TWO);
        playGame(PLAYER_TWO, PLAYER_ONE);
        playGameWithExtra(PLAYER_ONE, PLAYER_TWO);
        playGameWithExtra(PLAYER_TWO, PLAYER_ONE);
        playGame(PLAYER_ONE, PLAYER_TWO);
        playGame(PLAYER_TWO, PLAYER_ONE);
        playGame(PLAYER_ONE, PLAYER_TWO);
        playGame(PLAYER_TWO, PLAYER_ONE);
        playGameWithExtra(PLAYER_ONE, PLAYER_TWO);
        playGame(PLAYER_TWO, PLAYER_ONE);
        playGameWithExtra(PLAYER_ONE, PLAYER_TWO);
        playGame(PLAYER_TWO, PLAYER_ONE);
        playGameWithExtra(PLAYER_ONE, PLAYER_TWO);
    }

    private static void playGame(String playerOne, String playerTwo) {
        singlesMatch.pointsWonBy(playerOne);
        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerTwo);
        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());
    }

    private static void playGameWithExtra(String playerOne, String playerTwo) {
        singlesMatch.pointsWonBy(playerOne);
        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerTwo);
        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerTwo);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());

        singlesMatch.pointsWonBy(playerOne);
        System.out.println(singlesMatch.score());
    }
}
