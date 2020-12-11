package day11.finder;

import day11.SeatState;
import day11.Tile;
import java.util.Optional;

public interface NextSeatFinder {

    Optional<SeatState> findSeat(Tile[][] room, int x, int y, Direction direction);

}
