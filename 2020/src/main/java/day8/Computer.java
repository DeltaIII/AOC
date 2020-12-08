package day8;

import day8.instruction.Instruction;

public class Computer {

    private final Memory memory;

    public Computer(final Memory memory) {
        this.memory = memory;
    }

    public Memory getMemory() {
        return memory;
    }

    public BootResult attemptBoot() {
        boolean bootValid = true;
        boolean instructionsComplete = false;
        while (bootValid && !instructionsComplete) {
            Instruction nextInstruction = memory.getNextInstruction();
            if (nextInstruction.getTimesRun() > 0) {
                bootValid = false;
                break;
            }
            nextInstruction.accept(memory);
            instructionsComplete = memory.isEndOfMemory();
        }
        return new BootResult(bootValid, instructionsComplete);
    }

}
