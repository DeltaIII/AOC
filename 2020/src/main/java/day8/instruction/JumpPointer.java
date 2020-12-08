package day8.instruction;

import day8.program.HaltReason;
import day8.program.ProgramMemory;
import java.util.Optional;

public class JumpPointer implements Instruction {

    private final int toJump;

    JumpPointer(final int toJump) {
        this.toJump = toJump;
    }

    @Override
    public Optional<HaltReason> apply(final ProgramMemory programMemory) {
        return programMemory.incrementAddress(toJump);
    }
}
