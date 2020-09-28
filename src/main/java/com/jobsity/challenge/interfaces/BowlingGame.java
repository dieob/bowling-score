package com.jobsity.challenge.interfaces;

import com.jobsity.challenge.models.Frame;

import java.util.List;

public interface BowlingGame {
    int calculateScore (Frame frame);

    void printTable(List<Frame> frameList);
}
