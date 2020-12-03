package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;

public class EqualsOperation implements Operation {

    private static final int COMPARED_INDEX = 1;
    private static final int REFERENCE_INDEX = 2;

    @Override
    public int getOperationCode() {
        return 8;
    }

    @Override
    public int getNumberOfParameters() {
        return 3;
    }

    @Override
    public void accept(Instruction instruction, Memory memory) {
        long elementToCompare = instruction.readFromMemory(COMPARED_INDEX, memory);
        long referenceElement = instruction.readFromMemory(REFERENCE_INDEX, memory);

        long valueToWrite;
        if(elementToCompare == referenceElement){
            valueToWrite = 1L;
        }else {
            valueToWrite = 0L;
        }
        
        instruction.writeToMemory(getNumberOfParameters(),memory,valueToWrite);
        memory.incrementAddress(this.getNumberOfParameters()+1);
    }
}
