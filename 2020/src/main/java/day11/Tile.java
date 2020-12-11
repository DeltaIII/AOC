package day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tile {
    private SeatState state;
    private int xOrdinate;
    private int yOrdinate;
}
