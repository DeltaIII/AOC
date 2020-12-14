package day14.command;

import day14.DockingComputer;
import java.util.function.Consumer;

public class UpdateMemory implements Consumer<DockingComputer> {

    private final long index;
    private final long value;

    public UpdateMemory(final long index, final long value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public void accept(final DockingComputer dockingComputer) {
        dockingComputer.setValue(index, value);
    }
}
