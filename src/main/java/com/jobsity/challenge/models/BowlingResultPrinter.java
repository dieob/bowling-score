package com.jobsity.challenge.models;

import com.jobsity.challenge.interfaces.ResultPrinterInterface;

import java.util.LinkedHashMap;
import java.util.List;

public class BowlingResultPrinter implements ResultPrinterInterface {


    private static final int MAX_FRAMES = 10;
    private static final int MAX_PINS = 10;

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
