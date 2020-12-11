package day11.finder;

import day11.SeatState;
import java.util.Optional;

public interface NextSeatFinder {

    Optional<SeatState> findSeat(SeatState[][] room, int x, int y, Direction direction);

}
