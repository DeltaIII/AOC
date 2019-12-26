package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;

public class MultiplicationOperation implements Operation {

    private static final int MULTIPLICAND_INDEX = 1;
    private static final int MULTIPLIER_INDEX = 2;


    @Override
    public int getOperationCode() {
        return 2;
    }

    @Override
    public int getNumberOfParameters() {
        return 3;
    }

    @Override
    public Integer apply(Instruction instruction, Memory memory) {
        int multiplicand = instruction.getParameter(MULTIPLICAND_INDEX, memory);
        int multiplier = instruction.getParameter(MULTIPLIER_INDEX, memory);
        return multiplicand*multiplier;
    }
}
