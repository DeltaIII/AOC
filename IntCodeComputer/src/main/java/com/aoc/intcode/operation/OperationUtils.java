package com.aoc.intcode.operation;

import java.util.Map;
import java.util.TreeMap;

public class OperationUtils {

    private static final Map<Integer,Operation>  OPERATIONS = getOperations();
    private static final int TERMINATE_OPERATION_CODE = 99;

    public static boolean isTerminationInstruction(Instruction instruction){
        return TERMINATE_OPERATION_CODE == instruction.getOperationCode();
    }

    public static Operation getOperationForInstruction(Instruction instruction){
        return OPERATIONS.get(instruction.getOperationCode());
    }

    private static Map<Integer, Operation> getOperations() {
        Map<Integer,Operation> operations = new TreeMap<>();

        mapMemoryChangingOperation(operations, new AdditionOperation());
        mapMemoryChangingOperation(operations, new MultiplicationOperation());
        mapMemoryChangingOperation(operations, new InputOperation());

        OutputOperation outputOperation = new OutputOperation();
        operations.put(outputOperation.getOperationCode(), outputOperation);

        return operations;
    }

    private static final void mapMemoryChangingOperation(Map<Integer,Operation> operations, Operation operation){
        MemoryChangingOperation memoryChangingOperation = new MemoryChangingOperation(operation);
        operations.put(operation.getOperationCode(),memoryChangingOperation);
    }

}
