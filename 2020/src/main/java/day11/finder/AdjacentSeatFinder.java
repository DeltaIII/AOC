package day11.finder;

import day11.SeatState;
import day11.Tile;
import direction.GridDirection;
import java.util.Optional;

public class AdjacentSeatFinder implements NextSeatFinder {


    @Override
    public Optional<SeatState> findSeat(final Tile[][] room,
                                        final int x,
                                        final int y,
                                        final GridDirection gridDirection) {
        SeatState seatState = room[y + gridDirection.getDy()][x + gridDirection.getDx()].getState();
        if (seatState == SeatState.OCCUPIED || seatState == SeatState.EMPTY) {
            return Optional.of(seatState);
        }
        return Optional.empty();
    }
}
