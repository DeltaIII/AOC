package day8;

import static org.assertj.core.api.BDDAssertions.then;

import day8.instruction.Instruction;
import day8.instruction.InstructionParser;
import day8.program.HaltReason;
import day8.program.ProgramResult;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class JumpInstructionFixerTest {

    private static final String TEST_DATA = "day8/testData.txt";
    private static final String INPUT = "day8/input.txt";

    @Test
    void testGetFixedInstructions_testData() throws IOException {
        // Given
        final List<Instruction> instructions =
            InputFileReader.readObjects(TEST_DATA, InstructionParser::parse).collect(Collectors.toList());

        // When
        ProgramResult programResult = JumpInstructionFixer.getFixedInstructions(instructions);

        // Then
        then(programResult).isNotNull();
        then(programResult.getHaltReason()).isSameAs(HaltReason.END_OF_PROGRAM);
        then(programResult.getAccumulatorResult()).isEqualTo(8);
    }
    @Test
    void testGetFixedInstructions_part2Input() throws IOException {
        // Given
        final List<Instruction> instructions =
            InputFileReader.readObjects(INPUT, InstructionParser::parse).collect(Collectors.toList());

        // When
        ProgramResult programResult = JumpInstructionFixer.getFixedInstructions(instructions);

        // Then
        then(programResult).isNotNull();
        then(programResult.getHaltReason()).isSameAs(HaltReason.END_OF_PROGRAM);
        then(programResult.getAccumulatorResult()).isEqualTo(1375);
    }
}