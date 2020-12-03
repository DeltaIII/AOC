package com.aoc.intcode.memory;

import com.aoc.intcode.parameter.ImmediateMode;
import com.aoc.intcode.parameter.ParameterMode;
import com.aoc.intcode.parameter.ParameterModeUtil;

/**
 * An instruction gives the operation (defined by an OpCode) and the parameters
 */
public class Instruction {

    private static final int OPERATION_CODE_DIVISOR = 100;
    private int instructionPointer;
    private final long instruction;

    private Instruction(int instructionPointer, long instruction){
        this.instructionPointer = instructionPointer;
        this.instruction = instruction;
    }

    public int getOperationCode(){
        return (int) (this.instruction % OPERATION_CODE_DIVISOR);
    }

    public long readFromMemory(int parameterNumber, Memory memory){
        ParameterMode readMode = getParameterMode(parameterNumber);
        return readValueFromMemory(parameterNumber, memory, readMode);
    }

    public void writeToMemory(int parameterNumber, Memory memory, Long newValue){
        ParameterMode writeMode = getParameterMode(parameterNumber);
        int parameterPointer = getParameterPointer(parameterNumber);
        writeMode.writeToAddress(parameterPointer, memory, newValue);
    }

    private long readValueFromMemory(int parameterNumber, Memory memory, ParameterMode readMode) {
        int parameterPointer = getParameterPointer(parameterNumber);
        return readMode.readValue(parameterPointer,memory);
    }

    private int getParameterPointer(int parameterNumber) {
        return this.instructionPointer+parameterNumber;
    }

    private ParameterMode getParameterMode(int parameterNumber) {
        int parameterInstruction = getParameterInstruction(parameterNumber);
        return ParameterModeUtil.getParameterMode(parameterInstruction);
    }

    private int getParameterInstruction(int parameterNumber) {
        int divisor = OPERATION_CODE_DIVISOR;
        for (int power=1; power<parameterNumber; power++){
            divisor*=10;
        }
        return (int) (this.instruction / divisor % 10);
    }

    public static Instruction getInstruction(int instructionPointer, Memory memory){
        long instruction = memory.getValueAtAddress(instructionPointer);
        if (instruction<0){
            throw new IllegalArgumentException(
                    String.format("Cannot parse instruction at pointer %d, it was negative.", instructionPointer));
        }
        return new Instruction(instructionPointer, instruction);
    }

}
