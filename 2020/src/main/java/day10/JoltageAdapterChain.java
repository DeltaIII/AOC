package day10;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class JoltageAdapterChain {

    private final SortedSet<Integer> adapters = new TreeSet<>();
    private final Map<Integer, AtomicInteger> joltageDifferences = new HashMap<>();
    private int endOfChain = 0;

    public JoltageAdapterChain() {
        adapters.add(0);
    }

    public void addAdapter(final int adapterRating) {
        int difference = adapterRating - endOfChain;
        if (difference < 1 || difference > 3) {
            throw new IllegalArgumentException("Invalid adapter rating");
        }
        joltageDifferences.computeIfAbsent(difference, integer -> new AtomicInteger(0)).incrementAndGet();
        adapters.add(adapterRating);
        endOfChain = adapterRating;
    }

    public SortedSet<Integer> getAdapters() {
        return adapters;
    }

    public Map<Integer, AtomicInteger> getJoltageDifferences() {
        return joltageDifferences;
    }
}
