package com.aoc.intcode.memory;

import java.util.LinkedList;
import java.util.List;


/**
 * Integer csv to {@link Memory} parser.
 *
 * Parses input of the form "1,2,3,4,5,6,7"
 */
public class MemoryParser {

    public static Memory parseProgram(String csvString){
        return parseProgram(csvString.split(","));
    }

    public static Memory parseProgram(String[] inputIntegersAsStrings){
        List<Long> inputs = new LinkedList<>();

        for (String inputIntegersAsString : inputIntegersAsStrings) {
            inputs.add(Long.parseLong(inputIntegersAsString));
        }

        return new Memory(inputs);

    }


}
