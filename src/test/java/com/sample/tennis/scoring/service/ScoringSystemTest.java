package com.sample.tennis.scoring.service;

import com.sample.tennis.scoring.model.Player;
import com.sample.tennis.scoring.helper.TestHelper;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ScoringSystemTest {

    private static final String PLAYER_1 = "player 1";
    private static final String PLAYER_2 = "player 2";

    private ScoringSystem scoringSystem = new ScoringSystem();

    @Test
    public void shouldNotIncrementTheGameWhenPlayerDoesNotWinTheGame() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        player1.incrementPointsWon();
        player1.incrementPointsWon();
        player2.incrementPointsWon();

        scoringSystem.scoreGame(player1, player2);

        assertThat(player1.getGamesWon(), is(0));
        assertThat(player2.getGamesWon(), is(0));
        assertThat(player1.getPointsWon(), is(2));
        assertThat(player2.getPointsWon(), is(1));
    }

    @Test
    public void shouldIncrementTheGameForPlayerOneWhenPlayerOneWinTheGame() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 4);
        TestHelper.incrementPlayerPoints(player2, 2);

        scoringSystem.scoreGame(player1, player2);

        assertThat(player1.getGamesWon(), is(1));
        assertThat(player2.getGamesWon(), is(0));
        assertThat(player1.getPointsWon(), is(0));
        assertThat(player2.getPointsWon(), is(0));
    }

    @Test
    public void shouldIncrementTheGameForPlayerOneWhenPlayerOneWinTheGameAfterAdvantage() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 6);
        TestHelper.incrementPlayerPoints(player2, 4);

        scoringSystem.scoreGame(player1, player2);

        assertThat(player1.getGamesWon(), is(1));
        assertThat(player2.getGamesWon(), is(0));
        assertThat(player1.getPointsWon(), is(0));
        assertThat(player2.getPointsWon(), is(0));
    }

    @Test
    public void shouldIncrementTheGameForPlayerTwoWhenPlayerTwoWinTheGame() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 2);
        TestHelper.incrementPlayerPoints(player2, 4);

        scoringSystem.scoreGame(player1, player2);

        assertThat(player1.getGamesWon(), is(0));
        assertThat(player2.getGamesWon(), is(1));
        assertThat(player1.getPointsWon(), is(0));
        assertThat(player2.getPointsWon(), is(0));
    }

    @Test
    public void shouldIncrementTheGameForPlayerTwoWhenPlayerTwoWinTheGameAfterTieBreaker() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerGames(player1, 6);
        TestHelper.incrementPlayerGames(player2, 6);
        TestHelper.incrementPlayerPoints(player1, 5);
        TestHelper.incrementPlayerPoints(player2, 7);

        scoringSystem.scoreGame(player1, player2);

        assertThat(player1.getGamesWon(), is(6));
        assertThat(player2.getGamesWon(), is(7));
        assertThat(player1.getPointsWon(), is(0));
        assertThat(player2.getPointsWon(), is(0));
    }

    @Test
    public void shouldNotIncrementTheSetForPlayerOneWhenPlayerDoesNotOneWinTheSet() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        TestHelper.incrementPlayerGames(player1, 4);
        TestHelper.incrementPlayerGames(player2, 2);

        scoringSystem.scoreSet(player1, player2);

        assertThat(player1.getGamesWon(), is(4));
        assertThat(player2.getGamesWon(), is(2));
        assertThat(player1.getSetsWon(), is(0));
        assertThat(player2.getSetsWon(), is(0));
    }

    @Test
    public void shouldIncrementTheSetForPlayerOneWhenPlayerOneWinTheSet() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        TestHelper.incrementPlayerGames(player1, 6);
        TestHelper.incrementPlayerGames(player2, 4);

        scoringSystem.scoreSet(player1, player2);

        assertThat(player1.getSetsWon(), is(1));
        assertThat(player2.getSetsWon(), is(0));
        assertThat(player1.getGamesWon(), is(6));
        assertThat(player2.getGamesWon(), is(4));
    }

    @Test
    public void shouldIncrementTheSetForPlayerTwoWhenPlayerTwoWinTheSet() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        TestHelper.incrementPlayerGames(player1, 5);
        TestHelper.incrementPlayerGames(player2, 7);

        scoringSystem.scoreSet(player1, player2);

        assertThat(player1.getSetsWon(), is(0));
        assertThat(player2.getSetsWon(), is(1));
        assertThat(player1.getGamesWon(), is(5));
        assertThat(player2.getGamesWon(), is(7));
    }

    @Test
    public void shouldIncrementTheSetForPlayerTwoWhenPlayerTwoWinTheSetInTieBreaker() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        TestHelper.incrementPlayerGames(player1, 6);
        TestHelper.incrementPlayerGames(player2, 7);

        scoringSystem.scoreSet(player1, player2);

        assertThat(player1.getSetsWon(), is(0));
        assertThat(player2.getSetsWon(), is(1));
        assertThat(player1.getGamesWon(), is(6));
        assertThat(player2.getGamesWon(), is(7));
    }

    @Test
    public void shouldReturnCorrectScoreBeforeStartingOfMatch() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0"));
    }

    @Test
    public void shouldReturnCorrectScoreForZeroFifteenScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player2, 1);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, 0-15"));
    }

    @Test
    public void shouldReturnCorrectScoreForThirtyFifteenScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 2);
        TestHelper.incrementPlayerPoints(player2, 1);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, 30-15"));
    }

    @Test
    public void shouldReturnCorrectScoreForThirtyFortyScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 2);
        TestHelper.incrementPlayerPoints(player2, 3);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, 30-40"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForInCorrectScore() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 2);
        TestHelper.incrementPlayerPoints(player2, 6);

        scoringSystem.getScore(player1, player2);
    }

    @Test
    public void shouldReturnCorrectScoreForDeuceScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 3);
        TestHelper.incrementPlayerPoints(player2, 3);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, Deuce"));
    }

    @Test
    public void shouldReturnCorrectScoreForAdvantagePlayerOneScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 4);
        TestHelper.incrementPlayerPoints(player2, 3);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, Advantage player 1"));
    }

    @Test
    public void shouldReturnCorrectScoreForAdvantagePlayerTwoScores() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerPoints(player1, 5);
        TestHelper.incrementPlayerPoints(player2, 6);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("0-0, Advantage player 2"));
    }

    @Test
    public void shouldReturnCorrectScoreForGamePoints() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerGames(player1, 4);
        TestHelper.incrementPlayerGames(player2, 3);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("4-3"));
    }

    @Test
    public void shouldReturnCorrectScoreForTieBreakerPoints() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerGames(player1, 6);
        TestHelper.incrementPlayerGames(player2, 6);
        TestHelper.incrementPlayerPoints(player1, 5);
        TestHelper.incrementPlayerPoints(player2, 3);

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("6-6, 5-3"));
    }

    @Test
    public void shouldReturnTrueIfPlayerOneWinsTheMatch() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        player1.incrementSetsWon();

        boolean hasMatchOver = scoringSystem.hasMatchOver(player1, player2);

        assertTrue(hasMatchOver);
    }

    @Test
    public void shouldReturnFalseIfPlayStillGoingOn() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);

        boolean hasMatchOver = scoringSystem.hasMatchOver(player1, player2);

        assertFalse(hasMatchOver);
    }

    @Test
    public void shouldReturnPlayerOneIfPlayerOneIsTheWinner() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerGames(player1, 6);
        TestHelper.incrementPlayerGames(player2, 4);
        player1.incrementSetsWon();

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("6-4, player 1 Won"));
    }

    @Test
    public void shouldReturnPlayerTwoIfPlayerTwoIsTheWinner() {
        final Player player1 = new Player(PLAYER_1);
        final Player player2 = new Player(PLAYER_2);
        TestHelper.incrementPlayerGames(player1, 5);
        TestHelper.incrementPlayerGames(player2, 7);
        player2.incrementSetsWon();

        final String score = scoringSystem.getScore(player1, player2);

        assertThat(score, is("5-7, player 2 Won"));
    }
}