package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;

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
    public void accept(Instruction instruction, Memory memory) {
        Long valueToOutput = instruction.readFromMemory(OUTPUT_PARAMETER_NUMBER, memory);
        memory.addToOutputs(valueToOutput);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
