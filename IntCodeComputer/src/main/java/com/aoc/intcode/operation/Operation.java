package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;

import java.util.function.BiConsumer;

public interface Operation extends BiConsumer<Instruction, Memory> {

    public int getOperationCode();
    public int getNumberOfParameters();
}
