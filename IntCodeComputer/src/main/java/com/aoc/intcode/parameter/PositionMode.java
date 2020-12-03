package com.aoc.intcode.parameter;

import com.aoc.intcode.memory.Memory;

/**
 * Position parameter mode: Parses the value at the parameter address as the parameter's position, then returns the
 * the value held at the position in {@link Memory}.
 */
public class PositionMode implements ParameterMode{

    private static PositionMode singleton;

    private PositionMode(){};

    @Override
    public long readValue(int parameterPointer, Memory memory) {
        int address = getAddress(parameterPointer, memory);
        return memory.getValueAtAddress(address);
    }
    @Override
    public void writeToAddress(int parameterPointer, Memory memory, Long valueToWrite) {
        int address = getAddress(parameterPointer, memory);
        memory.setValueAtAddress(address, valueToWrite);
    }

    private int getAddress(int parameterPointer, Memory memory) {
        return Math.toIntExact(memory.getValueAtAddress(parameterPointer));
    }

    public static synchronized PositionMode getInstance(){
        if(singleton==null){
            singleton = new PositionMode();
        }
        return singleton;
    }
}
