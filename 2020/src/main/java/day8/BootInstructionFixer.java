package day8;

import day8.instruction.BootInstruction;
import day8.instruction.BootInstructions;
import day8.instruction.JumpPointer;
import java.util.LinkedList;
import java.util.List;

public class BootInstructionFixer {

    public static Memory getFixedInstructions(final List<BootInstruction> originalInstructions) {
        AttemptedBootResult attemptedBootResult = attemptBoot(originalInstructions);
        if (attemptedBootResult.getBootResult().isValidBoot()) {
            throw new IllegalArgumentException("Memory already valid.");
        }

        for (int index = 0; index < originalInstructions.size(); index++) {
            BootInstruction instruction = originalInstructions.get(index);
            if (instruction.getMemoryUpdater() instanceof JumpPointer) {
                attemptedBootResult = attemptSubstitution(originalInstructions, index);
                if (attemptedBootResult.getBootResult().isValidBoot()) {
                    return attemptedBootResult.getMemory();
                }
            }
        }

        throw new IllegalArgumentException("No valid answer found");
    }

    private static AttemptedBootResult attemptSubstitution(final List<BootInstruction> originalInstructions,
                                                           final int index) {
        for (BootInstruction bootInstruction : originalInstructions) {
            bootInstruction.reset();
        }

        final List<BootInstruction> changedInstructions = new LinkedList<>();
        changedInstructions.addAll(originalInstructions);
        changedInstructions.set(index, BootInstructions.noProcess(0));
        return attemptBoot(changedInstructions);
    }

    private static AttemptedBootResult attemptBoot(final List<BootInstruction> instructions) {
        Computer computer = new Computer(new Memory(instructions));
        BootResult bootResult = computer.attemptBoot();
        return new AttemptedBootResult(bootResult, computer.getMemory());
    }

    private static class AttemptedBootResult {
        private final BootResult bootResult;
        private final Memory memory;

        public AttemptedBootResult(final BootResult bootResult, final Memory memory) {
            this.bootResult = bootResult;
            this.memory = memory;
        }

        public BootResult getBootResult() {
            return bootResult;
        }

        public Memory getMemory() {
            return memory;
        }
    }

}
