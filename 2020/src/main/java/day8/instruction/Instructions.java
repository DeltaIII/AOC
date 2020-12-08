package day8.instruction;

public class Instructions {

    public static Instruction accumulate(final int accumulateSteps) {
        return new Accumulate(accumulateSteps);
    }

    public static Instruction jump(final int jumpSteps){
        return new JumpPointer(jumpSteps);
    }

    public static Instruction noProcess(final int steps) {
        return new NoOperation(steps);
    }
}
