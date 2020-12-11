package day11;


import static org.assertj.core.api.BDDAssertions.then;

import day11.finder.AdjacentSeatFinder;
import day11.finder.NextSeatFinder;
import day11.finder.SightRangeSeatFinder;
import day11.rules.EmptySeatRule;
import day11.rules.FloorRule;
import day11.rules.OccupiedSeatRule;
import day11.rules.SeatRule;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class WaitingRoomUpdaterTest {

    private static final String TEST_DATA = "day11/testData.txt";
    private static final String INPUT = "day11/input.txt";

    private static final NextSeatFinder ADJACENT = new AdjacentSeatFinder();
    private static final NextSeatFinder SIGHT_BASED = new SightRangeSeatFinder();

    private static final int PART1_OCCUPIED_UPPERBOUND = 3;
    private static final Map<SeatState, SeatRule> PART1_RULES = new EnumMap<>(SeatState.class);

    static {
        PART1_RULES.put(SeatState.FLOOR, new FloorRule());
        PART1_RULES.put(SeatState.OCCUPIED, new OccupiedSeatRule(ADJACENT, PART1_OCCUPIED_UPPERBOUND));
        PART1_RULES.put(SeatState.EMPTY, new EmptySeatRule(ADJACENT));
    }

    private static final int PART2_OCCUPIED_UPPERBOUND = 4;
    private static final Map<SeatState, SeatRule> PART2_RULES = new EnumMap<>(SeatState.class);

    static {
        PART2_RULES.put(SeatState.FLOOR, new FloorRule());
        PART2_RULES.put(SeatState.OCCUPIED, new OccupiedSeatRule(SIGHT_BASED, PART2_OCCUPIED_UPPERBOUND));
        PART2_RULES.put(SeatState.EMPTY, new EmptySeatRule(SIGHT_BASED));
    }

    @Test
    void testReachEquilibrium_part1_testData() throws IOException {
        // Given
        List<List<SeatState>> parsedInput = InputFileReader
            .readObjects(TEST_DATA, SeatStateParser::parse)
            .collect(Collectors.toList());
        SeatState[][] waitingRoom = buildWaitingRoom(parsedInput);
        WaitingRoomUpdater updater = new WaitingRoomUpdater(PART1_RULES, waitingRoom);

        // When
        SeatState[][] seatStates = updater.reachEquilibrium();

        // Then
        then(seatStates).isNotNull();
        then(sumOccupied(seatStates)).isEqualTo(37);
    }

    @Test
    void testReachEquilibrium_part1_puzzleInput() throws IOException {
        // Given
        List<List<SeatState>> parsedInput = InputFileReader
            .readObjects(INPUT, SeatStateParser::parse)
            .collect(Collectors.toList());
        SeatState[][] waitingRoom = buildWaitingRoom(parsedInput);
        WaitingRoomUpdater updater = new WaitingRoomUpdater(PART1_RULES, waitingRoom);

        // When
        SeatState[][] seatStates = updater.reachEquilibrium();

        // Then
        then(seatStates).isNotNull();
        then(sumOccupied(seatStates)).isEqualTo(2361);
    }



    @Test
    void testReachEquilibrium_part2_testData() throws IOException {
        // Given
        List<List<SeatState>> parsedInput = InputFileReader
            .readObjects(TEST_DATA, SeatStateParser::parse)
            .collect(Collectors.toList());
        SeatState[][] waitingRoom = buildWaitingRoom(parsedInput);
        WaitingRoomUpdater updater = new WaitingRoomUpdater(PART2_RULES, waitingRoom);

        // When
        SeatState[][] seatStates = updater.reachEquilibrium();

        // Then
        then(seatStates).isNotNull();
        then(sumOccupied(seatStates)).isEqualTo(26);
    }

    @Test
    void testReachEquilibrium_part2_puzzleInput() throws IOException {
        // Given
        List<List<SeatState>> parsedInput = InputFileReader
            .readObjects(INPUT, SeatStateParser::parse)
            .collect(Collectors.toList());
        SeatState[][] waitingRoom = buildWaitingRoom(parsedInput);
        WaitingRoomUpdater updater = new WaitingRoomUpdater(PART2_RULES, waitingRoom);

        // When
        SeatState[][] seatStates = updater.reachEquilibrium();

        // Then
        then(seatStates).isNotNull();
        then(sumOccupied(seatStates)).isEqualTo(2119);
    }

    private long sumOccupied(final SeatState[][] seatStates) {
        return Arrays.stream(seatStates).map(seatStateArray -> Arrays.stream(seatStateArray)
                .filter(s -> s == SeatState.OCCUPIED).count())
            .reduce(0L, Long::sum);
    }

    private SeatState[][] buildWaitingRoom(final List<List<SeatState>> parsedInput) {
        int height = parsedInput.size();
        int width = parsedInput.get(0).size();
        WaitingRoomBuilder builder = new WaitingRoomBuilder(width, height);
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= height; y++) {
                builder.addState(parsedInput.get(y - 1).get(x - 1), x, y);
            }
        }
        return builder.build();
    }
}