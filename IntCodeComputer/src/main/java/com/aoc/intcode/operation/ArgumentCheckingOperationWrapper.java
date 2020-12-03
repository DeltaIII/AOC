package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;

public class ArgumentCheckingOperationWrapper implements Operation {

    private final Operation operation;

    ArgumentCheckingOperationWrapper(Operation operation){
        this.operation = operation;
    }

    @Override
    public void accept(Instruction operationInstruction, Memory memory) {
        int instructionOpCode = operationInstruction.getOperationCode();
        if(instructionOpCode == this.getOperationCode()){
            this.operation.accept(operationInstruction, memory);
            return;
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
