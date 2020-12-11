package day11.rules;

import day11.SeatState;

public class FloorRule implements SeatRule {

    @Override
    public SeatState getUpdatedState(final SeatState[][] originalStates,
                                     final int x,
                                     final int y) {
        if (originalStates[y][x] != SeatState.FLOOR) {
            throw new IllegalArgumentException("Invalid coordinates for rule");
        }
        return SeatState.FLOOR;
    }
}
