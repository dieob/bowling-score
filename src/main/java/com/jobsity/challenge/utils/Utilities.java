package com.jobsity.challenge.utils;

import com.jobsity.challenge.models.Play;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Utilities {

    public static HashMap<String, List<Play>> parseTextFile(String filePath) {
        String[] values;
        HashMap<String, List<Play>> result = new HashMap<>();
        List<String> players = new ArrayList<>();
        String lastPlayer = "";
        List<Play> plays = new ArrayList<>();
        int scoreCount = -1;
        int[] currentScore = new int[3];
        int score = 0;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis);    //file to be scanned

            while (sc.hasNextLine()) {
                values = sc.nextLine().split("\\s+");
                String player = values[0];

                if(!players.contains(player)){
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
                    Play currentPlay = new Play();
                    currentPlay.setPlayer(lastPlayer);
                    currentPlay.setPinfalls(currentScore);
                    plays.add(currentPlay);

                    //begin new player's play
                    scoreCount = 0;
                    currentScore = new int[3];
                }
                currentScore[scoreCount] = score;
                lastPlayer = player;
            }

            //Save last throw
            Play currentPlay = new Play();
            currentPlay.setPlayer(lastPlayer);
            currentPlay.setPinfalls(currentScore);
            plays.add(currentPlay);
            sc.close();     //closes the scanner

            for (Play play : plays) {
                System.out.println(play.getPlayer());
                System.out.println(play.getPinfalls()[0] + "-" + play.getPinfalls()[1] + "-" + play.getPinfalls()[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Play play : plays) {
            List<Play> newList;

            if(result.containsKey(play.getPlayer())){
                newList = result.get(play.getPlayer());
            } else {
                newList = new ArrayList<>();
            }

            newList.add(play);
            result.put(play.getPlayer(), newList);
        }

        for(Object key : result.keySet()){
            System.out.print(key);
            for(int k=0; k<result.get(key).size(); k++) {
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
