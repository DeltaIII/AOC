package day14.command;

import day14.BitMask;
import day14.DockingComputer;
import java.util.function.Consumer;

public class UpdateMask implements Consumer<DockingComputer> {

    private final BitMask bitMask;

    public UpdateMask(final BitMask bitMask) {
        this.bitMask = bitMask;
    }

    @Override
    public void accept(final DockingComputer dockingComputer) {
        dockingComputer.setBitMask(bitMask);
    }
}
