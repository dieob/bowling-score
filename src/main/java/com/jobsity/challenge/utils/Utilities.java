package com.jobsity.challenge.utils;

import com.jobsity.challenge.models.Frame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Utilities {

    public static LinkedHashMap<String, List<Frame>> parseTextFile(String filePath) {
        String[] values;
        LinkedHashMap<String, List<Frame>> result = new LinkedHashMap<>();
        List<String> players = new ArrayList<>();
        String lastPlayer = "";
        List<Frame> frames = new ArrayList<>();
        int scoreCount = -1;
        int[] currentScore = new int[3];
        int score = 0;
        int frameCount = -1;
        boolean firstPlayer = false;
        String firstPlayerName ="";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);    //file to be scanned

            while (sc.hasNextLine()) {
                values = sc.nextLine().split("\\s+");
                String player = values[0];

                if(!players.contains(player)){
                    if(!firstPlayer){
                        firstPlayerName = player;
                        firstPlayer = true;
                    }
                    players.add(player);
                }

                if (isInteger(values[1])) {
                    score = Integer.valueOf(values[1]);
                } else {
                    score = 0;
                }

                scoreCount++;
                if (!player.equalsIgnoreCase(lastPlayer) && !lastPlayer.equalsIgnoreCase("")) {
                    //Save the last player play

                    if(lastPlayer.equalsIgnoreCase(firstPlayerName)){
                        frameCount++;
                    }

                    Frame currentFrame = new Frame();
                    currentFrame.setPlayer(lastPlayer);
                    currentFrame.setPinfalls(currentScore);
                    currentFrame.setFrameNumber(frameCount);
                    frames.add(currentFrame);

                    //begin new player's play
                    scoreCount = 0;
                    currentScore = new int[3];
                }
                currentScore[scoreCount] = score;
                lastPlayer = player;
            }

            //Save last throw
            Frame currentFrame = new Frame();
            currentFrame.setPlayer(lastPlayer);
            currentFrame.setPinfalls(currentScore);
            currentFrame.setFrameNumber(frameCount);
            frames.add(currentFrame);
            sc.close();     //closes the scanner

            for (Frame frame : frames) {
                System.out.println(frame.getPlayer());
                System.out.println(frame.getPinfalls()[0] + "-" + frame.getPinfalls()[1] + "-" + frame.getPinfalls()[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Frame frame : frames) {
            List<Frame> newList;

            if(result.containsKey(frame.getPlayer())){
                newList = result.get(frame.getPlayer());
            } else {
                newList = new ArrayList<>();
            }

            newList.add(frame);
            result.put(frame.getPlayer(), newList);
        }

        for(Object key : result.keySet()){
            System.out.print(key);
            for(int k=0; k<result.get(key).size(); k++) {
                System.out.print(" ");
                System.out.print(result.get(key).get(k).getFrameNumber());
                System.out.print(" ");
                System.out.print(result.get(key).get(k).getPinfalls()[0]);
                System.out.print(" ");
                System.out.print(result.get(key).get(k).getPinfalls()[1]);
                System.out.print("//");
            }
            System.out.println(" ");
        }

        return result;
    }

    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
}
