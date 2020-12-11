package day11.finder;

import day11.SeatState;
import java.util.Optional;

public class AdjacentSeatFinder implements NextSeatFinder {


    @Override
    public Optional<SeatState> findSeat(final SeatState[][] room,
                                        final int x,
                                        final int y,
                                        final Direction direction) {
        SeatState seatState = room[y + direction.getDy()][x + direction.getDx()];
        if (seatState == SeatState.OCCUPIED || seatState == SeatState.EMPTY) {
            return Optional.of(seatState);
        }
        return Optional.empty();
    }
}
