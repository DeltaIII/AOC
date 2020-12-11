package day11;


public class WaitingRoomBuilder {

    private final SeatState[][] states;

    public WaitingRoomBuilder(final int width, final int height) {
        this.states = new SeatState[height + 2][width + 2];

        // Apply left and right boundaries
        for (int j = 0; j < states.length; j++){
            states[j][0] = SeatState.OUT_OF_BOUNDS;
            states[j][width + 1] = SeatState.OUT_OF_BOUNDS;
        }

        // Apply top and bottom boundaries
        for (int i = 0; i < states[0].length; i++) {
            states[0][i] = SeatState.OUT_OF_BOUNDS;
            states[height + 1][i] = SeatState.OUT_OF_BOUNDS;
        }
    }

    public void addState(SeatState state, int x, int y) {
        states[y][x] = state;
    }

    public SeatState[][] build() {
        return states;
    }

}
