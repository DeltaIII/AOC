package day11.rules;

import day11.SeatState;

public interface SeatRule {

    /**
     * Get the updated state for a seat identified by the x, y coordinates originalState[y][x]
     * @param originalStates the original seat dates defining how the rule should behave
     * @param x the x ordinate
     * @param y the y ordinate
     * @return the changed {@link SeatState}
     */
    SeatState getUpdatedState(SeatState[][] originalStates, int x, int y);
}
