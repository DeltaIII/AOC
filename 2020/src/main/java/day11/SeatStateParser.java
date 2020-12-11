package day11;

import java.util.ArrayList;
import java.util.List;

public class SeatStateParser {

    public static List<SeatState> parse(final String unparsedSeatStates) {
        List<SeatState> states = new ArrayList<>();
        for (char c : unparsedSeatStates.toCharArray()) {
            switch (c) {
                case 'L':
                    states.add(SeatState.EMPTY);
                    break;
                case '.':
                    states.add(SeatState.FLOOR);
                    break;
                case '#':
                    states.add(SeatState.OCCUPIED);
                    break;
                default:
                    throw new IllegalArgumentException("Not a valid state");
            }
        }
        return states;
    }
}
