package day8.instruction;

import day8.program.ProgramMemory;

public class Instructions {

    public static Instruction accumulate(final int accumulateSteps) {
        return new Accumulate(accumulateSteps);
    }

    public static Instruction jump(final int jumpSteps){
        return new JumpPointer(jumpSteps);
    }

    public static Instruction noProcess(final int unused) {
        return new NoOperation();
    }
}
