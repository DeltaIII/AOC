package day8.instruction;

import day8.Memory;
import java.util.function.Consumer;

public class JumpPointer implements Consumer<Memory> {

    private final int toJump;

    JumpPointer(final int toJump) {
        this.toJump = toJump;
    }

    @Override
    public void accept(final Memory memory) {
        memory.incrementAddress(toJump);
    }
}
