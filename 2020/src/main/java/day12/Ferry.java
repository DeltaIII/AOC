package day12;

import direction.ManhattanPoint;

public class Ferry {

    private ManhattanPoint currentWaypoint;
    private int xOrdinate = 0;
    private int yOrdinate = 0;

    public Ferry(final ManhattanPoint bearing,
                 final int xOrdinate,
                 final int yOrdinate) {
        this.currentWaypoint = bearing;
        this.xOrdinate = xOrdinate;
        this.yOrdinate = yOrdinate;
    }

    public void move(final int steps) {
        this.xOrdinate += currentWaypoint.getXOrdinate() * steps;
        this.yOrdinate += currentWaypoint.getYOrdinate() * steps;
    }

    public int getxOrdinate() {
        return xOrdinate;
    }

    public int getyOrdinate() {
        return yOrdinate;
    }

    public ManhattanPoint getCurrentWaypoint() {
        return currentWaypoint;
    }

    public void setCurrentWaypoint(final ManhattanPoint currentSpeed) {
        this.currentWaypoint = currentSpeed;
    }
}
