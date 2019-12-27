package com.aoc.intcode.parameter;

import com.aoc.intcode.memory.Memory;

/**
 * Immediate parameter mode: returns the value held at the parameter address in {@link Memory}.
 */
public class ImmediateMode implements ParameterMode{

    private static ImmediateMode singleton;

    private ImmediateMode(){};

    @Override
    public long readValue(int parameterPointer, Memory memory) {
        return memory.getValueAtAddress(parameterPointer);
    }

    public static synchronized ImmediateMode getInstance(){
        if(singleton==null){
            singleton = new ImmediateMode();
        }
        return singleton;
    }
}
