package day11.rules;

import day11.SeatState;
import day11.Tile;
import day11.finder.NextSeatFinder;

public class OccupiedSeatRule extends OccupiedNumberBasedSeatRule {

    private int upperBound;

    public OccupiedSeatRule(final NextSeatFinder seatFinder, final int upperBound) {
        super(seatFinder);
        this.upperBound = upperBound;
    }

    @Override
    public SeatState getUpdatedState(final Tile[][] originalStates,
                                     final Tile tile) {
        if (originalStates[tile.getYOrdinate()][tile.getXOrdinate()].getState() != SeatState.OCCUPIED) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        if (this.countNextOccupiedSeats(originalStates, tile) > upperBound) {
            return SeatState.EMPTY;
        }
        return SeatState.OCCUPIED;
    }
}
