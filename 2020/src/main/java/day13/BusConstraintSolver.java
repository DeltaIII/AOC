package day13;

import java.util.Iterator;
import java.util.SortedSet;

public class BusConstraintSolver {

    public static long getEarliestSolutionTime_iterativeSearch(final SortedSet<BusConstraint> constraints, long initialGuess) {
        long start = System.nanoTime();
        Iterator<BusConstraint> iterator = constraints.iterator();
        final BusConstraint firstConstraint = iterator.next();

        long solutionTimeStamp = getFirstGuess(firstConstraint, initialGuess);
        long increment = firstConstraint.getBusId();
        while (iterator.hasNext()) {
            BusConstraint constraint = iterator.next();
            while (!constraint.isTimeValid(solutionTimeStamp)) {
                solutionTimeStamp += increment;
            }
            increment = getLowestCommonMultiplier(increment, constraint.getBusId());
        }

        System.out.println("Time alg 1 = " + ((System.nanoTime() - start)/1000000.0) + "ms");
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


    public static long getEarliestSolutionTime_chineseRemainderTheorem(final SortedSet<BusConstraint> constraints) {
        long start = System.nanoTime();
        long productOfAllDivisors = 1;
        for (BusConstraint constraint : constraints) {
            productOfAllDivisors *= constraint.getBusId();
        }
        long sum = 0;
        for (BusConstraint constraint : constraints) {
            if (constraint.getConstraintMinutes() == 0) {
                continue;
            }
            long remainder = constraint.getBusId() - constraint.getConstraintMinutes();
            long modulo = constraint.getBusId();
            long productOfOtherDivisors = productOfAllDivisors / modulo;
            long moduloInverseOfOtherDivisors = inverseModulo(productOfOtherDivisors % modulo, modulo);

            long term = remainder * productOfOtherDivisors * moduloInverseOfOtherDivisors;
            sum += term;
        }
        long solution = sum % productOfAllDivisors;
        System.out.println("Time alg 2 = " + ((System.nanoTime() - start)/1000000.0) + "ms");
        return solution;
    }

    private static long inverseModulo(final long a, final long m) {
        long y = 0;
        long x = 1;
        long mi = m;
        long ai = a;
        while (ai > 1) {
            long quotient = ai/mi;

            long temp = mi;
            mi = ai % mi;
            ai = temp;

            temp = y;
            y = x - quotient * y;
            x = temp;
        }
        if (x < 0) {
            x += m;
        }
        return x;
    }
}
