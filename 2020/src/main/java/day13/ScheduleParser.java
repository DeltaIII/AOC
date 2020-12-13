package day13;

import java.util.SortedSet;
import java.util.TreeSet;

public class ScheduleParser {

    /**
     * @param scheduleString String of format "7,13,x,x,59,x,31,19", where x is ignored
     */
    public static SortedSet<Integer> parse(final String scheduleString) {
        final SortedSet<Integer> schedule = new TreeSet<>();
        for (String input : scheduleString.split(",")) {
            if (!"x".equals(input)) {
                schedule.add(Integer.parseInt(input));
            }
        }
        return schedule;
    }
}
