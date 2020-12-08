package day8.instruction;

import day8.program.HaltReason;
import day8.program.ProgramMemory;
import java.util.Optional;

public class NoOperation implements Instruction {

    private final int steps;

    public NoOperation(final int steps) {
        this.steps = steps;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public Optional<HaltReason> apply(final ProgramMemory programMemory) {
        return programMemory.incrementAddress(1);
    }
}
