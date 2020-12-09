package day9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class XmasCipher {

    private final int preambleLength;
    private final List<Long> currentPreamble = new LinkedList<>();

    public XmasCipher(final int preambleLength) {
        this.preambleLength = preambleLength;
    }

    public CipherResponse decode(List<Long> input) {
        if (input.size() <= this.preambleLength) {
            throw new IllegalArgumentException("Input too short!");
        }
        Iterator<Long> inputIterator = input.iterator();
        getPreamble(inputIterator);

        return decode(inputIterator);
    }

    private CipherResponse decode(final Iterator<Long> inputIterator) {
        int index = this.preambleLength;
        while (inputIterator.hasNext()) {
            final Long nextNumber = inputIterator.next();
            isNumberValid(nextNumber);
            if (!isNumberValid(nextNumber)) {
                return new CipherResponse(false, Optional.of(nextNumber), Optional.of(index));
            }
            updatePreamble(nextNumber);
            index++;
        }
        return new CipherResponse(true, Optional.empty(), Optional.empty());
    }

    private boolean isNumberValid(final Long nextNumber) {
        final Set<Long> cipherSet = new HashSet<>(currentPreamble);
        for (Long preambleNumber : cipherSet) {
            Long compliment = nextNumber - preambleNumber;
            if (cipherSet.contains(compliment)) {
                return true;
            }
        }
        return false;
    }

    private void getPreamble(final Iterator<Long> inputIterator) {
        for (int i = 0; i < this.preambleLength; i++) {
            currentPreamble.add(inputIterator.next());
        }
    }

    private void updatePreamble(final Long nextNumber) {
        this.currentPreamble.remove(0);
        this.currentPreamble.add(nextNumber);
    }
}
