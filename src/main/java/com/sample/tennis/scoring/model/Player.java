package com.sample.tennis.scoring.model;

public class Player {

    private String name;
    private int pointsWon = 0;
    private int gamesWon = 0;
    private int setsWon = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPointsWon() {
        return pointsWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void incrementPointsWon() {
        this.pointsWon++;
    }

    public void resetPointsWon() {
        this.pointsWon = 0;
    }

    public void incrementGamesWon() {
        this.gamesWon++;
    }

    public void incrementSetsWon() {
        this.setsWon++;
    }
}
