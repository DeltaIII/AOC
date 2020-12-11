package day11.rules;

import day11.SeatState;
import day11.Tile;
import day11.finder.NextSeatFinder;

public class EmptySeatRule extends OccupiedNumberBasedSeatRule {


    public EmptySeatRule(final NextSeatFinder seatFinder) {
        super(seatFinder);
    }

    @Override
    public SeatState getUpdatedState(final Tile[][] originalStates,
                                     final Tile tile) {
        if (originalStates[tile.getYOrdinate()][tile.getXOrdinate()].getState() != SeatState.EMPTY) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        if (this.countNextOccupiedSeats(originalStates, tile) == 0) {
            return SeatState.OCCUPIED;
        }
        return SeatState.EMPTY;
    }
}
