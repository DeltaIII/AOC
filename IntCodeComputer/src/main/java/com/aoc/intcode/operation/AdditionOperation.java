package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;

public class AdditionOperation implements Operation {

    private static final int AUGEND_INDEX = 1;
    private static final int AUDDEND_INDEX = 2;


    @Override
    public int getOperationCode() {
        return 1;
    }

    @Override
    public int getNumberOfParameters() {
        return 3;
    }

    @Override
    public Integer apply(Instruction instruction, Memory memory) {
        int augend = instruction.getParameter(AUGEND_INDEX, memory);
        int addend = instruction.getParameter(AUDDEND_INDEX, memory);
        return augend+addend;
    }
}
