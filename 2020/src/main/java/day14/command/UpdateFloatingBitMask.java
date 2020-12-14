package day14.command;

import day14.DockingComputer;
import day14.FloatingBitMask;
import java.util.function.Consumer;

public class UpdateFloatingBitMask implements Consumer<DockingComputer> {

    private final FloatingBitMask bitMask;

    public UpdateFloatingBitMask(final FloatingBitMask bitMask) {
        this.bitMask = bitMask;
    }

    @Override
    public void accept(final DockingComputer dockingComputer) {
        dockingComputer.setFloatingBitMask(bitMask);
    }

}
