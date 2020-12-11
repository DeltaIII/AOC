package day11.rules;

import day11.SeatState;
import day11.finder.NextSeatFinder;

public class EmptySeatRule extends OccupiedNumberBasedSeatRule {


    public EmptySeatRule(final NextSeatFinder seatFinder) {
        super(seatFinder);
    }

    @Override
    public SeatState getUpdatedState(final SeatState[][] originalStates,
                                     final int x,
                                     final int y) {
        if (originalStates[y][x] != SeatState.EMPTY) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        if (this.countNextOccupiedSeats(originalStates, x, y) == 0) {
            return SeatState.OCCUPIED;
        }
        return SeatState.EMPTY;
    }
}
