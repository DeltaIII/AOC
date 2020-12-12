package day12.instruction;

import day12.Ferry;
import direction.ManhattanDirection;
import direction.ManhattanPoint;
import java.util.function.BiConsumer;

public class ModifyWaypointInstruction implements BiConsumer<Ferry, Integer> {

    private final ManhattanDirection direction;

    ModifyWaypointInstruction(final ManhattanDirection direction) {
        this.direction = direction;
    }

    @Override
    public void accept(final Ferry ferry, final Integer steps) {
        ManhattanPoint currentWaypoint = ferry.getCurrentWaypoint();
        ManhattanPoint updatedWaypoint = new ManhattanPoint(
            currentWaypoint.getXOrdinate() + steps * direction.getDx(),
            currentWaypoint.getYOrdinate() + steps * direction.getDy());
        ferry.setCurrentWaypoint(updatedWaypoint);
    }
}
