package direction;

public enum GridDirection {
    NORTH_WEST(-1, -1),
    WEST(-1, 0),
    SOUTH_WEST(-1, +1),
    NORTH(0, -1),
    SOUTH(0, +1),
    NORTH_EAST(+1, -1),
    EAST(+1, 0),
    SOUTH_EAST(+1, +1);

    private final int dx;
    private final int dy;

    GridDirection(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
