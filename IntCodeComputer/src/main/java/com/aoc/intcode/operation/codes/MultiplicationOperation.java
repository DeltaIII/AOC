package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;
import com.aoc.intcode.parameter.ImmediateMode;

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
    public void accept(Instruction instruction, Memory memory) {
        long multiplicand = instruction.readFromMemory(MULTIPLICAND_INDEX, memory);
        long multiplier = instruction.readFromMemory(MULTIPLIER_INDEX, memory);
        Long operationResult =  multiplicand*multiplier;
        instruction.writeToMemory(getNumberOfParameters(),memory,operationResult);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
