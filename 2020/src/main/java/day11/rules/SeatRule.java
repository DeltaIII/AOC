package day11.rules;

import day11.SeatState;
import day11.Tile;

public interface SeatRule {

    /**
     * Get the updated state for a seat identified by the x, y coordinates originalState[y][x]
     * @param originalStates the original seat dates defining how the rule should behave
     * @param tile the Tile to update
     * @return the changed {@link SeatState}
     */
    SeatState getUpdatedState(Tile[][] originalStates, Tile tile);
}
