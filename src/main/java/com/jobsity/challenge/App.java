package com.jobsity.challenge;

import com.jobsity.challenge.interfaces.BowlingGame;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.utils.BowlingException;
import com.jobsity.challenge.utils.FileParser;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Main Class for the Bowling Score Game Calculation
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try{
            FileParser parser = new FileParser();
            //Parser will get the frames for each player
            LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(args[0]);

            //Calculate the score of each plyer in the game
                gameFrames.forEach((player, frames)->{
                    BowlingGame game =  new BowlingGameScoreBoard(frames);
                    for (Frame frame : frames) {
                        frame.setScore(game.calculateScore(frame));
                    }
                });

            //Print the results board
            BowlingGame game =  new BowlingGameScoreBoard();
            game.printResultBoard(gameFrames);

        } catch (FileNotFoundException fe){
            new BowlingException("The file "+args[0]+ " could not be found.");
        }
    }
}
