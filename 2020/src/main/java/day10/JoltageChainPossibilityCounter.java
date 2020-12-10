package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
                Long possiblePermutations = subsetCombinations.computeIfAbsent(
                    currentSubSet,
                    this::getNumberOfPossibleSubsetPermutations);
                totalPermutations *= possiblePermutations;

                currentSubSet = new ArrayList<>();
            } else {
                currentSubSet.add(difference);
            }
        }
        System.out.println("Time = " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime()-start) + "micro seconds");
        return totalPermutations;
    }

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


}
