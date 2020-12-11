package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

public class JoltageChainPossibilityCounter {

    private static final int MINIMUM_CHAIN_LENGTH = 3;
    private final int maxStepSize;

    public JoltageChainPossibilityCounter(final int maxStepSize) {
        this.maxStepSize = maxStepSize;
    }

    public long getNumberOfPossiblePermutations(final JoltageAdapterChain chain) {
        long start = System.nanoTime();
        SortedSet<Integer> adapters = chain.getAdapters();
        if (adapters.size() <= MINIMUM_CHAIN_LENGTH) {
            return 1;
        }

        List<Integer> adapterList = new ArrayList<>(adapters);

        int[] differences = new int[adapters.size() + 1];
        // Apply some boundary conditions to force subset consistency
        differences[0] = maxStepSize;
        differences[adapters.size()] = maxStepSize;

        for (int index = 0; index < adapters.size() - 1 ; index++) {
            differences[index + 1] = adapterList.get(index + 1) - adapterList.get(index);
        }

        Map<List<Integer>, Long> subsetCombinations = new HashMap<>();
        List<Integer> currentSubSet = new ArrayList<>();
        long totalPermutations = 1;
        for (int index = 0; index < differences.length; index++) {
            int difference = differences[index];
            if (difference >= maxStepSize || index == differences.length - 1) {

                // Use existing subset calculation if possible
                totalPermutations *=
                    subsetCombinations.computeIfAbsent(
                    currentSubSet,
                    this::getNumberOfPossibleSubsetPermutations);

                currentSubSet = new ArrayList<>();
            } else {
                currentSubSet.add(difference);
            }
        }
        System.out.println("Time = " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime()-start) + "micro seconds");
        return totalPermutations;
    }

    // Generalised for any step size, not just step size 1
    private Long getNumberOfPossibleSubsetPermutations(final List<Integer> diffSubset) {

        long[] possibleRoutes = new long[diffSubset.size() + 1];
        possibleRoutes[0] = 1;
        for (int index = 1; index < possibleRoutes.length; index++) {
            int remaining = maxStepSize;
            int nextIndex = index;
            while (remaining > 0 && nextIndex < possibleRoutes.length) {
                remaining -= diffSubset.get(nextIndex - 1);
                possibleRoutes[nextIndex] += possibleRoutes[index - 1];
                nextIndex++;
            }
        }
        return possibleRoutes[possibleRoutes.length - 1];
    }

    /**
     * Based on Jamie Kelly's solution in Rust. Very neat, but roughly 5-10 times slower using the puzzle input
     */
    public long getNumberOfPossiblePermutationsDynamicProgramming(final JoltageAdapterChain chain) {
        long start = System.nanoTime();
        SortedSet<Integer> adapters = chain.getAdapters();
        if (adapters.size() <= MINIMUM_CHAIN_LENGTH) {
            return 1;
        }

        Iterator<Integer> inputIterator = adapters.iterator();
        Map<Integer, Long> subchainPossibleSteps = new HashMap<>();
        subchainPossibleSteps.put(inputIterator.next(), 1L);
        while (inputIterator.hasNext()) {
            Integer nextAdapter = inputIterator.next();
            pruneDistantSteps(subchainPossibleSteps, nextAdapter);
            subchainPossibleSteps.put(nextAdapter, subchainPossibleSteps.values().stream().reduce(0L, Long::sum));
        }
        System.out.println("Time 2 = " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime()-start) + "micro seconds");
        return subchainPossibleSteps.get(adapters.last());
    }

    private void pruneDistantSteps(final Map<Integer, Long> subchainPossibleSteps,
                                   final Integer nextAdapter) {
        subchainPossibleSteps.keySet()
            .removeIf(otherAdapter ->  nextAdapter > otherAdapter + maxStepSize);

    }

}
