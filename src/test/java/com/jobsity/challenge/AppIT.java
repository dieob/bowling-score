package com.jobsity.challenge;

import com.jobsity.challenge.interfaces.BowlingGame;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.utils.FileParser;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

public class AppIT {

    @Test
    public void noScoreIntegrationTest() throws FileNotFoundException {

        FileParser parser = new FileParser();
        LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile("no-score.txt");

        gameFrames.forEach((player, frames)->{
            BowlingGame game =  new BowlingGameScoreBoard(frames);
            for (Frame frame : frames) {
                frame.setScore(game.calculateScore(frame));

                assertEquals(0, frame.getScore());
            }
        });
    }
}
