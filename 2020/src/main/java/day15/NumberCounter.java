package day15;

import java.util.LinkedList;
import java.util.List;
import lombok.ToString;

@ToString
public class NumberCounter {

    private final List<Integer> turnsSeen = new LinkedList<>();
    private final int number;
    private int timesSeen = 0;

    public NumberCounter(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getTimesSeen() {
        return timesSeen;
    }

    public void addTurnSeen(final int turn) {
        turnsSeen.add(turn);
        if (turnsSeen.size() > 2) {
            turnsSeen.remove(0);
        }
        timesSeen++;
    }

    public Integer getTurnsDifference() {
        return turnsSeen.get(1) - turnsSeen.get(0);
    }
}
