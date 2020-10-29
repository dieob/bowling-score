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
}