package day8.instruction;

import day8.Memory;
import java.util.function.Consumer;

public class NoOperation implements Consumer<Memory> {

    @Override
    public void accept(final Memory memory) {
        memory.incrementAddress(1);
    }
}
