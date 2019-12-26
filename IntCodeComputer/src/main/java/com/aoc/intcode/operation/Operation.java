package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;

import java.util.function.BiFunction;

public interface Operation extends BiFunction<Instruction, Memory, Integer> {

    public int getOperationCode();
    public int getNumberOfParameters();
}
