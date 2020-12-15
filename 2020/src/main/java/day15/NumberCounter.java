package day15;

import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

@ToString
public class NumberCounter {

    private final List<Integer> turnsSeen = new ArrayList<>();
    private final int number;

    public NumberCounter(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getTimesSeen() {
        return turnsSeen.size();
    }

    public void addTurnSeen(final int turn) {
        turnsSeen.add(turn);
    }

    public List<Integer> getTurnsSeen() {
        return turnsSeen;
    }
}
