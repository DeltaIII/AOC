package day14;

import java.util.HashMap;
import java.util.Map;

public class DockingComputer {

    private final Map<Long, Long> memory = new HashMap<>();
    private BitMask bitMask;
    private FloatingBitMask floatingBitMask;

    public DockingComputer(final int numberOfBits) {
        bitMask = new BitMask(numberOfBits);
        floatingBitMask = new FloatingBitMask(numberOfBits);
    }

    public void setFloatingMemoryValue(final long initialIndex, final long value) {
        for (Long index : this.floatingBitMask.getAllPossibleResults(initialIndex)) {
            this.memory.put(index, value);
        }
    }

    public void setValue(final long index, final long value) {
        this.memory.put(index, bitMask.mask(value));
    }

    public void setBitMask(final BitMask bitMask) {
        this.bitMask = bitMask;
    }

    public void setFloatingBitMask(final FloatingBitMask floatingBitMask) {
        this.floatingBitMask = floatingBitMask;
    }

    public Map<Long, Long> getMemory() {
        return memory;
    }
}
