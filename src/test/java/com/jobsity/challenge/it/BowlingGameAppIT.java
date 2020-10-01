package com.jobsity.challenge.it;

import com.jobsity.challenge.interfaces.BowlingGameInterface;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.models.Parser;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

public class BowlingGameAppIT {
    String testFilepath;
    String noScoreFile;
    String allFaultFile;
    String  perfectScoreFile;

    @Before
    public void setUp() {
        testFilepath = System.getProperty("user.dir") + "/src/test/java/com/jobsity/challenge/it/resources/";
        noScoreFile = "no-score.txt";
        allFaultFile = "all-fault.txt";
        perfectScoreFile = "perfect-score.txt";
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
    public void allFaultIntegrationTest() throws FileNotFoundException {

        Parser parser = new Parser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(testFilepath+allFaultFile);

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));

                assertEquals(0, frame.getScore());
            }
        });
    }

    @Test
    public void perfectScoreIntegrationTest() throws FileNotFoundException {

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
}
