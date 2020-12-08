package day8.instruction;

import day8.program.HaltReason;
import day8.program.ProgramMemory;
import java.util.Optional;

public class Accumulate implements Instruction {


    private final int toAccumulate;

    Accumulate(final int toAccumulate) {
        this.toAccumulate = toAccumulate;
    }

    @Override
    public int getSteps() {
        return toAccumulate;
    }

    @Override
    public synchronized Optional<HaltReason> apply(final ProgramMemory programMemory) {
        programMemory.updateAccumulator(toAccumulate);
        return programMemory.incrementAddress(1);
    }
}
