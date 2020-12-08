package day8.instruction;

import day8.program.HaltReason;
import day8.program.ProgramMemory;
import java.util.Optional;

public class EndProgram  implements Instruction {

    @Override
    public Optional<HaltReason> apply(final ProgramMemory programMemory) {
        return Optional.of(HaltReason.END_OF_PROGRAM);
    }
}
