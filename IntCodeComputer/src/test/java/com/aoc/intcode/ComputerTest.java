package com.aoc.intcode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Nested
    class DayTwoTests{

        @Test
        public void testCase1() {
            String input = "1,9,10,3,2,3,11,0,99,30,40,50";
            int expectedOutput = 3500;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        @Test
        public void testCase2(){
            String input = "1,1,1,4,99,5,6,0,99";
            int expectedOutput = 30;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        @Test
        public void testCase3(){
            String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,9,23,1,23,13,27,1,10,27,31,2,31,13,35,1,10,35,39,2,9,39,43,2,43,9,47,1,6,47,51,1,10,51,55,2,55,13,59,1,59,10,63,2,63,13,67,2,67,9,71,1,6,71,75,2,75,9,79,1,79,5,83,2,83,13,87,1,9,87,91,1,13,91,95,1,2,95,99,1,99,6,0,99,2,14,0,0";
            int expectedOutput = 3085697;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        private int getFirstIntegerAfterRunning(String input){
            Computer intCodeComputer = Computer.initialise(input);
            List<Integer> finalState = intCodeComputer.run();
            return finalState.get(0);
        }
    }

    @Nested
    class DayFiveTests{

        @Test
        public void muliplyWithImmediateMode(){
            String input = "1002,4,3,4,33";
            String expected = "[1002, 4, 3, 4, 99]";
            Computer intCodeComputer = Computer.initialise(input);
            assertEquals(expected, intCodeComputer.run().toString());
        }

        @Test
        public void testInputOutput(){
            String computerInput = "3,0,4,0,99";
            Integer input = Integer.valueOf(12456);
            String expectedMemoryState = "["+input+", 0, 4, 0, 99]";
            Computer intCodeComputer = Computer.initialise(computerInput);

            //Act
            List<Integer> returnedMemoryState = intCodeComputer.run(Arrays.asList(input));

            assertEquals(expectedMemoryState, returnedMemoryState.toString());

            List<Integer> outputs = intCodeComputer.getOutputs();
            assertEquals(1,outputs.size());
            assertEquals(input, outputs.get(0));
        }

        @Test
        public void partOne(){
            String computerInput = "3,225,1,225,6,6,1100,1,238,225,104,0,1101,91,67,225,1102,67,36,225,1102,21,90,225,2,13,48,224,101,-819,224,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,62,9,225,1,139,22,224,101,-166,224,224,4,224,1002,223,8,223,101,3,224,224,1,223,224,223,102,41,195,224,101,-2870,224,224,4,224,1002,223,8,223,101,1,224,224,1,224,223,223,1101,46,60,224,101,-106,224,224,4,224,1002,223,8,223,1001,224,2,224,1,224,223,223,1001,191,32,224,101,-87,224,224,4,224,102,8,223,223,1001,224,1,224,1,223,224,223,1101,76,90,225,1101,15,58,225,1102,45,42,224,101,-1890,224,224,4,224,1002,223,8,223,1001,224,5,224,1,224,223,223,101,62,143,224,101,-77,224,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223,1101,55,54,225,1102,70,58,225,1002,17,80,224,101,-5360,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,102,2,223,223,1005,224,329,1001,223,1,223,1108,677,226,224,1002,223,2,223,1006,224,344,101,1,223,223,107,677,226,224,1002,223,2,223,1006,224,359,101,1,223,223,108,677,677,224,1002,223,2,223,1006,224,374,1001,223,1,223,108,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,404,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1008,226,677,224,102,2,223,223,1006,224,434,101,1,223,223,107,226,226,224,102,2,223,223,1005,224,449,1001,223,1,223,1007,677,677,224,1002,223,2,223,1006,224,464,1001,223,1,223,1007,226,226,224,1002,223,2,223,1005,224,479,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,494,1001,223,1,223,8,226,226,224,102,2,223,223,1006,224,509,101,1,223,223,1107,677,677,224,102,2,223,223,1005,224,524,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,554,101,1,223,223,1007,677,226,224,1002,223,2,223,1005,224,569,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,584,101,1,223,223,107,677,677,224,1002,223,2,223,1005,224,599,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,614,101,1,223,223,7,677,677,224,1002,223,2,223,1006,224,629,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,659,1001,223,1,223,8,677,226,224,1002,223,2,223,1005,224,674,101,1,223,223,4,223,99,226";
            List<Integer> inputs = Arrays.asList(1);

            Computer intCodeComputer = Computer.initialise(computerInput);

            //Act
            intCodeComputer.run(inputs);

            List<Integer> outputs = intCodeComputer.getOutputs();
            int expectedDiagnosticCode = 15508323;

            int diagnosticCodeIndex = outputs.size()-1;
            int outputNumber = 0;
            for (int index = 0; index<diagnosticCodeIndex; index++){
                assertEquals(0,outputs.get(index),"Did not get a non-zero code for output number " + outputNumber);
                outputNumber++;
            }

            assertEquals(expectedDiagnosticCode, outputs.get(diagnosticCodeIndex));
        }
    }
}