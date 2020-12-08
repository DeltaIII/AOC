package day8;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import day8.instruction.BootInstruction;
import day8.instruction.BootInstructionParser;
import day8.instruction.Instruction;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class ComputerTest {

    private static final String TEST_DATA = "day8/testData.txt";
    private static final String INPUT = "day8/input.txt";

    @Test
    void testAttemptBoot_testData() throws IOException {
        // Given
        final List<BootInstruction> instructions =
            InputFileReader.readObjects(TEST_DATA, BootInstructionParser::parse).collect(Collectors.toList());
        final Computer computer = new Computer(new Memory(instructions));

        // When
        BootResult bootResult = computer.attemptBoot();

        // Then
        then(bootResult).isNotNull();
        then(bootResult.isValidBoot()).isFalse();
        then(bootResult.isInstructionsComplete()).isFalse();
        then(computer.getMemory().getAccumulator()).isEqualTo(5);
        then(computer.getMemory().getPointerAddress()).isEqualTo(1);
    }

    @Test
    void testAttemptBoot_part1Input() throws IOException {
        // Given
        final List<BootInstruction> instructions =
            InputFileReader.readObjects(INPUT, BootInstructionParser::parse).collect(Collectors.toList());
        final Computer computer = new Computer(new Memory(instructions));

        // When
        BootResult bootResult = computer.attemptBoot();

        // Then
        then(bootResult).isNotNull();
        then(bootResult.isValidBoot()).isFalse();
        then(bootResult.isInstructionsComplete()).isFalse();
        then(computer.getMemory().getAccumulator()).isEqualTo(1548);
        then(computer.getMemory().getPointerAddress()).isEqualTo(448);
    }
}