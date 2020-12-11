package day11;

import day11.rules.SeatRule;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WaitingRoomUpdater {

    private final Map<SeatState, SeatRule> updateRules;

    private final Map<Integer, SeatState[][]> history = new HashMap<>();
    private final int height;
    private final int width;

    public WaitingRoomUpdater(final Map<SeatState, SeatRule> updateRules, final SeatState[][] initial) {
        this.updateRules = updateRules;
        this.height = initial.length - 2;
        this.width = initial[0].length - 2;
        history.put(0, initial);
    }

    public SeatState[][] reachEquilibrium() {
        boolean inEquilibrium = false;
        int iteration = 0;
        while (!inEquilibrium) {
            iteration++;
            SeatState[][] oldState = history.get(iteration - 1);
            WaitingRoomBuilder newStateBuilder = new WaitingRoomBuilder(this.width, this.height);
            for (int x = 1; x <= this.width ; x++) {
                for (int y = 1; y <= this.height; y++) {
                    SeatRule seatRule = updateRules.get(oldState[y][x]);
                    SeatState updatedState = seatRule.getUpdatedState(oldState, x, y);
                    newStateBuilder.addState(updatedState, x, y);
                }
            }

            SeatState[][] newState = newStateBuilder.build();
            history.put(iteration, newState);

//            System.out.println(" ------- Iteration " + iteration + " ------- ");
//            Arrays.stream(newState).forEach(seatStateArray ->
//                System.out.println(Arrays.stream(seatStateArray)
//                    .map(SeatState::getCharacter).collect(Collectors.joining())));

            inEquilibrium = isInEquilibrium(newState, oldState);
        }
        return history.get(iteration);
    }

    private boolean isInEquilibrium(final SeatState[][] newState, final SeatState[][] oldState) {
        for (int x = 1; x <= this.width ; x++) {
            for (int y = 1; y <= this.height; y++) {
                if (newState[y][x] != oldState[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }
}
