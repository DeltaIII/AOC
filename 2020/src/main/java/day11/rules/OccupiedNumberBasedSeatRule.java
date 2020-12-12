package day11.rules;

import day11.SeatState;
import day11.Tile;
import direction.GridDirection;
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

    protected int countNextOccupiedSeats(final Tile[][] originalStates,
                                         final Tile tile) {
        return (int) getNextSeatStates(originalStates, tile)
            .stream()
            .filter(s -> s == SeatState.OCCUPIED)
            .count();
    }

    private Collection<SeatState> getNextSeatStates(final Tile[][] originalStates,
                                                    final Tile tile) {
        List<Optional<SeatState>> nextSeatStates = new ArrayList<>();
        for (GridDirection gridDirection : GridDirection.values()) {
            nextSeatStates.add(
                seatFinder.findSeat(originalStates, tile.getXOrdinate(), tile.getYOrdinate(), gridDirection));
        }
        return nextSeatStates.stream()
            .filter(Optional::isPresent).map(Optional::get)
            .collect(Collectors.toList());
    }
}
