package com.sample.tennis.scoring;

import com.sample.tennis.scoring.model.Player;
import com.sample.tennis.scoring.helper.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatchTest {

    private static final String PLAYER_1 = "player 1";
    private static final String PLAYER_2 = "player 2";

    private Match match;

    @Before
    public void setUp() {
        match = new Match(PLAYER_1, PLAYER_2);
    }

    @Test
    public void shouldMarkPointsWonForPlayerOne() {
        match.pointsWonBy(PLAYER_1);
        final Player playerOne = match.getPlayerOne();

        assertThat(playerOne.getPointsWon(), is(1));
        assertThat(playerOne.getGamesWon(), is(0));
        assertThat(playerOne.getSetsWon(), is(0));
    }

    @Test
    public void shouldMarkPointsWonForPlayerTwo() {
        match.pointsWonBy(PLAYER_2);
        final Player playerTwo = match.getPlayerTwo();

        assertThat(playerTwo.getPointsWon(), is(1));
        assertThat(playerTwo.getGamesWon(), is(0));
        assertThat(playerTwo.getSetsWon(), is(0));
    }

    @Test
    public void shouldGetScoreForPlayerOneWhenPlayerOneScores() {
        match.pointsWonBy(PLAYER_1);

        final String score = match.score();

        assertThat(score, is("0-0, 15-0"));
    }

    @Test
    public void shouldGetScoreForWhenBothPlayersScores() {
        match.pointsWonBy(PLAYER_1);
        match.pointsWonBy(PLAYER_2);

        final String score = match.score();

        assertThat(score, is("0-0, 15-15"));
    }

    @Test
    public void shouldNotScorePointsIfMatchIsOver() {
        final Player playerOne = match.getPlayerOne();
        final Player playerTwo = match.getPlayerTwo();

        TestHelper.incrementPlayerGames(playerOne, 6);
        TestHelper.incrementPlayerGames(playerTwo, 4);
        playerOne.incrementSetsWon();

        final String scoreBefore = match.score();

        assertThat(scoreBefore, is("6-4, player 1 Won"));

        match.pointsWonBy(PLAYER_1);

        final String scoreAfter = match.score();

        assertThat(scoreAfter, is("6-4, player 1 Won"));
    }
}