package day15;

import java.util.LinkedList;
import java.util.List;
import lombok.ToString;

@ToString
public class NumberCounter {

    private final int[] turnsSeen = new int[2];
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
        if (timesSeen < 2) {
            turnsSeen[timesSeen] = turn;
        } else {
            turnsSeen[0] = turnsSeen[1];
            turnsSeen[1] = turn;
        }
        timesSeen++;
    }

    public Integer getTurnsDifference() {
        return turnsSeen[1] - turnsSeen[0];
    }
}
