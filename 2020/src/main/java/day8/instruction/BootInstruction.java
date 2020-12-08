package day8.instruction;

import day8.Memory;
import java.util.function.Consumer;

public class BootInstruction implements Instruction{

    private final Consumer<Memory> memoryUpdater;
    private int timesRun;

    BootInstruction(final Consumer<Memory> memoryUpdater) {
        this.memoryUpdater = memoryUpdater;
    }

    public Consumer<Memory> getMemoryUpdater() {
        return memoryUpdater;
    }

    @Override
    public int getTimesRun() {
        return this.timesRun;
    }

    @Override
    public void reset() {
        this.timesRun = 0;
    }

    @Override
    public void accept(final Memory memory) {
        memoryUpdater.accept(memory);
        timesRun++;
    }

}
