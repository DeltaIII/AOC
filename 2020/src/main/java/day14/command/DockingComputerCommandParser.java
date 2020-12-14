package day14.command;

import day14.BitMask;
import day14.DockingComputer;
import day14.FloatingBitMask;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockingComputerCommandParser {

    private static final Pattern MEMORY_INDEX = Pattern.compile("mem\\[(\\d+)\\]");

    public static Consumer<DockingComputer> parseVersion1(final String commandString) {
        String[] commandValuePair = commandString.split(" = ");
        if ("mask".equals(commandValuePair[0])) {
            return parseUpdateMask(commandValuePair[1]);
        } else {
            return parseUpdateMemory(commandValuePair[0], commandValuePair[1]);
        }
    }

    public static Consumer<DockingComputer> parseVersion2(final String commandString) {
        String[] commandValuePair = commandString.split(" = ");
        if ("mask".equals(commandValuePair[0])) {
            return parseUpdateFloatingBitMask(commandValuePair[1]);
        } else {
            return parseUpdateFloatingMemoryValue(commandValuePair[0], commandValuePair[1]);
        }
    }

    private static Consumer<DockingComputer> parseUpdateMemory(final String key, final String value) {
        Matcher matcher = MEMORY_INDEX.matcher(key);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid memory update key!");
        }
        long index = Long.parseLong(matcher.group(1));
        return new UpdateMemory(index, Long.parseLong(value));
    }

    private static Consumer<DockingComputer> parseUpdateMask(final String maskString) {
        char[] maskInput = maskString.toCharArray();
        int index = maskInput.length - 1;
        final BitMask bitMask = new BitMask(maskInput.length);
        for (char c : maskInput) {
            switch (c) {
                case '1':
                    bitMask.overrideOne(index);
                    break;
                case '0':
                    bitMask.overrideZero(index);
                    break;
                case 'X':
                default:
                    break;
            }
            index --;
        }
        return new UpdateMask(bitMask);
    }

    private static Consumer<DockingComputer> parseUpdateFloatingMemoryValue(final String key, final String value) {
        Matcher matcher = MEMORY_INDEX.matcher(key);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid memory update key!");
        }
        long index = Long.parseLong(matcher.group(1));
        return new UpdateFloatingMemoryValue(index, Long.parseLong(value));
    }

    private static Consumer<DockingComputer> parseUpdateFloatingBitMask(final String maskString) {
        char[] maskInput = maskString.toCharArray();
        int index = maskInput.length - 1;
        final FloatingBitMask bitMask = new FloatingBitMask(maskInput.length);
        for (char c : maskInput) {
            switch (c) {
                case '1':
                    bitMask.overrideOne(index);
                    break;
                case '0':
                    bitMask.unset(index);
                    break;
                case 'X':
                    bitMask.addFloatingBit(index);
                    break;
                default:
                    break;
            }
            index --;
        }
        return new UpdateFloatingBitMask(bitMask);
    }

}
