package day8.instruction;

import day8.program.ProgramMemory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class InstructionParser {

    private static Map<String, Function<Integer, Instruction>> factories = new HashMap<>();

    static {
        factories.put("acc", Instructions::accumulate);
        factories.put("jmp", Instructions::jump);
        factories.put("nop", Instructions::noProcess);
    }

    public static Instruction parse(String instructionString) {
        String[] typeStepPair = instructionString.trim().split(" ");
        int steps = Integer.parseInt(typeStepPair[1]);
        String instructionKey = typeStepPair[0];
        if (factories.containsKey(instructionKey)) {
            return factories.get(instructionKey).apply(steps);
        }
        throw new IllegalArgumentException("Unknown instruction type <" + instructionKey + ">");
    }
}
