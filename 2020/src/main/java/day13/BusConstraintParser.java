package day13;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class BusConstraintParser {

    /**
     * @param scheduleString String of format "7,13,x,x,59,x,31,19", where x is ignored
     */
    public static SortedSet<BusConstraint> parse(final String scheduleString) {
        final SortedSet<BusConstraint> constraints = new TreeSet<>(Comparator.comparingInt(BusConstraint::getBusId));

        String[] splitConstraintData = scheduleString.split(",");
        int referenceBus = Integer.parseInt(splitConstraintData[0]);
        for (int constraintMinutes = 0; constraintMinutes < splitConstraintData.length; constraintMinutes++) {
            String input = splitConstraintData[constraintMinutes];
            if (!"x".equals(input)) {
                constraints.add(new BusConstraint(Integer.parseInt(input), referenceBus, constraintMinutes));
            }
        }
        return constraints;
    }
}
