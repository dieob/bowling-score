package com.jobsity.challenge.interfaces;

import com.jobsity.challenge.models.Frame;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * FileParser interface represents the methods
 * that must be implemented to parse files according to format.
 *
 * @author Diego Báez
 *
 */

public interface FileParser {
    // Must implement a method to handle the file according to its needs
    LinkedHashMap<String, List<Frame>> handleFile(String fileName);
}
