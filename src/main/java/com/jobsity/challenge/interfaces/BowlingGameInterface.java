package com.jobsity.challenge.interfaces;

/**
 * BowlingGame interface represents the methods that must be implemented.
 * for a Bowling Game
 *
 * @author Diego Báez
 *
 */

import com.jobsity.challenge.models.Frame;

public interface BowlingGameInterface {

    int calculateScore (Frame frame);
}
