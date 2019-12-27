package com.aoc.intcode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Nested
    class DayTwoTests{

        @Test
        public void testCase1() {
            String input = "1,9,10,3,2,3,11,0,99,30,40,50";
            long expectedOutput = 3500;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        @Test
        public void testCase2(){
            String input = "1,1,1,4,99,5,6,0,99";
            long expectedOutput = 30;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        @Test
        public void testCase3(){
            String input = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,19,9,23,1,23,13,27,1,10,27,31,2,31,13,35,1,10,35,39,2,9,39,43,2,43,9,47,1,6,47,51,1,10,51,55,2,55,13,59,1,59,10,63,2,63,13,67,2,67,9,71,1,6,71,75,2,75,9,79,1,79,5,83,2,83,13,87,1,9,87,91,1,13,91,95,1,2,95,99,1,99,6,0,99,2,14,0,0";
            long expectedOutput = 3085697;
            assertEquals(expectedOutput,getFirstIntegerAfterRunning(input));
        }

        private long getFirstIntegerAfterRunning(String input){
            Computer intCodeComputer = Computer.initialise(input);
            List<Long> finalState = intCodeComputer.run();
            return finalState.get(0);
        }
    }

    @Nested
    class DayFiveTests{
        private static final String DAY_INPUT =
                "3,225,1,225,6,6,1100,1,238,225,104,0,1101,91,67,225,1102,67,36,225,1102,21,90,225,2,13,48,224,101," +
                        "-819,224,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,62,9,225,1,139,22,224,101," +
                        "-166,224,224,4,224,1002,223,8,223,101,3,224,224,1,223,224,223,102,41,195,224,101,-2870,224,224," +
                        "4,224,1002,223,8,223,101,1,224,224,1,224,223,223,1101,46,60,224,101,-106,224,224,4,224,1002,223," +
                        "8,223,1001,224,2,224,1,224,223,223,1001,191,32,224,101,-87,224,224,4,224,102,8,223,223,1001,224," +
                        "1,224,1,223,224,223,1101,76,90,225,1101,15,58,225,1102,45,42,224,101,-1890,224,224,4,224,1002,223," +
                        "8,223,1001,224,5,224,1,224,223,223,101,62,143,224,101,-77,224,224,4,224,1002,223,8,223,1001,224," +
                        "4,224,1,224,223,223,1101,55,54,225,1102,70,58,225,1002,17,80,224,101,-5360,224,224,4,224,102,8," +
                        "223,223,1001,224,3,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105," +
                        "227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999," +
                        "1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1," +
                        "0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677," +
                        "77,224,102,2,223,223,1005,224,329,1001,223,1,223,1108,677,226,224,1002,223,2,223,1006,224,344,101," +
                        "1,223,223,107,677,226,224,1002,223,2,223,1006,224,359,101,1,223,223,108,677,677,224,1002,223,2,223," +
                        "1006,224,374,1001,223,1,223,108,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,7,226,677,224," +
                        "102,2,223,223,1006,224,404,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,419,101,1,223," +
                        "223,1008,226,677,224,102,2,223,223,1006,224,434,101,1,223,223,107,226,226,224,102,2,223,223,1005," +
                        "224,449,1001,223,1,223,1007,677,677,224,1002,223,2,223,1006,224,464,1001,223,1,223,1007,226,226," +
                        "224,1002,223,2,223,1005,224,479,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,494,1001," +
                        "223,1,223,8,226,226,224,102,2,223,223,1006,224,509,101,1,223,223,1107,677,677,224,102,2,223,223," +
                        "1005,224,524,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,1107,677," +
                        "226,224,1002,223,2,223,1006,224,554,101,1,223,223,1007,677,226,224,1002,223,2,223,1005,224,569," +
                        "101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,584,101,1,223,223,107,677,677,224,1002,223," +
                        "2,223,1005,224,599,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,614,101,1,223,223,7,677," +
                        "677,224,1002,223,2,223,1006,224,629,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,644," +
                        "101,1,223,223,108,226,226,224,102,2,223,223,1005,224,659,1001,223,1,223,8,677,226,224,1002,223," +
                        "2,223,1005,224,674,101,1,223,223,4,223,99,226";

        @Nested
        class PartOneTests {
            @Test
            public void muliplyWithImmediateMode() {
                String input = "1002,4,3,4,33";
                String expected = "[1002, 4, 3, 4, 99]";
                Computer intCodeComputer = Computer.initialise(input);
                assertEquals(expected, intCodeComputer.run().toString());
            }

            @Test
            public void testInputOutput() {
                String computerInput = "3,0,4,0,99";
                Long input = 12456L;
                String expectedMemoryState = "[" + input + ", 0, 4, 0, 99]";
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                List<Long> returnedMemoryState = intCodeComputer.run(Collections.singletonList(input));

                assertEquals(expectedMemoryState, returnedMemoryState.toString());

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1, outputs.size());
                assertEquals(input, outputs.get(0));
            }

            @Test
            public void partOne(){
                List<Long> inputs = Collections.singletonList(1L);

                Computer intCodeComputer = Computer.initialise(DAY_INPUT);

                //Act
                intCodeComputer.run(inputs);

                List<Long> outputs = intCodeComputer.getOutputs();
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
        @Nested
        class EqualsOpCodeTests{

            @Test
            public void positionModeNotEqual(){
                String computerInput = "3,9,8,9,10,9,4,9,99,-1,8";
                Long input = 12456L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void positionModeEqual(){
                String computerInput = "3,9,8,9,10,9,4,9,99,-1,8";
                Long input = 8L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(1, outputs.get(0));
            }

            @Test
            public void immediateModeNotEqual(){
                String computerInput = "3,3,1108,-1,8,3,4,3,99";
                Long input = 1L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void immediateModeEqual(){
                String computerInput = "3,3,1108,-1,8,3,4,3,99";
                Long input = 8L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(1, outputs.get(0));
            }
        }

        @Nested
        class LessThanOpCodeTests{

            @Test
            public void positionModeGreaterThan(){
                String computerInput = "3,9,7,9,10,9,4,9,99,-1,8";
                Long input = 12456L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void positionModeEqual(){
                String computerInput = "3,9,7,9,10,9,4,9,99,-1,8";
                Long input = 8L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void positionModeLessThan(){
                String computerInput = "3,9,7,9,10,9,4,9,99,-1,8";
                Long input = 2L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(1, outputs.get(0));
            }

            @Test
            public void immediateModeGreaterThan(){
                String computerInput = "3,3,1107,-1,8,3,4,3,99";
                Long input = 11L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void immediateModeEqual(){
                String computerInput = "3,3,1107,-1,8,3,4,3,99";
                Long input = 8L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(0, outputs.get(0));
            }

            @Test
            public void immediateModeLessThan(){
                String computerInput = "3,3,1107,-1,8,3,4,3,99";
                Long input = 1L;
                Computer intCodeComputer = Computer.initialise(computerInput);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(1, outputs.get(0));
            }
        }

        @Nested
        class JumpPointerTests{

            private static final String POSITION_MODE_INPUT =  "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
            private static final String IMMEDIATE_MODE_INPUT =  "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";

            @Test
            public void jumpWithPositionZero(){
                Long expected = 0L;
                Long input = 0L;
                Computer intCodeComputer = Computer.initialise(POSITION_MODE_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

            @Test
            public void jumpWithPositionNonZero(){
                Long expected = 1L;
                Long input = 12456L;
                Computer intCodeComputer = Computer.initialise(POSITION_MODE_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

            @Test
            public void jumpWithImmediateZero(){
                Long expected = 0L;
                Long input = 0L;
                Computer intCodeComputer = Computer.initialise(IMMEDIATE_MODE_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

            @Test
            public void jumpWithImmediateNonZero(){
                Long expected = 1L;
                Long input = 12456L;
                Computer intCodeComputer = Computer.initialise(IMMEDIATE_MODE_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

        }

        @Nested
        class PartTwoTests{

            private static final String COMPUTER_INPUT =
                    "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,"+
                    "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,"+
                    "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

            @Test
            public void greaterThanEight(){
                Long expected = 1001L;
                Long input = 12456L;
                Computer intCodeComputer = Computer.initialise(COMPUTER_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

            @Test
            public void EqualToEight(){
                Long expected = 1000L;
                Long input = 8L;
                Computer intCodeComputer = Computer.initialise(COMPUTER_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }

            @Test
            public void LessThanEight(){
                Long expected = 999L;
                Long input = 2L;
                Computer intCodeComputer = Computer.initialise(COMPUTER_INPUT);

                //Act
                intCodeComputer.run(Collections.singletonList(input));

                List<Long> outputs = intCodeComputer.getOutputs();
                assertEquals(1,outputs.size());
                assertEquals(expected, outputs.get(0));
            }


        }

        @Nested
        class PartTwoRun{

            @Test
            public void partTwo(){
                List<Long> inputs = Collections.singletonList(5L);

                Computer intCodeComputer = Computer.initialise(DAY_INPUT);

                //Act
                intCodeComputer.run(inputs);

                List<Long> outputs = intCodeComputer.getOutputs();
                int expectedDiagnosticCode = 9006327;

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

    @Nested
    class DayNineTests{

        private static final String DAY_PROGRAM_INPUT = "1102,34463338,34463338,63,1007,63,34463338,63,1005,63,53,1101,3,0,1000,109,988,209,12,9,1000,209,6,209,3,203,0,1008,1000,1,63,1005,63,65,1008,1000,2,63,1005,63,904,1008,1000,0,63,1005,63,58,4,25,104,0,99,4,0,104,0,99,4,17,104,0,99,0,0,1102,1,37,1000,1101,856,0,1029,1101,286,0,1025,1101,39,0,1004,1101,861,0,1028,1101,845,0,1026,1102,28,1,1002,1102,1,0,1020,1101,0,892,1023,1101,0,291,1024,1101,35,0,1018,1101,0,27,1006,1102,1,26,1011,1101,33,0,1019,1102,31,1,1014,1102,1,36,1010,1102,23,1,1007,1101,0,32,1016,1101,29,0,1008,1101,20,0,1001,1102,1,25,1015,1101,38,0,1017,1101,0,24,1012,1102,1,22,1005,1101,1,0,1021,1101,0,21,1003,1102,1,838,1027,1102,1,30,1013,1101,895,0,1022,1101,0,34,1009,109,7,1208,0,22,63,1005,63,201,1001,64,1,64,1105,1,203,4,187,1002,64,2,64,109,-6,2102,1,5,63,1008,63,24,63,1005,63,223,1105,1,229,4,209,1001,64,1,64,1002,64,2,64,109,17,21102,40,1,-6,1008,1012,40,63,1005,63,255,4,235,1001,64,1,64,1106,0,255,1002,64,2,64,109,-15,21108,41,41,9,1005,1012,277,4,261,1001,64,1,64,1106,0,277,1002,64,2,64,109,11,2105,1,10,4,283,1105,1,295,1001,64,1,64,1002,64,2,64,109,-9,21101,42,0,8,1008,1013,44,63,1005,63,315,1105,1,321,4,301,1001,64,1,64,1002,64,2,64,109,13,1206,3,337,1001,64,1,64,1106,0,339,4,327,1002,64,2,64,109,-10,1208,0,29,63,1005,63,361,4,345,1001,64,1,64,1106,0,361,1002,64,2,64,109,2,2108,27,-4,63,1005,63,383,4,367,1001,64,1,64,1105,1,383,1002,64,2,64,109,-4,1207,2,30,63,1005,63,405,4,389,1001,64,1,64,1105,1,405,1002,64,2,64,109,22,1205,-8,417,1106,0,423,4,411,1001,64,1,64,1002,64,2,64,109,-27,2108,19,0,63,1005,63,443,1001,64,1,64,1106,0,445,4,429,1002,64,2,64,109,13,21108,43,45,-1,1005,1013,461,1106,0,467,4,451,1001,64,1,64,1002,64,2,64,109,1,21107,44,45,4,1005,1019,485,4,473,1105,1,489,1001,64,1,64,1002,64,2,64,109,-8,2102,1,-7,63,1008,63,37,63,1005,63,515,4,495,1001,64,1,64,1106,0,515,1002,64,2,64,109,1,2107,38,-4,63,1005,63,533,4,521,1105,1,537,1001,64,1,64,1002,64,2,64,109,4,21107,45,44,1,1005,1013,553,1106,0,559,4,543,1001,64,1,64,1002,64,2,64,109,-7,2107,21,-4,63,1005,63,575,1106,0,581,4,565,1001,64,1,64,1002,64,2,64,109,9,1205,7,599,4,587,1001,64,1,64,1105,1,599,1002,64,2,64,109,-11,2101,0,-3,63,1008,63,40,63,1005,63,619,1105,1,625,4,605,1001,64,1,64,1002,64,2,64,109,1,2101,0,-2,63,1008,63,28,63,1005,63,651,4,631,1001,64,1,64,1106,0,651,1002,64,2,64,109,1,21102,46,1,7,1008,1012,44,63,1005,63,671,1106,0,677,4,657,1001,64,1,64,1002,64,2,64,109,4,1201,-7,0,63,1008,63,28,63,1005,63,699,4,683,1105,1,703,1001,64,1,64,1002,64,2,64,109,-6,1207,-3,36,63,1005,63,719,1105,1,725,4,709,1001,64,1,64,1002,64,2,64,109,-4,1201,6,0,63,1008,63,23,63,1005,63,745,1106,0,751,4,731,1001,64,1,64,1002,64,2,64,109,8,1202,-6,1,63,1008,63,20,63,1005,63,777,4,757,1001,64,1,64,1105,1,777,1002,64,2,64,109,5,1202,-5,1,63,1008,63,25,63,1005,63,801,1001,64,1,64,1105,1,803,4,783,1002,64,2,64,109,8,21101,47,0,-6,1008,1014,47,63,1005,63,829,4,809,1001,64,1,64,1106,0,829,1002,64,2,64,109,1,2106,0,6,1001,64,1,64,1106,0,847,4,835,1002,64,2,64,109,11,2106,0,-4,4,853,1105,1,865,1001,64,1,64,1002,64,2,64,109,-15,1206,3,883,4,871,1001,64,1,64,1106,0,883,1002,64,2,64,109,14,2105,1,-8,1105,1,901,4,889,1001,64,1,64,4,64,99,21102,1,27,1,21102,1,915,0,1106,0,922,21201,1,57564,1,204,1,99,109,3,1207,-2,3,63,1005,63,964,21201,-2,-1,1,21102,1,942,0,1105,1,922,22101,0,1,-1,21201,-2,-3,1,21101,957,0,0,1105,1,922,22201,1,-1,-2,1106,0,968,21202,-2,1,-2,109,-3,2106,0,0";

        @Test
        public void partOne(){
            Computer computer = Computer.initialise(DAY_PROGRAM_INPUT);
            long testModeInput = 1L;
            Long expected = 2316632620L;

            //Act
            computer.run(Arrays.asList(testModeInput));

            List<Long> outputs = computer.getOutputs();

            //Assert
            assertEquals(1, outputs.size());
            assertEquals(expected, outputs.get(0));
        }


        @Test
        public void partTwo(){
            Computer computer = Computer.initialise(DAY_PROGRAM_INPUT);
            long boostModeInput = 2L;
            Long expected = 78869L;

            //Act
            computer.setIsDebugMode(false);
            computer.run(Arrays.asList(boostModeInput));

            List<Long> outputs = computer.getOutputs();

            //Assert
            assertEquals(1, outputs.size());
            assertEquals(expected, outputs.get(0));
        }

        @Nested
        class UpgradeTests{

            @Test
            public void quineExample(){
                String program = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
                String expected = "[109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99]";

                Computer computer = Computer.initialise(program);

                //Act
                computer.run();

                assertEquals(expected, computer.getOutputs().toString());

            }

            @Test
            public void outputLargeNumber(){
                String program = "1102,34915192,34915192,7,4,7,99,0";
                long expected = 34915192L*34915192L;

                Computer computer = Computer.initialise(program);

                //Act
                computer.run();

                List<Long> outputs = computer.getOutputs();
                assertEquals(1, outputs.size());
                assertEquals(expected, outputs.get(0));

            }

        }


    }
}