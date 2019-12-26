package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.parameter.ImmediateMode;

public class MemoryChangingOperation implements Operation {

    private final Operation operation;

    MemoryChangingOperation(Operation operation){
        this.operation = operation;
    }

    @Override
    public Integer apply(Instruction operationInstruction, Memory memory) {
        int instructionOpCode = operationInstruction.getOperationCode();
        if(instructionOpCode == this.getOperationCode()){
            Integer operationResult = this.operation.apply(operationInstruction, memory);
            int outputPointer = operationInstruction.getParameterValue(getNumberOfParameters(), memory, ImmediateMode.getInstance());
            memory.setValueAtAddress(outputPointer, operationResult);
            return operationResult;
        }
        throw new IllegalArgumentException(
                String.format(
                        "Expected operation for code %d, but received instruction for OpCode %d",
                        this.getOperationCode(),
                        instructionOpCode));
    }

    @Override
    public int getOperationCode(){
        return this.operation.getOperationCode();
    }

    @Override
    public int getNumberOfParameters(){
        return this.operation.getNumberOfParameters();
    }

}
