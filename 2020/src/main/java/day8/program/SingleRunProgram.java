package day8.program;

import day8.instruction.EndProgram;
import day8.instruction.Instruction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SingleRunProgram implements Program {

    private final List<Instruction> instructions;
    private final ProgramMemory memory;

    public SingleRunProgram(final List<Instruction> instructions) {
        this.instructions = new ArrayList<>(instructions);
        this.instructions.add(new EndProgram());
        this.memory = new ProgramMemory(this.instructions.size());
    }

    public ProgramResult run() {
        Set<Instruction> instructionsRun = new HashSet<>();
        Optional<HaltReason> haltReason = Optional.empty();
        Instruction currentInstruction = null;
        int instructionNumber = 0;
        while (!haltReason.isPresent()) {
            instructionNumber = memory.getPointerAddress();
            currentInstruction = instructions.get(instructionNumber);

            haltReason = instructionsRun.add(currentInstruction) ?
                currentInstruction.apply(memory) : Optional.of(HaltReason.REPEATED_INSTRUCTION);
        }

        Optional<ErrorDump> errorDump;
        if (haltReason.get() != HaltReason.END_OF_PROGRAM) {
            errorDump = Optional.of(new ErrorDump(currentInstruction, instructionNumber, memory));
        } else {
            errorDump = Optional.empty();
        }
        return new ProgramResult(haltReason.get(), memory.getAccumulator(), errorDump);
    }
}
