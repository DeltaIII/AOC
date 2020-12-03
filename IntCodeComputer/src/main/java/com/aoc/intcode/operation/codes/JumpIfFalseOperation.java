package com.aoc.intcode.operation.codes;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.Operation;

public class JumpIfFalseOperation implements Operation {

    private static final int FLAG_INDEX = 1;
    private static final int NEW_POINTER_INDEX = 2;

    @Override
    public int getOperationCode() {
        return 6;
    }

    @Override
    public int getNumberOfParameters() {
        return 2;
    }

    @Override
    public void accept(Instruction instruction, Memory memory) {
        if (!flagParameterIsTrue(instruction, memory)){
            long newPointerAddress = instruction.readFromMemory(NEW_POINTER_INDEX, memory);
            memory.setPointerAddress(Math.toIntExact(newPointerAddress));
        } else {
            memory.incrementAddress(this.getNumberOfParameters()+1);
        }
    }

    private boolean flagParameterIsTrue(Instruction instruction, Memory memory) {
        long flag = instruction.readFromMemory(FLAG_INDEX, memory);
        return 0 != flag;
    }
}
