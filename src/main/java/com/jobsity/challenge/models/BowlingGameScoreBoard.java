package com.jobsity.challenge.models;

/**
 * Implementation for a specific BowlingGame.
 *
 * @author Diego Báez
 *
 */

import com.jobsity.challenge.interfaces.BowlingGameInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class BowlingGameScoreBoard implements BowlingGameInterface {


    private List<Frame> frames = new ArrayList<>();
    private static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;

    public BowlingGameScoreBoard() {
    }

    public BowlingGameScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }


    /** Calculates the score of a given frame */
    @Override
    public int calculateScore(Frame frame) {
        int result = 0;
        int prevScore = 0;

        if(frame.getFrameNumber() != 0){
            prevScore = frames.get(frame.getFrameNumber()-1).getScore();
        }
        //if it is the last frame
        if(frame.getFrameNumber() == MAX_FRAMES-1){
            if(frame.isStrike() || frame.isSpare()){
                result = prevScore + frame.getTotalPinfallsForLastFrame();
            }
        } else if(frame.isStrike()){
            //if they are 2 strikes in a row
            if(frames.get(frame.getFrameNumber()+1).isStrike()){
                //if it is the first
                if(frame.getFrameNumber() != MAX_FRAMES-2) {
                    int nextFramePins = 0;
                    if(!frames.get(frame.getFrameNumber() + 2).getPinfalls()[0].equalsIgnoreCase("F")){
                        nextFramePins = Integer.parseInt(frames.get(frame.getFrameNumber() + 2).getPinfalls()[0]);
                    }
                    result = prevScore + MAX_PINS * 2 + nextFramePins;
                } else {
                    //this means it is the penultimate frame
                    //needs to be handled in a special way
                    result = prevScore + MAX_PINS + frames.get(frame.getFrameNumber()+1).getTotalPinFalls();
                }
            } else {
                //this means the next is not a strike so count the total
                result = prevScore + MAX_PINS + frames.get(frame.getFrameNumber()+1).getTotalPinFalls();
            }
            //if it is a spare
        } else if(frame.isSpare()) {
            int nextFramePins = 0;
            if(!frames.get(frame.getFrameNumber() + 1).getPinfalls()[0].equalsIgnoreCase("F")){
                nextFramePins = Integer.parseInt(frames.get(frame.getFrameNumber() + 1).getPinfalls()[0]);
            }
            result = prevScore + MAX_PINS + nextFramePins;
        } else {
            result = prevScore + frame.getTotalPinFalls();
        }

        return result;
    }


    /** Prints the result table in the required format **/
    @Override
    public void printResultBoard(LinkedHashMap<String, List<Frame>> frames) {

        clearConsole();

        String titleFormat = "%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n";
        System.out.println("------------------------------------------------RESULTS:------------------------------------------------");
        System.out.println();
        System.out.printf(titleFormat, "Frame", "1","2","3","4","5","6","7","8","9","10");

        frames.forEach((name, scoreList)->{
            System.out.println(name);
            System.out.print("Pinfalls");
            scoreList.stream().forEach((frame)->{
                String[] scores = new String[3];
                String pinfallsFormat1st = "%3s%5s";
                String pinfallsFormat = "%5s%5s";
                String tenthFrame = "%5s";

                if(frame.isStrike()){
                    if(frame.getFrameNumber()==MAX_FRAMES-1){
                        scores[0] = "X";
                        scores[1] = frame.getPinfalls()[1].equalsIgnoreCase(String.valueOf(MAX_PINS)) ? "X" : String.valueOf(frame.getPinfalls()[1]);
                        scores[2] = frame.getPinfalls()[2].equalsIgnoreCase(String.valueOf(MAX_PINS)) ? "X" : String.valueOf(frame.getPinfalls()[2]);
                    } else {
                        scores[0] = " ";
                        scores[1] = "X";
                        scores[2] = String.valueOf(frame.getPinfalls()[2]);
                    }
                } else if(frame.isSpare()){
                    scores[0] = String.valueOf(frame.getPinfalls()[0]);
                    scores[1] = "/";
                    scores[2] = String.valueOf(frame.getPinfalls()[2]);
                } else {
                    scores[0] = String.valueOf(frame.getPinfalls()[0]);
                    scores[1] = String.valueOf(frame.getPinfalls()[1]);
                    scores[2] = String.valueOf(frame.getPinfalls()[2]);
                }

                System.out.printf(frame.getFrameNumber()==0? pinfallsFormat1st : pinfallsFormat,
                        scores[0], scores[1]);
                if(frame.getFrameNumber()==9){
                    if(!scores[2].equalsIgnoreCase("null")){
                        System.out.printf(tenthFrame, scores[2]);
                     }
                    System.out.println();
                }

            });

            System.out.print("Score");
            scoreList.stream().forEach((frame)->{
                String scoreFormat1st = "%7s";
                String scoreFormat = "%10s";
                String tenthFormat = "%10s%n";
                if(frame.getFrameNumber() == MAX_FRAMES-1){
                    System.out.printf(tenthFormat, frame.getScore());
                } else{
                    System.out.printf(frame.getFrameNumber()==0? scoreFormat1st : scoreFormat, frame.getScore());
                }
            });
        });

        clearConsole();
    }

    private void clearConsole(){
        for(int i = 0; i < 5; i++)
        {
            System.out.println("\b");
        }
    }
}