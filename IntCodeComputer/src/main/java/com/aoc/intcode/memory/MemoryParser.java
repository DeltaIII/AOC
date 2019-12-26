package com.aoc.intcode.memory;

import java.util.LinkedList;
import java.util.List;


/**
 * Integer csv to {@link Memory} parser.
 *
 * Parses input of the form "1,2,3,4,5,6,7"
 */
public class MemoryParser {

    public static Memory parse(String csvString){
        return parse(csvString.split(","));
    }

    public static Memory parse(String[] inputIntegersAsStrings){
        List<Integer> inputs = new LinkedList<>();

        for (String inputIntegersAsString : inputIntegersAsStrings) {
            inputs.add(Integer.parseInt(inputIntegersAsString));
        }

        return new Memory(inputs);

    }


}
