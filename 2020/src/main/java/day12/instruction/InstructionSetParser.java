package day12.instruction;

import day12.Ferry;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionSetParser {

    public static final Map<String, BiConsumer<Ferry, Integer>> PART1_INSTRUCTION_MAP = new HashMap<>();

    static {
        PART1_INSTRUCTION_MAP.put("N", Instructions.moveNorth());
        PART1_INSTRUCTION_MAP.put("W", Instructions.moveWest());
        PART1_INSTRUCTION_MAP.put("S", Instructions.moveSouth());
        PART1_INSTRUCTION_MAP.put("E", Instructions.moveEast());
        PART1_INSTRUCTION_MAP.put("L", Instructions.turnLeft());
        PART1_INSTRUCTION_MAP.put("R", Instructions.turnRight());
        PART1_INSTRUCTION_MAP.put("F", Instructions.moveForward());
    }
    public static final Map<String, BiConsumer<Ferry, Integer>> PART2_INSTRUCTION_MAP = new HashMap<>();

    static {
        PART2_INSTRUCTION_MAP.put("N", Instructions.moveWaypointNorth());
        PART2_INSTRUCTION_MAP.put("W", Instructions.moveWaypointWest());
        PART2_INSTRUCTION_MAP.put("S", Instructions.moveWaypointSouth());
        PART2_INSTRUCTION_MAP.put("E", Instructions.moveWaypointEast());
        PART2_INSTRUCTION_MAP.put("L", Instructions.turnLeft());
        PART2_INSTRUCTION_MAP.put("R", Instructions.turnRight());
        PART2_INSTRUCTION_MAP.put("F", Instructions.moveForward());
    }

    private static Pattern PATTERN = Pattern.compile("(.)(\\d+)");

    private final Map<String, BiConsumer<Ferry, Integer>> instructionMap;

    public InstructionSetParser(final Map<String, BiConsumer<Ferry, Integer>> instructionMap) {
        this.instructionMap = instructionMap;
    }

    public InstructionSet parse(final String instructionString) {
        Matcher matcher = PATTERN.matcher(instructionString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid instruction string: " + instructionString);
        }
        String instructionCharacter = matcher.group(1);
        int increment = Integer.parseInt(matcher.group(2));

        if (instructionMap.containsKey(instructionCharacter)) {
            return new InstructionSet(instructionMap.get(instructionCharacter), increment);
        }
        throw new IllegalArgumentException("Invalid instruction character: " + instructionCharacter);
    }

}
