package com.jobsity.challenge.models;

import com.jobsity.challenge.interfaces.BowlingGame;
import java.util.List;

public final class BowlingGameScoreBoard implements BowlingGame {


    private final List<Frame> frames;
    private static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;


    /**
     * setup the game, ie all {@link BowlingGameScoreBoard#MAX_FRAMES} frames
     */
    public BowlingGameScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }

    /*@Override
    public int calculateScore(Play frame) {
        //if it is the last frame count all pinfall opportunities
        if(frame.getFrameNumber() == MAX_FRAMES-1){
            return frames.get(frame.getFrameNumber()-1).getScore() + frame.getTotalPinfallsForLastFrame();
        }

        //if it is a strike
        if(frame.isStrike()){

            //if the last 3 are strikes
            if(frame.getFrameNumber() < MAX_FRAMES-2 && frame.getFrameNumber() != 0){
                if(frames.get(frame.getFrameNumber()+1).isStrike() && frames.get(frame.getFrameNumber()+2).isStrike()){
                    return frames.get(frame.getFrameNumber()-1).getScore() + MAX_PINS*3;
                }
            }
            //if the penultimo
            else if (frame.getFrameNumber() == MAX_FRAMES-2 && frames.get(frame.getFrameNumber()+1).isStrike()){
                return frames.get(frame.getFrameNumber()-1).getScore() + frame.getTotalPinFalls() +
                        + frames.get(frame.getFrameNumber()+1).getPinfalls()[0] + frames.get(frame.getFrameNumber()+1).getPinfalls()[1];
            }

            //if it is the first frame
            if(frame.getFrameNumber() == 0){
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()+1).getPinfalls()[0]
                        + frames.get(frame.getFrameNumber()+1).getPinfalls()[1];
            }
            //if it is the last frame
            else if (frame.getFrameNumber() == MAX_FRAMES-1){
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()-1).getScore();
            }
            //if it is a frame in the middle
            else {
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()-1).getScore()
                        + calculateScore(frames.get(frame.getFrameNumber()+1));
            }
        }

        //if it is a spare
        else if(frame.isSpare()){
            //if it is the first frame
            if(frame.getFrameNumber() == 0){
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()+1).getPinfalls()[0];
            }
            //if it is the last frame
            else if(frame.getFrameNumber() == MAX_FRAMES-1) {
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()-1).getScore();
            }
            //if it is a frame in the middle
            else{
                return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()-1).getScore()
                        + frames.get(frame.getFrameNumber()+1).getPinfalls()[0];
            }
        }
        //if it is not spare or strike
        else {
            if(frame.getFrameNumber() == 0){
                return frame.getTotalPinFalls();
            }
            return frame.getTotalPinFalls() + frames.get(frame.getFrameNumber()-1).getScore();
        }
    } */

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
                    result = prevScore + MAX_PINS * 2 + frames.get(frame.getFrameNumber() + 2).getPinfalls()[0];
                } else {
                    //this means it is the penultimo frame
                    //needs to be handled in a special way
                    result = prevScore + MAX_PINS + frames.get(frame.getFrameNumber()+1).getTotalPinFalls();
                }
            } else {
                //this means the next is not a strike so count the total
                result = prevScore + MAX_FRAMES + frames.get(frame.getFrameNumber()+1).getTotalPinFalls();
            }
            //if it is a spare
        } else if(frame.isSpare()) {
            result = prevScore + MAX_FRAMES + frames.get(frame.getFrameNumber()+1).getPinfalls()[0];
        } else {
            result = prevScore + frame.getTotalPinFalls();
        }

        return result;
    }


    @Override
    public void printTable(List<Frame> frameList) {

    }
}