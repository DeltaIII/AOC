package day10;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoltageAdapterChainBuilder {

    public static JoltageAdapterChain build(final Stream<Integer> adapterRatings) {
        final SortedSet<Integer> sortedAdapters = adapterRatings.collect(Collectors.toCollection(TreeSet::new));
        final JoltageAdapterChain chain = new JoltageAdapterChain();
        sortedAdapters.forEach(chain::addAdapter);
        chain.addAdapter(getTerminus(sortedAdapters));
        return chain;
    }

    private static int getTerminus(final SortedSet<Integer> sortedAdapters) {
        return sortedAdapters.last() + 3;
    }
}
