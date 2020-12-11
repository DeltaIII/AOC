package day11;


public class WaitingRoomBuilder {

    private final Tile[][] states;

    public WaitingRoomBuilder(final int width, final int height) {
        this.states = new Tile[height + 2][width + 2];

        // Apply left and right boundaries
        for (int j = 0; j < states.length; j++){
            states[j][0] = new Tile(SeatState.OUT_OF_BOUNDS, 0, j);
            states[j][width + 1] = new Tile(SeatState.OUT_OF_BOUNDS, width + 1, j);
        }

        // Apply top and bottom boundaries
        for (int i = 0; i < states[0].length; i++) {
            states[0][i] = new Tile(SeatState.OUT_OF_BOUNDS, i, 0);
            states[height + 1][i] = new Tile(SeatState.OUT_OF_BOUNDS, i, height + 1);
        }
    }

    public void addTile(SeatState state, int x, int y) {
        states[y][x] = new Tile(state, x, y);
    }

    public Tile[][] build() {
        return states;
    }

}
