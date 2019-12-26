package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.parameter.ImmediateMode;
import com.aoc.intcode.parameter.PositionMode;

import java.util.List;

public class OutputOperation implements Operation {

    private static final int OUTPUT_PARAMETER_NUMBER = 1;

    @Override
    public int getOperationCode() {
        return 4;
    }

    @Override
    public int getNumberOfParameters() {
        return 1;
    }

    @Override
    public Integer apply(Instruction instruction, Memory memory) {
        Integer valueToOutput = instruction.getParameter(OUTPUT_PARAMETER_NUMBER, memory);
        memory.addToOutputs(valueToOutput);
        return valueToOutput;
    }
}
