package day12.instruction;

import day12.Ferry;
import direction.ManhattanDirection;
import direction.ManhattanPoint;
import java.util.function.BiConsumer;

public class NormalisedWaypointFerryInstruction implements BiConsumer<Ferry, Integer> {

    private final ManhattanPoint normalisedWaypoint;

    NormalisedWaypointFerryInstruction(final ManhattanDirection direction) {
        this.normalisedWaypoint = new ManhattanPoint(direction);
    }


    @Override
    public void accept(final Ferry ferry, final Integer steps) {
        ManhattanPoint originalBearing = ferry.getCurrentWaypoint();
        ferry.setCurrentWaypoint(normalisedWaypoint);
        ferry.move(steps);
        ferry.setCurrentWaypoint(originalBearing);
    }
}
