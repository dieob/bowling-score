package com.jobsity.challenge.models;

import java.util.List;

public class Frame {

    private static final int MAX_ATTEMPTS = 2;
    private static final int MAX_FRAMES = 10;
    private String player;
    private int[] pinfalls = new int[MAX_ATTEMPTS+1];
    private int totalPinFalls;
    private int score;
    private boolean isStrike;
    private boolean isSpare;
    private boolean isLastFrame;
    private int frameNumber;


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int[] getPinfalls() {
        return pinfalls;
    }

    public void setPinfalls(int[] pinfalls) {
        this.pinfalls = pinfalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        if(this.pinfalls[0]==10){
            return true;
        } else {
            return false;
        }
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public boolean isSpare() {
        int counter =0;
        for (int i = 0; i < pinfalls.length-1; i++) {
            counter += pinfalls[i];
        }
        if(counter==10){
            return true;
        } else {
            return false;
        }
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public boolean isLastFrame() {
        return frameNumber==MAX_FRAMES;
    }

    public void setLastFrame(boolean lastFrame) {
        isLastFrame = lastFrame;
    }

    public int getTotalPinFalls() {
        int total=0;
        for (int i = 0; i < this.pinfalls.length-1; i++) {
            total += this.pinfalls[i];
        }
        return total;
    }

    public int getTotalPinfallsForLastFrame(){
        int total=0;
        for (int i = 0; i < this.pinfalls.length; i++) {
            total += this.pinfalls[i];
        }
        return total;
    }

    public void setTotalPinFalls(int totalPinFalls) {
        this.totalPinFalls = totalPinFalls;
    }
}
