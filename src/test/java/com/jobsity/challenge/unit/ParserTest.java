package com.jobsity.challenge.unit;


import com.jobsity.challenge.models.Frame;
import com.jobsity.challenge.models.Parser;
import com.jobsity.challenge.exceptions.BowlingException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Unit test for the Parser class.
 */

public class ParserTest {

    String testFilepath;
    String badFormatFile;
    String exceedFramesFile;
    String negativePinfallsFile;
    String moreThan10PinfallsFile;

    @Before
    public void setUp() {
        testFilepath = System.getProperty("user.dir")
                + "/src/test/java/com/jobsity/challenge/unit/resources/";
        badFormatFile = "bad-format.txt";
        exceedFramesFile = "exceed-frames.txt";
        negativePinfallsFile = "negative-pinfalls.txt";
        moreThan10PinfallsFile = "more-10.txt";
    }

    @Test
    public void badFormatTest(){
        Parser parser = new Parser();

        try {
            LinkedHashMap<String, List<Frame>> gameFrames =
                    parser.handleFile(testFilepath + badFormatFile);
        } catch(BowlingException be){
            assertEquals("Invalid file format.", be.getMessage());
        }

    }

    @Test
    public void exceedFramesTest(){
        Parser parser = new Parser();

        try {
            LinkedHashMap<String, List<Frame>> gameFrames =
                    parser.handleFile(testFilepath + exceedFramesFile);
        } catch(BowlingException be){
            assertEquals("Max amount of frames exceeded.", be.getMessage());
        }

    }

    @Test
    public void negativePinfallsTest(){
        Parser parser = new Parser();

        try {
            LinkedHashMap<String, List<Frame>> gameFrames =
                    parser.handleFile(testFilepath + negativePinfallsFile);
        } catch(BowlingException be){
            assertEquals("Invalid pinfalls amount.", be.getMessage());
        }

    }

    @Test
    public void greaterThan10PinfallsTest(){
        Parser parser = new Parser();

        try {
            LinkedHashMap<String, List<Frame>> gameFrames =
                    parser.handleFile(testFilepath + moreThan10PinfallsFile);
        } catch(BowlingException be){
            assertEquals("Invalid pinfalls amount.", be.getMessage());
        }

    }
}
