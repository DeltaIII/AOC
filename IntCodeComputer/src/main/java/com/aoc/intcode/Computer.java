package com.aoc.intcode;

import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.memory.MemoryParser;
import com.aoc.intcode.memory.Instruction;
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
    private static Logger logger;


    private Computer(Memory memory){
        this.memory = memory;
        if(logger==null) {
            logger = Logger.getLogger(Computer.class.getName());
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new MemoryStateFormatter());
            logger.addHandler(consoleHandler);
            logger.setUseParentHandlers(false);
        }
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
        Instruction currentInstruction = updateCurrentInstruction();
        while (!OperationUtils.isTerminationInstruction(currentInstruction) && !memory.isEndOfMemory()){
            Operation operation = OperationUtils.getOperationForInstruction(currentInstruction);
            operation.accept(currentInstruction, memory);
            printState(this.memory.getCurrentState());
            currentInstruction = updateCurrentInstruction();
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


    private Instruction updateCurrentInstruction() {
        return Instruction.getInstruction(memory.getPointerAddress(), memory);
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
