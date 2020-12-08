package day8;

import day8.instruction.Instruction;
import day8.instruction.Instructions;
import day8.instruction.JumpPointer;
import day8.instruction.NoOperation;
import day8.program.HaltReason;
import day8.program.ProgramResult;
import java.util.ArrayList;
import java.util.List;

public class JumpInstructionCorruptionFixer {

    public static ProgramResult getCorrectedResult(final List<Instruction> originalInstructions) {
        Computer computer = new Computer();
        ProgramResult programResult = computer.runProgramToCompletion(originalInstructions);
        if (isProgramSuccessful(programResult)) {
            throw new IllegalArgumentException("ProgramMemory already valid.");
        }

        for (int index = 0; index < originalInstructions.size(); index++) {
            Instruction instruction = originalInstructions.get(index);
            if (instruction instanceof JumpPointer || instruction instanceof NoOperation) {
                programResult = attemptSubstitution(originalInstructions, index, instruction, computer);
                if (isProgramSuccessful(programResult)) {
                    return programResult;
                }
            }
        }

        throw new IllegalArgumentException("No valid answer found");
    }

    private static ProgramResult attemptSubstitution(final List<Instruction> originalInstructions,
                                                     final int index,
                                                     final Instruction instructionToSubstitute,
                                                     final Computer computer) {
        final List<Instruction> changedInstructions = new ArrayList<>(originalInstructions);
        if (instructionToSubstitute instanceof JumpPointer) {
            changedInstructions.set(index, Instructions.noProcess(instructionToSubstitute.getSteps()));
        } else {
            changedInstructions.set(index, Instructions.jump(instructionToSubstitute.getSteps()));
        }
        return computer.runProgramToCompletion(changedInstructions);
    }

    private static boolean isProgramSuccessful(final ProgramResult programResult) {
        return programResult.getHaltReason() == HaltReason.END_OF_PROGRAM;
    }

}
