package com.aoc.intcode;

import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.memory.MemoryParser;
import com.aoc.intcode.operation.Instruction;
import com.aoc.intcode.operation.Operation;
import com.aoc.intcode.operation.OperationUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Computer {

    private final Memory memory;
    private final Logger logger;


    private Computer(Memory memory){
        this.memory = memory;
        this.logger =  Logger.getLogger(Computer.class.getName());
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new MemoryStateFormatter());
        this.logger.addHandler(consoleHandler);
        this.logger.setUseParentHandlers(false);
    }

    public List<Integer> run(List<Integer> inputs){
        for (Integer input : inputs) {
            this.memory.addToInputs(input);
        }
        runToTermination();
        return memory.getCurrentState();
    }

    public List<Integer> run() {
        return run(new ArrayList<>());
    }

    private void runToTermination() {
        logger.log(Level.INFO, "<<BEGIN>>");
        outputIndexes();
        printState(this.memory.getCurrentState());
        int instructionPointer = 0;
        Instruction currentInstruction = updateCurrentInstruction(instructionPointer);
        while (!OperationUtils.isTerminationInstruction(currentInstruction) && instructionPointer<memory.size()){
            Operation operation = OperationUtils.getOperationForInstruction(currentInstruction);
            operation.apply(currentInstruction, memory);
            printState(this.memory.getCurrentState());
            instructionPointer = updateInstructionPointer(instructionPointer, operation);
            currentInstruction = updateCurrentInstruction(instructionPointer);
        }
        logger.log(Level.INFO,"<<FINI>>");
    }

    private void outputIndexes() {
        List<Integer> indexes = new LinkedList<>();
        for (int index = 0; index<this.memory.size(); index++){
            indexes.add(index);
        }
        printState(indexes);
    }

    private void printState(List<Integer> state){
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer value : state) {
            stringBuilder.append(String.format(" %5d,", value));
        }
        logger.log(Level.INFO, stringBuilder.toString());

    }

    private int updateInstructionPointer(int instructionPointer, Operation operation) {
        int numberOfIntegersParsed = operation.getNumberOfParameters()+1;
        return instructionPointer + numberOfIntegersParsed;
    }

    private Instruction updateCurrentInstruction(int instructionPointer) {
        return Instruction.getInstruction(instructionPointer, memory);
    }

    public List<Integer> getOutputs(){
        return this.memory.getOutputs();
    }

    public static final Computer initialise(String intCodeInput){
        Memory memory = MemoryParser.parse(intCodeInput);
        return new Computer(memory);
    }

    private static class MemoryStateFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            StringBuffer sb = new StringBuffer();
            sb.append(record.getLevel()+":     ");
            sb.append(record.getMessage());
            sb.append("\n");
            return sb.toString();
        }

    }

}
