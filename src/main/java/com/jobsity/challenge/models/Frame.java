package com.jobsity.challenge.models;

import java.util.List;

public class Frame {

    private static final int MAX_ATTEMPTS = 2;
    private static final int MAX_FRAMES = 10;
    private String player;
    private String[] pinfalls = new String[MAX_ATTEMPTS+1];
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

    public String[] getPinfalls() {
        return pinfalls;
    }

    public void setPinfalls(String[] pinfalls) {
        this.pinfalls = pinfalls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        if(this.pinfalls[0].equalsIgnoreCase("10")){
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
            if(!pinfalls[i].equalsIgnoreCase("F")){
                counter += Integer.parseInt(pinfalls[i]);
            }
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
            if(!pinfalls[i].equalsIgnoreCase("F")){
                total += Integer.parseInt(pinfalls[i]);
            }
        }
        return total;
    }

    public int getTotalPinfallsForLastFrame(){
        int total=0;
        for (int i = 0; i < this.pinfalls.length; i++) {
            if(!pinfalls[i].equalsIgnoreCase("F")){
                total += Integer.parseInt(pinfalls[i]);
            }
        }
        return total;
    }

    public void setTotalPinFalls(int totalPinFalls) {
        this.totalPinFalls = totalPinFalls;
    }
}
