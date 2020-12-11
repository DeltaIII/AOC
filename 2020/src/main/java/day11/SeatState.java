package day11;

public enum SeatState {
    EMPTY("L"),
    OCCUPIED("#"),
    FLOOR("."),
    OUT_OF_BOUNDS("~");

    private String character;
    SeatState(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}
