package day11;

import day11.rules.SeatRule;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WaitingRoomUpdater {

    private final Map<SeatState, SeatRule> updateRules;

    private final Map<Integer, Tile[][]> history = new HashMap<>();
    private final int height;
    private final int width;

    public WaitingRoomUpdater(final Map<SeatState, SeatRule> updateRules, final Tile[][] initial) {
        this.updateRules = updateRules;
        this.height = initial.length - 2;
        this.width = initial[0].length - 2;
        history.put(0, initial);
    }

    public Tile[][] reachEquilibrium() {
        boolean inEquilibrium = false;
        int iteration = 0;
        while (!inEquilibrium) {
            iteration++;
            Tile[][] oldState = history.get(iteration - 1);
            WaitingRoomBuilder newStateBuilder = new WaitingRoomBuilder(this.width, this.height);
            Arrays.stream(oldState).parallel()
                .forEach(
                    row -> Arrays.stream(row).parallel()
                        .forEach(tile -> updateStateOfTile(tile, oldState, newStateBuilder)));

            Tile[][] newState = newStateBuilder.build();
            history.put(iteration, newState);

//            System.out.println(" ------- Iteration " + iteration + " ------- ");
//            Arrays.stream(newState).forEach(seatStateArray ->
//                System.out.println(Arrays.stream(seatStateArray)
//                    .map(SeatState::getCharacter).collect(Collectors.joining())));

            inEquilibrium = isInEquilibrium(newState, oldState);
        }
        return history.get(iteration);
    }

    private void updateStateOfTile(final Tile tile,
                                   final Tile[][] oldState,
                                   final WaitingRoomBuilder roomBuilder) {
        SeatState state = tile.getState();
        if (state != SeatState.OUT_OF_BOUNDS) {
            SeatRule seatRule = updateRules.get(state);
            SeatState updatedState = seatRule.getUpdatedState(oldState, tile);
            roomBuilder.addTile(updatedState, tile.getXOrdinate(), tile.getYOrdinate());
        }
    }

    private boolean isInEquilibrium(final Tile[][] newState, final Tile[][] oldState) {
        for (int x = 1; x <= this.width ; x++) {
            for (int y = 1; y <= this.height; y++) {
                if (newState[y][x].getState() != oldState[y][x].getState()) {
                    return false;
                }
            }
        }
        return true;
    }


}
