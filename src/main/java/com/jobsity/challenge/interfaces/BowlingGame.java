package com.jobsity.challenge.interfaces;

/**
 * BowlingGame interface represents the methods that must be implemented.
 * for a Bowling Game
 *
 * @author Diego Báez
 *
 */

import com.jobsity.challenge.models.Frame;

import java.util.LinkedHashMap;
import java.util.List;

public interface BowlingGame {
    int calculateScore (Frame frame);

    void printResultBoard(LinkedHashMap<String, List<Frame>> frames);
}
