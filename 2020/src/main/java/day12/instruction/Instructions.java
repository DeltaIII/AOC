package day12.instruction;

import day12.Ferry;
import direction.ManhattanDirection;
import java.util.function.BiConsumer;

public class Instructions {

    public static BiConsumer<Ferry, Integer> moveForward() {
        return Ferry::move;
    }

    public static BiConsumer<Ferry, Integer> moveNorth() {
        return new NormalisedWaypointFerryInstruction(ManhattanDirection.NORTH);
    }

    public static BiConsumer<Ferry, Integer> moveEast() {
        return new NormalisedWaypointFerryInstruction(ManhattanDirection.EAST);
    }

    public static BiConsumer<Ferry, Integer> moveSouth() {
        return new NormalisedWaypointFerryInstruction(ManhattanDirection.SOUTH);
    }

    public static BiConsumer<Ferry, Integer> moveWest() {
        return new NormalisedWaypointFerryInstruction(ManhattanDirection.WEST);
    }
    public static BiConsumer<Ferry, Integer> moveWaypointNorth() {
        return new ModifyWaypointInstruction(ManhattanDirection.NORTH);
    }

    public static BiConsumer<Ferry, Integer> moveWaypointEast() {
        return new ModifyWaypointInstruction(ManhattanDirection.EAST);
    }

    public static BiConsumer<Ferry, Integer> moveWaypointSouth() {
        return new ModifyWaypointInstruction(ManhattanDirection.SOUTH);
    }

    public static BiConsumer<Ferry, Integer> moveWaypointWest() {
        return new ModifyWaypointInstruction(ManhattanDirection.WEST);
    }

    public static BiConsumer<Ferry, Integer> turnLeft() {
        return (ferry, degrees) -> ferry.getCurrentWaypoint().rotateCounterClockwise(degrees);
    }

    public static BiConsumer<Ferry, Integer> turnRight() {
        return (ferry, degrees) -> ferry.getCurrentWaypoint().rotateClockwise(degrees);
    }
}
