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
        int multiplicand = instruction.getParameter(MULTIPLICAND_INDEX, memory);
        int multiplier = instruction.getParameter(MULTIPLIER_INDEX, memory);
        Integer operationResult =  multiplicand*multiplier;

        int outputPointer = instruction.getParameterValue(getNumberOfParameters(), memory, ImmediateMode.getInstance());
        memory.setValueAtAddress(outputPointer, operationResult);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
