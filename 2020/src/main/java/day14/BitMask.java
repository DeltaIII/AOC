package day14;

import java.util.BitSet;

public class BitMask {

    private final BitSet oneMask;
    private final BitSet zeroMask;

    public BitMask(final int maskSize) {
        this.oneMask = new BitSet(maskSize);
        this.zeroMask = new BitSet(maskSize);
        for (int index = 0; index < maskSize; index++) {
            this.zeroMask.set(index);
        }
    }

    public void unset(int index) {
        this.oneMask.clear(index);
        this.zeroMask.set(index);
    }

    public void overrideOne(int index) {
        unset(index);
        this.oneMask.set(index);
    }

    public void overrideZero(int index) {
        unset(index);
        this.zeroMask.clear(index);
    }

    public long mask(long input) {
        long oneOverride = input | toLong(oneMask);
        return oneOverride & toLong(zeroMask);
    }

    private static long toLong(final BitSet bitSet) {
        long value = 0;
        for (int i = bitSet.length() -1; i >= 0; i --) {
            value = value << 1;
            if (bitSet.get(i)) {
                value ++;
            }
        }
        return value;
    }
}
