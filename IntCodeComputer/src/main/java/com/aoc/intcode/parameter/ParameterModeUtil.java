package com.aoc.intcode.parameter;

public class ParameterModeUtil {

    public static ParameterMode getParameterMode(int parameterModeInstruction){
        switch (parameterModeInstruction){
            case 0:
                return PositionMode.getInstance();
            case 1:
                return ImmediateMode.getInstance();
            case 2:
                return RelativeMode.getInstance();
            default:
                throw new IllegalArgumentException(
                        String.format("Unknown parameter mode for value %d",parameterModeInstruction));
        }
    }
}
