package day11.rules;

import day11.SeatState;
import day11.finder.NextSeatFinder;

public class OccupiedSeatRule extends OccupiedNumberBasedSeatRule {

    private int upperBound;

    public OccupiedSeatRule(final NextSeatFinder seatFinder, final int upperBound) {
        super(seatFinder);
        this.upperBound = upperBound;
    }

    @Override
    public SeatState getUpdatedState(final SeatState[][] originalStates,
                                     final int x,
                                     final int y) {
        if (originalStates[y][x] != SeatState.OCCUPIED) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        if (this.countNextOccupiedSeats(originalStates, x, y) > upperBound) {
            return SeatState.EMPTY;
        }
        return SeatState.OCCUPIED;
    }
}
