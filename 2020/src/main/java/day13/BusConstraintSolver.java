package day13;

import java.util.Iterator;
import java.util.SortedSet;

public class BusConstraintSolver {

    public static long getEarliestSolutionTime(final SortedSet<BusConstraint> constraints, long initialGuess) {
        long start = System.nanoTime();
        Iterator<BusConstraint> iterator = constraints.iterator();
        final BusConstraint firstConstraint = iterator.next();

        long solutionTimeStamp = getFirstGuess(firstConstraint, initialGuess);
        long increment = firstConstraint.getBusId();
        while (iterator.hasNext()) {
            BusConstraint constraint = iterator.next();
            int subIteration = 0;
            while (!constraint.isTimeValid(solutionTimeStamp)) {
                solutionTimeStamp += increment;
                subIteration++;
                if (subIteration % 100 == 0) {
                    System.out.println(String.format("Current Solution = %s; increment = %s; sub_iteration = %s; constraint = %s", solutionTimeStamp, increment, subIteration, constraint));
                }
            }
            increment = getLowestCommonMultiplier(increment, constraint.getBusId());
        }

        System.out.println("Time = " + ((System.nanoTime() - start)/1000000.0) + "ms");
        return solutionTimeStamp;
    }

    private static long getFirstGuess(final BusConstraint firstConstraint, final long initialGuess) {
        return initialGuess - (initialGuess % firstConstraint.getBusId()) - firstConstraint.getConstraintMinutes();
    }

    private static long getLowestCommonMultiplier(final long increment, final int busId) {
        return (increment * busId) / getGreatestCommonDivisor(increment, busId);
    }

    private static long getGreatestCommonDivisor(final long first, final int second) {
        long remainder = 0;
        long gcd = first;
        long divisor = second;
        // Euclidean algorithm for gcd
        do {
            remainder = gcd % divisor;
            gcd = divisor;
            divisor = remainder;
        } while (divisor != 0);
        return gcd;
    }
}
