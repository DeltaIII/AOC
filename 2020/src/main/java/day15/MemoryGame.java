package day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGame {

    public static int runToTurn(final int turnNumber, final List<Integer> input) {
        if (input.size() == 0) {
            throw new IllegalArgumentException("Invalid input.");
        }

        final Map<Integer, NumberCounter> previousTurnNumbers = new HashMap<>();
        int currentTurn = 1;
        NumberCounter currentNumberCounter = null;
        for (Integer startingNumber : input) {
            currentNumberCounter =
                previousTurnNumbers.computeIfAbsent(startingNumber, NumberCounter::new);
            currentNumberCounter.addTurnSeen(currentTurn);
            currentTurn++;
        }

        while (currentTurn <= turnNumber) {
            int nextNumber =
                currentNumberCounter.getTimesSeen() == 1? 0 : getDifferenceOfTurns(currentNumberCounter.getTurnsSeen());
            currentNumberCounter = previousTurnNumbers.computeIfAbsent(nextNumber, NumberCounter::new);
            currentNumberCounter.addTurnSeen(currentTurn);
            currentTurn++;
        }

        return currentNumberCounter.getNumber();
    }

    private static int getDifferenceOfTurns(final List<Integer> turnsSeen) {
        int lastIndex = turnsSeen.size() - 1;
        return turnsSeen.get(lastIndex) - turnsSeen.get(lastIndex - 1);
    }

}
