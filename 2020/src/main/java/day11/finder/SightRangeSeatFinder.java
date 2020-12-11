package day11.finder;

import day11.SeatState;
import java.util.Optional;

public class SightRangeSeatFinder implements NextSeatFinder {

    @Override
    public Optional<SeatState> findSeat(final SeatState[][] room,
                                        final int x,
                                        final int y,
                                        final Direction direction) {
        boolean canSee = true;
        int nextX = x;
        int nextY = y;
        while (canSee) {
            nextX += direction.getDx();
            nextY += direction.getDy();
            SeatState nextVisibleState = room[nextY][nextX];
            if (isStateOutOfBounds(nextVisibleState)) {
                canSee = false;
            } else if (isASeat(nextVisibleState)) {
                return Optional.of(nextVisibleState);
            }
        }
        // No seat found until room boundary
        return Optional.empty();
    }

    private boolean isStateOutOfBounds(final SeatState nextVisibleState) {
        return nextVisibleState == SeatState.OUT_OF_BOUNDS;
    }

    private boolean isASeat(final SeatState nextVisibleState) {
        return nextVisibleState != SeatState.FLOOR;
    }
}
