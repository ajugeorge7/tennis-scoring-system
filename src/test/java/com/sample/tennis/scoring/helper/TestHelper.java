package com.sample.tennis.scoring.helper;

import com.sample.tennis.scoring.model.Player;

import java.util.stream.IntStream;

public class TestHelper {

    private TestHelper() {
    }

    public static void incrementPlayerPoints(Player player, int count) {
        IntStream.range(0, count).forEach(c -> player.incrementPointsWon());
    }

    public static void incrementPlayerGames(Player player, int count) {
        IntStream.range(0, count).forEach(c -> player.incrementGamesWon());
    }
}
