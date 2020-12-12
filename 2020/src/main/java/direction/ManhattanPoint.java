package direction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ManhattanPoint {

    private int xOrdinate;
    private int yOrdinate;

    public ManhattanPoint(final ManhattanDirection direction) {
        this(direction.getDx(), direction.getDy());
    }

    public void rotateCounterClockwise(int degrees) {
        rotateClockwise(-1 * degrees);
    }

    public void rotateClockwise(int degrees) {
        ManhattanDirection xRotated = getRotatedXDirection(degrees);
        ManhattanDirection yRotated = getRotatedYDirection(degrees);
        int xMagnitude = Math.abs(xOrdinate);
        int yMagnitude = Math.abs(yOrdinate);
        xOrdinate = xMagnitude * xRotated.getDx() + yMagnitude * yRotated.getDx();
        yOrdinate = xMagnitude * xRotated.getDy() + yMagnitude * yRotated.getDy();
    }

    private ManhattanDirection getRotatedXDirection(final int degrees) {
        ManhattanDirection xstart;
        if (xOrdinate >= 0) {
            xstart = ManhattanDirection.EAST;
        } else {
            xstart = ManhattanDirection.WEST;
        }
        return ManhattanDirection.right(xstart, degrees);
    }

    private ManhattanDirection getRotatedYDirection(final int degrees) {
        ManhattanDirection ystart;
        if (yOrdinate >= 0) {
            ystart = ManhattanDirection.NORTH;
        } else {
            ystart = ManhattanDirection.SOUTH;
        }
        return ManhattanDirection.right(ystart, degrees);
    }
}
