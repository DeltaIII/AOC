package day8;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import day8.instruction.BootInstruction;
import day8.instruction.BootInstructionParser;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class BootInstructionFixerTest {

    private static final String TEST_DATA = "day8/testData.txt";
    private static final String INPUT = "day8/input.txt";

    @Test
    void testGetFixedInstructions_testData() throws IOException {
        // Given
        final List<BootInstruction> instructions =
            InputFileReader.readObjects(TEST_DATA, BootInstructionParser::parse).collect(Collectors.toList());

        // When
        Memory fixedMemory = BootInstructionFixer.getFixedInstructions(instructions);

        // Then
        then(fixedMemory).isNotNull();
        then(fixedMemory.isEndOfMemory()).isTrue();
        then(fixedMemory.getAccumulator()).isEqualTo(8);
    }
    @Test
    void testGetFixedInstructions_part2Input() throws IOException {
        // Given
        final List<BootInstruction> instructions =
            InputFileReader.readObjects(INPUT, BootInstructionParser::parse).collect(Collectors.toList());

        // When
        Memory fixedMemory = BootInstructionFixer.getFixedInstructions(instructions);

        // Then
        then(fixedMemory).isNotNull();
        then(fixedMemory.isEndOfMemory()).isTrue();
        then(fixedMemory.getAccumulator()).isEqualTo(1375);
    }
}