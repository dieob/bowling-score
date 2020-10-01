package com.jobsity.challenge.models;

/**
 * Class Frame represents each frame on a Bowling Match.
 *
 * @author Diego Báez
 *
 */

import com.jobsity.challenge.interfaces.FileParserInterface;
import com.jobsity.challenge.utils.BowlingException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class is used to parse the file given as input
 *
 **/
public class Parser implements FileParserInterface {

    /** Method to handle the file **/
    @Override
    public LinkedHashMap<String, List<Frame>> handleFile(String filePath) {
        LinkedHashMap<String, List<Frame>> result;
        List<Line> lines = parseFile(filePath);

        if(getNumberOfPlayers(lines)==1){
            result = handleSinglePlayer(lines);
        } else{
            result = handleMultiplePlayers(lines);
        }

        return result;
    }

    /** Reads the lines from the file and store them as a List **/
    public List<Line> parseFile(String fileName) {
        String executionPath = System.getProperty("user.dir");
        FileInputStream fis;
        try {
             fis = new FileInputStream(executionPath
                    + "/src/main/java/com/jobsity/challenge/files/" + fileName);

        }catch(FileNotFoundException f){
            throw new BowlingException("File not found.");
        }

        Scanner sc = new Scanner(fis);    //file to be scanned
        String[] values;
        List<Line> result = new ArrayList<>();

        while (sc.hasNextLine()) {
            Line newLine = new Line();
            values = sc.nextLine().split("\\s+");

            if(values.length != 2){
                sc.close();
                throw new BowlingException("Invalid file format.");
            }

            if(!values[1].equalsIgnoreCase("F")){
                if(Integer.parseInt(values[1]) < 0 || Integer.parseInt(values[1])>10){
                    sc.close();
                    throw new BowlingException("Invalid pinfalls amount. Received: " + values[1] + ". Pinfalls should be between 0 and 10.");
                }
            }

            newLine.setName(values[0]);
            newLine.setPinfalls(values[1]);
            result.add(newLine);
        }
        sc.close();
        return result;
    }

    /** Creates the structure containing the frames for a game with only one player **/
    public LinkedHashMap<String, List<Frame>> handleSinglePlayer(List<Line> lines){
        int frameCount = -1;
        String score = "";
        String[] currentScore;
        List<Frame> frames = new ArrayList<>();
        LinkedHashMap<String, List<Frame>> result;
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
                if(frameCount>9){
                    throw new BowlingException("Max amount of frames exceeded.");
                }

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

    /** Creates the structure containing the frames for a game with more than one player **/
    public LinkedHashMap<String, List<Frame>> handleMultiplePlayers(List<Line> lines) {
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

    /** Calculate the number of players for this game **/
    public int getNumberOfPlayers(List<Line> lines) {
        List<String> players = new ArrayList<>();

        for (Line line : lines) {
            String player = line.getName();

            if (!players.contains(player)) {
                players.add(player);
            }
        }

        return players.size();
    }

    /** Packs the List of Frames with the player's name as a key, and it's frames as the value **/
    public LinkedHashMap<String, List<Frame>> packFrames(List<Frame> frames){
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

        //Validate that only correct amount of frames are in the file
        result.forEach((player, playerFrames)->{
            if(playerFrames.size()>10){
                throw new BowlingException("Max amount of frames exceeded.");
            }
        });

        return result;
    }
}
