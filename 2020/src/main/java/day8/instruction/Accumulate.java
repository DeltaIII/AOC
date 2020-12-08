package day8.instruction;

import day8.Memory;
import java.util.function.Consumer;

public class Accumulate implements Consumer<Memory> {


    private final int toAccumulate;

    Accumulate(final int toAccumulate) {
        this.toAccumulate = toAccumulate;
    }

    @Override
    public synchronized void accept(final Memory memory) {
        memory.updateAccumulator(toAccumulate);
        memory.incrementAddress(1);
    }
}
