package com.aoc.intcode.parameter;

import com.aoc.intcode.memory.Memory;
import com.sun.istack.internal.NotNull;

@FunctionalInterface
public interface ParameterMode {

    /**
     * Read the parameter value from {@link Memory} given a specific parameter address
     * @param parameterPointer the address pointer to the {@link Memory} location of the parameter
     * @param memory the {@link Memory} to read
     * @return the parameter value read from the {@link Memory}. N.B. This may not be equal to the {@link Integer} value
     * stored at that parameter address pointer in the {@link Memory}
     */
    long readValue(int parameterPointer, @NotNull Memory memory);
}
