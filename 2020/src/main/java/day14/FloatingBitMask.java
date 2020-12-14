package day14;

import java.util.HashSet;
import java.util.Set;

public class FloatingBitMask extends BitMask {
    
    private final Set<Integer> floatingBits = new HashSet<>();
    private final BitMask floatingBitModifier;
    
    public FloatingBitMask(final int maskSize) {
        super(maskSize);
        floatingBitModifier = new BitMask(maskSize);
    }

    @Override
    public void unset(final int index) {
        super.unset(index);
        floatingBits.remove(index);
    }
    
    public void addFloatingBit(final int index) {
        unset(index);
        floatingBits.add(index);
    }
    
    public Set<Long> getAllPossibleResults(final long input) {
        long baseMaskedValue = mask(input);
        Set<Long> possibleValues = getPossibleValues(baseMaskedValue, this.floatingBits);
        return possibleValues;
    }

    private Set<Long> getPossibleValues(final long input, final Set<Integer> floatingBits) {
        Set<Long> possibleValues = new HashSet<>();
        if (floatingBits.size() > 0) {
            final Set<Integer> remainingBits = new HashSet<>(floatingBits);
            Integer bit = floatingBits.iterator().next();
            remainingBits.remove(bit);
            this.floatingBitModifier.overrideOne(bit);
            possibleValues.addAll(getPossibleValues(input, remainingBits));
            this.floatingBitModifier.overrideZero(bit);
            possibleValues.addAll(getPossibleValues(input, remainingBits));
            this.floatingBitModifier.unset(bit);
        } else {
            possibleValues.add(this.floatingBitModifier.mask(input));
        }
        return possibleValues;
    }


}
