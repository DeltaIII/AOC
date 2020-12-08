package day8.instruction;

import day8.program.HaltReason;
import day8.program.ProgramMemory;
import java.util.Optional;
import java.util.function.Function;

public interface Instruction  extends Function<ProgramMemory, Optional<HaltReason>> {
}
