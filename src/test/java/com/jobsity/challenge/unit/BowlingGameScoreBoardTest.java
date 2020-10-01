package com.jobsity.challenge.unit;

import static org.junit.Assert.assertEquals;

import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for Bowling Score Game.
 */
public class BowlingGameScoreBoardTest {

    final int INITIAL_SCORE = 0;

    @Test
    public void noStrikeNoSpare(){

        List<Frame> frames = new ArrayList();
        String[] pinfalls = new String[3];
        pinfalls[0] = "2";
        pinfalls[1] = "6";

        frames.add(new Frame("TestPlayer", pinfalls, 0, INITIAL_SCORE));

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "5";
        pinfalls2[1] = "3";

        frames.add(new Frame("TestPlayer", pinfalls2, 1, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(0));

        assertEquals(8, score);
    }

    @Test
    public void spareOnFirstFrame(){

        List<Frame> frames = new ArrayList();
        String[] pinfalls = new String[3];
        pinfalls[0] = "4";
        pinfalls[1] = "6";

        frames.add(new Frame("TestPlayer", pinfalls, 0, INITIAL_SCORE));

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "5";
        pinfalls2[1] = "3";

        frames.add(new Frame("TestPlayer", pinfalls2, 1, INITIAL_SCORE));
        frames.add(new Frame("TestPlayer", pinfalls2, 2, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(0));

        assertEquals(15, score);
    }

    @Test
    public void spareOnMiddleFrame(){

        List<Frame> frames = new ArrayList();
        String[] pinfalls = new String[3];
        pinfalls[0] = "5";
        pinfalls[1] = "2";

        frames.add(new Frame("TestPlayer", pinfalls, 0,7));

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "8";
        pinfalls2[1] = "2";

        frames.add(new Frame("TestPlayer", pinfalls2, 1, INITIAL_SCORE));

        String[] pinfalls3 = new String[3];
        pinfalls3[0] = "2";
        pinfalls3[1] = "3";
        frames.add(new Frame("TestPlayer", pinfalls3, 2, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(1));

        assertEquals(19, score);
    }

    @Test
    public void spareOnLastFrame(){

        List<Frame> frames = Stream.generate(()-> new Frame("", new String[3], 0, INITIAL_SCORE))
                .limit(10)
                .collect(Collectors.toList());

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "8";
        pinfalls2[1] = "1";

        frames.set(8, new Frame("TestPlayer", pinfalls2, 8, 9));

        String[] pinfalls3 = new String[3];
        pinfalls3[0] = "7";
        pinfalls3[1] = "3";
        pinfalls3[2] = "3";
        frames.set(9, new Frame("TestPlayer", pinfalls3, 9, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(9));

        assertEquals(22, score);
    }

    @Test
    public void strikeOnFirstFrame(){

        List<Frame> frames = new ArrayList();
        String[] pinfalls = new String[3];
        pinfalls[0] = "10";
        pinfalls[1] = "0";

        frames.add(new Frame("TestPlayer", pinfalls, 0, INITIAL_SCORE));

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "5";
        pinfalls2[1] = "3";

        frames.add(new Frame("TestPlayer", pinfalls2, 1, INITIAL_SCORE));
        frames.add(new Frame("TestPlayer", pinfalls2, 2, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(0));

        assertEquals(18, score);
    }

    @Test
    public void strikeOnMiddleFrame(){

        List<Frame> frames = new ArrayList();
        String[] pinfalls = new String[3];
        pinfalls[0] = "5";
        pinfalls[1] = "2";

        frames.add(new Frame("TestPlayer", pinfalls, 0,7));

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "10";
        pinfalls2[1] = "0";

        frames.add(new Frame("TestPlayer", pinfalls2, 1, INITIAL_SCORE));

        String[] pinfalls3 = new String[3];
        pinfalls3[0] = "2";
        pinfalls3[1] = "3";
        frames.add(new Frame("TestPlayer", pinfalls3, 2, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(1));

        assertEquals(22, score);
    }

    @Test
    public void strikeOnLastFrame(){

        List<Frame> frames = Stream.generate(()-> new Frame("TestPlayer", new String[3], 0, INITIAL_SCORE))
                .limit(10)
                .collect(Collectors.toList());

        String[] pinfalls2 = new String[3];
        pinfalls2[0] = "8";
        pinfalls2[1] = "1";

        frames.set(8, new Frame("TestPlayer", pinfalls2, 8, 9));

        String[] pinfalls3 = new String[3];
        pinfalls3[0] = "10";
        pinfalls3[1] = "1";
        pinfalls3[2] = "3";
        frames.set(9, new Frame("TestPlayer", pinfalls3, 9, INITIAL_SCORE));

        BowlingGameScoreBoard game = new BowlingGameScoreBoard(frames);

        int score = game.calculateScore(frames.get(9));

        assertEquals(23, score);
    }
}
