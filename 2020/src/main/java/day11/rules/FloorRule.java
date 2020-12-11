package day11.rules;

import day11.SeatState;
import day11.Tile;

public class FloorRule implements SeatRule {

    @Override
    public SeatState getUpdatedState(final Tile[][] originalStates,
                                     final Tile tile) {
        if (originalStates[tile.getYOrdinate()][tile.getXOrdinate()].getState() != SeatState.FLOOR) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        return SeatState.FLOOR;
    }
}
