package com.jobsity.challenge.interfaces;

import com.jobsity.challenge.models.Frame;

import java.util.LinkedHashMap;
import java.util.List;

public interface ResultPrinterInterface {

    void printResultBoard(LinkedHashMap<String, List<Frame>> frames);
}
