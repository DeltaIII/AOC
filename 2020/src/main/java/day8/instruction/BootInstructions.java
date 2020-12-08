package day8.instruction;

public class BootInstructions {

    public static BootInstruction accumulate(final int accumulateSteps) {
        return new BootInstruction(new Accumulate(accumulateSteps));
    }

    public static BootInstruction jump(final int jumpSteps){
        return new BootInstruction(new JumpPointer(jumpSteps));
    }

    public static BootInstruction noProcess(final int unused) {
        return new BootInstruction(new NoOperation());
    }
}
