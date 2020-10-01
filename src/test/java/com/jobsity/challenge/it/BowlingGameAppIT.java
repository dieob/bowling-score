package com.jobsity.challenge.it;

import com.jobsity.challenge.interfaces.BowlingGameInterface;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.models.Parser;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

public class BowlingGameAppIT {

    @Test
    public void noScoreIntegrationTest() throws FileNotFoundException {

        Parser parser = new Parser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile("no-score.txt");

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
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile("all-fault.txt");

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
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile("perfect-score.txt");

        gameFrames.forEach((player, frames)->{
            BowlingGameInterface game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));
            }
            assertEquals(300, frames.get(frames.size()-1).getScore());
        });
    }
}
