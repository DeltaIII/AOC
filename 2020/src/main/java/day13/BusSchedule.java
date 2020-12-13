package day13;

import java.util.SortedSet;

public class BusSchedule {

    private final SortedSet<Integer> schedule;

    public BusSchedule(final SortedSet<Integer> schedule) {
        this.schedule = schedule;
    }

    public BusTime getEarliestBusTime(final Integer currentTime) {
        int lowestWait = Integer.MAX_VALUE;
        Integer earilestBus = null;
        System.out.println("schedule = " + schedule);
        for (Integer nextBus : schedule) {
            int timeToNextBus = nextBus - currentTime % nextBus;
            System.out.println(String.format("Bus <%s> - wait = %s", nextBus, timeToNextBus));
            if (timeToNextBus < lowestWait) {
                lowestWait = timeToNextBus;
                earilestBus = nextBus;
            }
        }
        if (earilestBus == null) {
            throw new IllegalArgumentException("No answer found."); // Should be impossible
        }

        return new BusTime(earilestBus, currentTime + lowestWait);
    }
}
