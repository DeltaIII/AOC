package day8.instruction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BootInstructionParser {

    private static Map<String, Function<Integer, BootInstruction>> factories = new HashMap<>();

    static {
        factories.put("acc", BootInstructions::accumulate);
        factories.put("jmp", BootInstructions::jump);
        factories.put("nop", BootInstructions::noProcess);
    }

    public static BootInstruction parse(String instructionString) {
        String[] typeStepPair = instructionString.trim().split(" ");
        int steps = Integer.parseInt(typeStepPair[1]);
        String instructionKey = typeStepPair[0];
        if (factories.containsKey(instructionKey)) {
            return factories.get(instructionKey).apply(steps);
        }
        throw new IllegalArgumentException("Unknown instruction type <" + instructionKey + ">");
    }
}
