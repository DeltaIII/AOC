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
    public int readValue(int parameterPointer, Memory memory) {
        int position = memory.getValueAtAddress(parameterPointer);
        return memory.getValueAtAddress(position);
    }

    public static synchronized PositionMode getInstance(){
        if(singleton==null){
            singleton = new PositionMode();
        }
        return singleton;
    }
}
