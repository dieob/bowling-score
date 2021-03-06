package com.jobsity.challenge;

import com.jobsity.challenge.interfaces.BowlingGameInterface;
import com.jobsity.challenge.interfaces.FileParserInterface;
import com.jobsity.challenge.interfaces.ResultPrinterInterface;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.BowlingResultPrinter;
import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.exceptions.BowlingException;
import com.jobsity.challenge.models.Parser;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Main Class for the Bowling Score Game Calculation
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try {
            FileParserInterface parser = new Parser();
            //Parser will get the frames for each player
            LinkedHashMap<String, List<Frame>> gameFrames = parser.handleFile(args[0]);


            //Calculate the score of each player in the game
            gameFrames.forEach((player, frames) -> {

                BowlingGameInterface game = new BowlingGameScoreBoard(frames);
                for (Frame frame : frames) {
                    frame.setScore(game.calculateScore(frame));
                }
            });

            //Print the results board
            BowlingGameInterface game = new BowlingGameScoreBoard();
            ResultPrinterInterface printer = new BowlingResultPrinter();
            printer.printResultBoard(gameFrames);
        } catch (BowlingException be){
            System.out.println(be.getMessage());
        }
    }
}
