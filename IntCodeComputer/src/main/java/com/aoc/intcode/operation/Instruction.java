package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.parameter.ImmediateMode;
import com.aoc.intcode.parameter.ParameterMode;
import com.aoc.intcode.parameter.ParameterModeUtil;
import com.aoc.intcode.parameter.PositionMode;
import com.sun.istack.internal.NotNull;

/**
 * An instruction gives the operation (defined by an OpCode) and the parameters
 */
public class Instruction {

    private static final int OPERATION_CODE_DIVISOR = 100;
    private int instructionPointer;
    private final int instruction;

    private Instruction(int instructionPointer, int instruction){
        this.instructionPointer = instructionPointer;
        this.instruction = instruction;
    }

    public int getOperationCode(){
        return this.instruction % OPERATION_CODE_DIVISOR;
    }

    public int getParameter(int parameterNumber, Memory memory){
        ParameterMode parameterMode = getParameterMode(parameterNumber);
        return getParameterValue(parameterNumber, memory, parameterMode);
    }

    public int getParameterValue(int parameterNumber, Memory memory, ParameterMode parameterMode) {
        int parameterPointer = this.instructionPointer+parameterNumber;
        return parameterMode.readValue(parameterPointer,memory);
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
        return this.instruction / divisor % 10;
    }

    public static Instruction getInstruction(int instructionPointer, Memory memory){
        int instruction = memory.getValueAtAddress(instructionPointer);
        if (instruction<0){
            throw new IllegalArgumentException(
                    String.format("Cannot parse instruction at pointer %d, it was negative.", instructionPointer));
        }
        return new Instruction(instructionPointer, instruction);
    }

}
