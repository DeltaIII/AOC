package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;
import java.util.Scanner;

/**
 * Int code {@link MemoryChangingOperation} for an input
 */
public class InputOperation implements Operation {

    @Override
    public Integer apply(Instruction operationInstruction, Memory memory) {
        return memory.getInput();
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
