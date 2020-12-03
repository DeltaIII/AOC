package com.aoc.intcode.operation;

import com.aoc.intcode.memory.Instruction;
import com.aoc.intcode.memory.Memory;
import com.aoc.intcode.operation.codes.AdditionOperation;
import com.aoc.intcode.operation.codes.EqualsOperation;
import com.aoc.intcode.operation.codes.IncrementRelativeBaseOperation;
import com.aoc.intcode.operation.codes.InputOperation;
import com.aoc.intcode.operation.codes.JumpIfFalseOperation;
import com.aoc.intcode.operation.codes.JumpIfTrueOperation;
import com.aoc.intcode.operation.codes.LessThanOperation;
import com.aoc.intcode.operation.codes.MultiplicationOperation;
import com.aoc.intcode.operation.codes.OutputOperation;

import java.util.Map;
import java.util.TreeMap;

public class OperationUtils {

    private static final Map<Integer,Operation>  OPERATIONS = getOperations();
    private static final int TERMINATE_OPERATION_CODE = 99;

    public static boolean isTerminationInstruction(Instruction instruction){
        return TERMINATE_OPERATION_CODE == instruction.getOperationCode();
    }

    public static Operation getOperationForInstruction(Instruction instruction){
        Integer operationCode = instruction.getOperationCode();
        if(OPERATIONS.containsKey(operationCode)){
            return OPERATIONS.get(operationCode);
        }
        throw  new IllegalArgumentException("Unknown op code: "+ operationCode);
    }

    private static Map<Integer, Operation> getOperations() {
        Map<Integer,Operation> operations = new TreeMap<>();

        wrapOperationToCheckArguments(operations, new AdditionOperation());
        wrapOperationToCheckArguments(operations, new MultiplicationOperation());
        wrapOperationToCheckArguments(operations, new InputOperation());
        wrapOperationToCheckArguments(operations, new OutputOperation());
        wrapOperationToCheckArguments(operations, new JumpIfTrueOperation());
        wrapOperationToCheckArguments(operations, new JumpIfFalseOperation());
        wrapOperationToCheckArguments(operations, new LessThanOperation());
        wrapOperationToCheckArguments(operations, new EqualsOperation());
        wrapOperationToCheckArguments(operations, new IncrementRelativeBaseOperation());

        return operations;
    }

    private static final void wrapOperationToCheckArguments(Map<Integer,Operation> operations, Operation operation){
        ArgumentCheckingOperationWrapper memoryChangingOperation = new ArgumentCheckingOperationWrapper(operation);
        operations.put(operation.getOperationCode(),memoryChangingOperation);
    }

}
