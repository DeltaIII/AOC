package direction;

import java.util.Arrays;
import java.util.List;

public enum ManhattanDirection {

    NORTH(0, +1),
    EAST(+1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private static List<ManhattanDirection> ORDERED_DIRECTIONS = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    private final int dx;
    private final int dy;

    ManhattanDirection(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public static ManhattanDirection left(final ManhattanDirection originalDirection, final int degrees) {
        return getDirectionFromOrderedArray(originalDirection, -1 * degrees);
    }

    public static ManhattanDirection right(final ManhattanDirection originalDirection, final int degrees) {
        return getDirectionFromOrderedArray(originalDirection, degrees);
    }

    private static ManhattanDirection getDirectionFromOrderedArray(
        final ManhattanDirection originalDirection,
        final int degrees) {
        if (degrees % 90 != 0) {
            throw new IllegalArgumentException("Invalid turn degrees.");
        }

        int changeOfIndex = (degrees % 360) / 90;
        int index = (ORDERED_DIRECTIONS.indexOf(originalDirection)  + changeOfIndex) % 4;
        if (index < 0) {
            index += 4;
        }
        return ORDERED_DIRECTIONS.get(index);
    }
}
