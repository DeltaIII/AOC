package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.ArgumentCheckingOperationWrapper;
import com.aoc.intcode.operation.Operation;

public class IncrementRelativeBaseOperation implements Operation {

    @Override
    public void accept(Instruction operationInstruction, Memory memory) {
        int relativeBaseChange = Math.toIntExact(operationInstruction.readFromMemory(getNumberOfParameters(), memory));
        memory.addToRelativeBase(relativeBaseChange);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }

    @Override
    public int getOperationCode() {
        return 9;
    }

    @Override
    public int getNumberOfParameters() {
        return 1;
    }
}
