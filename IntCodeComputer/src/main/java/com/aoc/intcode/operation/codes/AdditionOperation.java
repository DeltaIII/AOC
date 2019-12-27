package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;
import com.aoc.intcode.parameter.ImmediateMode;

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
    public void accept(Instruction instruction, Memory memory) {
        long augend = instruction.readFromMemory(AUGEND_INDEX, memory);
        long addend = instruction.readFromMemory(AUDDEND_INDEX, memory);
        Long operationResult =  augend+addend;
        instruction.writeToMemory(getNumberOfParameters(),memory,operationResult);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
