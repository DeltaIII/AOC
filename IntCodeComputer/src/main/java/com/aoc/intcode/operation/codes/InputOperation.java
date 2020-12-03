package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;

public class InputOperation implements Operation {

    @Override
    public void accept(Instruction operationInstruction, Memory memory) {
        Long operationResult =  memory.getInput();
        operationInstruction.writeToMemory(getNumberOfParameters(),memory,operationResult);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }

    @Override
    public int getOperationCode() {
        return 3;
    }

    @Override
    public int getNumberOfParameters() {
        return 1;
    }
}
