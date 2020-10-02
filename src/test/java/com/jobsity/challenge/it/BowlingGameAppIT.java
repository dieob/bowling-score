package com.jobsity.challenge.it;

import com.jobsity.challenge.interfaces.BowlingGameInterface;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.models.Parser;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class BowlingGameAppIT {
    String testFilepath;
    String noScoreFile;
    String allFoulFile;
    String  perfectScoreFile;
    String youtubeExampleFile;

    @Before
    public void setUp() {
        testFilepath = System.getProperty("user.dir") + "/src/test/java/com/jobsity/challenge/it/resources/";
        noScoreFile = "no-score.txt";
        allFoulFile = "all-foul.txt";
        perfectScoreFile = "perfect-score.txt";
        youtubeExampleFile = "youtube-example.txt";
    }

    @Test
    public void noScoreIntegrationTest() {
        Parser parser = new Parser();

        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(testFilepath+noScoreFile);

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));

                assertEquals(0, frame.getScore());
            }
        });
    }

    @Test
    public void allFaultIntegrationTest() {

        Parser parser = new Parser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(testFilepath+allFoulFile);

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));

                assertEquals(0, frame.getScore());
            }
        });
    }

    @Test
    public void perfectScoreIntegrationTest() {

        Parser parser = new Parser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(testFilepath+perfectScoreFile);

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));
            }
            assertEquals(300, frames.get(frames.size()-1).getScore());
        });
    }

    @Test
    public void regularGameIntegrationTest() {

        Parser parser = new Parser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(testFilepath+youtubeExampleFile);

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));
            }
            assertEquals(170, frames.get(frames.size()-1).getScore());
        });
    }
}
