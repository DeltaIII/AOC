package day5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardingPassProcessor {

    public static BoardingPass getLargestIdPass(Stream<BoardingPass> boardingPassStream) {
        Iterator<BoardingPass> boardingPassIterator = boardingPassStream.iterator();
        BoardingPass highestId = boardingPassIterator.next();
        while (boardingPassIterator.hasNext()) {
            BoardingPass next = boardingPassIterator.next();
            if (next.getId() > highestId.getId()) {
                highestId = next;
            }
        }
        return highestId;
    }

    public static int findMissingBoardingPassId(Stream<BoardingPass> boardingPassStream) {
        SortedSet<BoardingPass> sortedById =
            boardingPassStream.collect(Collectors.toCollection(BoardingPassProcessor::setFactory));
        Iterator<BoardingPass> boardingPassIterator = sortedById.iterator();
        int lastId = 0;
        int nextId = 0;
        while (boardingPassIterator.hasNext()) {
            nextId = boardingPassIterator.next().getId();
            if (nextId - lastId == 2) {
                return nextId - 1;
            } else {
                lastId = nextId;
            }
        }
        throw new IllegalArgumentException("No answer found");
    }

    private static SortedSet<BoardingPass> setFactory() {
        return new TreeSet<>(Comparator.comparingInt(BoardingPass::getId));
    }

}
