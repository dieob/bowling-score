package com.jobsity.challenge;

import com.jobsity.challenge.interfaces.BowlingGame;
import com.jobsity.challenge.models.BowlingGameScoreBoard;
import com.jobsity.challenge.models.Frame;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import com.jobsity.challenge.utils.Utilities;

/**
 * Main Class for the Bowling Score calculation
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        Utilities util = new Utilities();
        LinkedHashMap<String, List<Frame>> gamePlays = util.parseTextFile(args[0]);
        int frameCounter=0;

        while(true){
            gamePlays.forEach((player, frames)->{
                BowlingGame game =  new BowlingGameScoreBoard(frames);
                for (Frame frame : frames) {
                    frame.setScore(game.calculateScore(frame));
                }
            });
            break;
        }

        for(Object key : gamePlays.keySet()){
            System.out.print(key);
            for(int k=0; k<gamePlays.get(key).size(); k++) {
                System.out.print(" ");
             /*   System.out.print(gamePlays.get(key).get(k).getFrameNumber());
                System.out.print(" ");
                System.out.print(gamePlays.get(key).get(k).getPinfalls()[0]);
                System.out.print(" ");*/
                System.out.print(gamePlays.get(key).get(k).getScore());
                System.out.print("//");
            }
            System.out.println(" ");
        }
    }
}
