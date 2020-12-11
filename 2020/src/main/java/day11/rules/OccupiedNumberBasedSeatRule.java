package day11.rules;

import day11.SeatState;
import day11.finder.Direction;
import day11.finder.NextSeatFinder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class OccupiedNumberBasedSeatRule implements SeatRule{

    private final NextSeatFinder seatFinder;

    protected OccupiedNumberBasedSeatRule(final NextSeatFinder seatFinder) {
        this.seatFinder = seatFinder;
    }

    protected int countNextOccupiedSeats(final SeatState[][] originalStates,
                                         final int x,
                                         final int y) {
        return (int) getAdjacentSeatStates(originalStates, x, y)
            .stream()
            .filter(s -> s == SeatState.OCCUPIED)
            .count();
    }

    private Collection<SeatState> getAdjacentSeatStates(final SeatState[][] originalStates,
                                                              final int x,
                                                              final int y) {
        List<Optional<SeatState>> adjacentSeatStates = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            adjacentSeatStates.add(seatFinder.findSeat(originalStates, x, y, direction));
        }
        return adjacentSeatStates.stream()
            .filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList());
    }
}
