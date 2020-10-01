package com.jobsity.challenge.utils;

/**
 * Class Frame represents each frame on a Bowling Match.
 *
 * @author Diego Báez
 *
 */

import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.models.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class FileParser {

    public static LinkedHashMap<String, List<Frame>> parseTextFile(String filePath) throws FileNotFoundException {
        LinkedHashMap<String, List<Frame>> result;
        List<Line> lines = parseFile(filePath);

        if(getNumberOfPlayers(lines)==1){
            result = handleSinglePlayer(lines);
        } else{
            result = handleMultiplePlayers(lines);
        }

        return result;
    }

    public static LinkedHashMap<String, List<Frame>> handleSinglePlayer(List<Line> lines){
        int frameCount = -1;
        String score = "";
        String[] currentScore = new String[3];
        List<Frame> frames = new ArrayList<>();
        LinkedHashMap<String, List<Frame>> result = new LinkedHashMap<>();
        int lineCount = 0;

        while(lineCount<lines.size()){
            frameCount++;
            currentScore = new String[3];
            score = lines.get(lineCount).getPinfalls();

            if(score.equalsIgnoreCase("10")){
                currentScore[0] = "10";
                if(frameCount==9){
                    currentScore[1] = lines.get(lineCount+1).getPinfalls();
                    currentScore[2] = lines.get(lineCount+2).getPinfalls();
                    lineCount+=2;
                }
            } else{
                currentScore[0]=score;

                currentScore[1]=lines.get(lineCount+1).getPinfalls();
                if(frameCount==9){
                    if(lines.size()-lineCount==3){
                        currentScore[2]=lines.get(lineCount+2).getPinfalls();
                        lineCount+=2;
                    }
                } else {
                    lineCount++; //increase counter to skip next line
                }
            }

            Frame currentFrame = new Frame(lines.get(lineCount).getName(), currentScore, frameCount, 0);
            frames.add(currentFrame);
            lineCount++;
        }

        result = packFrames(frames);

        return result;
    }

    public static LinkedHashMap<String, List<Frame>> handleMultiplePlayers(List<Line> lines) {
        LinkedHashMap<String, List<Frame>> result;
        List<String> players = new ArrayList<>();
        String lastPlayer = "";
        List<Frame> frames = new ArrayList<>();
        int scoreCount = -1;
        String[] currentScore = new String[3];
        String score = "";
        int frameCount = -1;
        boolean firstPlayer = false;
        String firstPlayerName ="";

            for (Line line : lines) {
                String player = line.getName();

                if(!players.contains(player)){
                    if(!firstPlayer){
                        firstPlayerName = player;
                        firstPlayer = true;
                    }
                    players.add(player);
                }

                score = line.getPinfalls();

                scoreCount++;
                if (!player.equalsIgnoreCase(lastPlayer) && !lastPlayer.equalsIgnoreCase("")) {
                    //Save the last player frame

                    if(lastPlayer.equalsIgnoreCase(firstPlayerName)){
                        frameCount++;
                    }

                    Frame currentFrame = new Frame(lastPlayer, currentScore, frameCount, 0);
                    frames.add(currentFrame);

                    //begin new player's frame
                    scoreCount = 0;
                    currentScore = new String[3];
                }
                currentScore[scoreCount] = score;
                lastPlayer = player;
            }

            //Save last throw
        Frame currentFrame = new Frame(lastPlayer, currentScore, frameCount, 0);
        frames.add(currentFrame);

        result = packFrames(frames);

        return result;
    }

    public static int getNumberOfPlayers(List<Line> lines) throws FileNotFoundException {
        List<String> players = new ArrayList<>();

        for (Line line : lines) {
            String player = line.getName();

            if (!players.contains(player)) {
                players.add(player);
            }
        }

        return players.size();
    }

    public static LinkedHashMap<String, List<Frame>> packFrames(List<Frame> frames){
        LinkedHashMap<String, List<Frame>> result = new LinkedHashMap<>();
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
        return result;
    }

    public static List<Line> parseFile(String fileName) throws FileNotFoundException {
        String executionPath = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(executionPath
                +"/src/main/java/com/jobsity/challenge/files/"+fileName);
        Scanner sc = new Scanner(fis);    //file to be scanned
        String[] values;
        List<Line> result = new ArrayList<>();

        while (sc.hasNextLine()) {
            Line newLine = new Line();
            values = sc.nextLine().split("\\s+");
            newLine.setName(values[0]);
            newLine.setPinfalls(values[1]);
            result.add(newLine);
        }
        sc.close();
        return result;
    }

    public static void printMap(LinkedHashMap<String, List<Frame>> result){
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
    }
}
