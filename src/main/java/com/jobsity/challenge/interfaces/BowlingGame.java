package com.jobsity.challenge.interfaces;

import com.jobsity.challenge.models.Frame;

import java.util.LinkedHashMap;
import java.util.List;

public interface BowlingGame {
    int calculateScore (Frame frame);

    void printTable(LinkedHashMap<String, List<Frame>> frames);
}
