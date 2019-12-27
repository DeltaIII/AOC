package com.aoc.intcode.parameter;

import com.aoc.intcode.memory.Memory;

/**
 * Relative parameter mode: Parses the value at the parameter address as a relative parameter position, offset by the
 * relative base in {@link Memory}, then returns the the value held at the position in {@link Memory}.
 */
public class RelativeMode implements ParameterMode{

    private static RelativeMode singleton;

    private RelativeMode(){};

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
        int relativePosition = Math.toIntExact(memory.getValueAtAddress(parameterPointer));
        return memory.getRelativeBase()+relativePosition;
    }

    public static synchronized RelativeMode getInstance(){
        if(singleton==null){
            singleton = new RelativeMode();
        }
        return singleton;
    }
}
