package day8;

import static org.assertj.core.api.BDDAssertions.then;

import day8.instruction.Instruction;
import day8.instruction.InstructionParser;
import day8.program.ErrorDump;
import day8.program.HaltReason;
import day8.program.ProgramResult;
import day8.program.ProgramMemory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class ComputerTest {

    private static final String TEST_DATA = "day8/testData.txt";
    private static final String INPUT = "day8/input.txt";

    @Test
    void testAttemptBoot_testData() throws IOException {
        // Given
        final List<Instruction> instructions =
            InputFileReader.readObjects(TEST_DATA, InstructionParser::parse).collect(Collectors.toList());
        final Computer computer = new Computer();

        // When
        ProgramResult programResult = computer.runProgramToCompletion(instructions);

        // Then
        then(programResult).isNotNull();
        then(programResult.getHaltReason()).isSameAs(HaltReason.REPEATED_INSTRUCTION);
        then(programResult.getAccumulatorResult()).isEqualTo(5);
        Optional<ErrorDump> errorDump = programResult.getErrorDump();
        then(errorDump).isPresent();
        then(errorDump.get().getInstructionNumber()).isEqualTo(1);
    }

    @Test
    void testAttemptBoot_part1Input() throws IOException {
        // Given
        final List<Instruction> instructions =
            InputFileReader.readObjects(INPUT, InstructionParser::parse).collect(Collectors.toList());
        final Computer computer = new Computer();

        // When
        ProgramResult programResult = computer.runProgramToCompletion(instructions);


        // Then
        then(programResult).isNotNull();
        then(programResult.getHaltReason()).isSameAs(HaltReason.REPEATED_INSTRUCTION);
        then(programResult.getAccumulatorResult()).isEqualTo(1548);
        Optional<ErrorDump> errorDump = programResult.getErrorDump();
        then(errorDump).isPresent();
        then(errorDump.get().getInstructionNumber()).isEqualTo(448);
    }
}