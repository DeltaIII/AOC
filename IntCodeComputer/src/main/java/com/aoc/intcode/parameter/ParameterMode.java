package com.aoc.intcode.parameter;

import com.aoc.intcode.memory.Memory;

public interface ParameterMode {

    /**
     * Read the parameter value from {@link Memory} given a specific parameter address
     * @param parameterPointer the address pointer to the {@link Memory} location of the parameter
     * @param memory the {@link Memory} to read
     * @return the parameter value read from the {@link Memory}. N.B. This may not be equal to the {@link Long} value
     * stored at that parameter address pointer in the {@link Memory}
     */
    long readValue(int parameterPointer, Memory memory);

    /**
     * Write to the address given by the parameter value in {@link Memory}. Note, this address may not be equal to value
     * stored at the parameter's address.
     * @param parameterPointer the address pointer to the {@link Memory} location of the parameter
     * @param memory the {@link Memory} to read
     * @param valueToWrite the value to write to Memory
     */
    void writeToAddress(int parameterPointer, Memory memory, Long valueToWrite);
}
