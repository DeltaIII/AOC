package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;

public class LessThanOperation implements Operation {

    private static final int COMPARED_INDEX = 1;
    private static final int REFERENCE_INDEX = 2;

    @Override
    public int getOperationCode() {
        return 7;
    }

    @Override
    public int getNumberOfParameters() {
        return 3;
    }

    @Override
    public void accept(Instruction instruction, Memory memory) {
        long elementToCompare = instruction.readFromMemory(COMPARED_INDEX, memory);
        long referenceElement = instruction.readFromMemory(REFERENCE_INDEX, memory);

        Long valueToWrite;
        if(elementToCompare<referenceElement){
            valueToWrite = 1l;
        } else {
            valueToWrite = 0l;
        }

        instruction.writeToMemory(getNumberOfParameters(),memory,valueToWrite);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
