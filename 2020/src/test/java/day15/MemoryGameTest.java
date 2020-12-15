package day15;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MemoryGameTest {

    private static final List<Integer> INPUT = Arrays.asList(1, 17, 0, 10, 18, 11, 6);

    private static List<Arguments> testDataScenarios() {
        final List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(5, 3));
        scenarios.add(Arguments.of(6, 3));
        scenarios.add(Arguments.of(7, 1));
        scenarios.add(Arguments.of(8, 0));
        scenarios.add(Arguments.of(9, 4));
        scenarios.add(Arguments.of(10, 0));
        return scenarios;
    }

    @ParameterizedTest
    @MethodSource("testDataScenarios")
    void testRunToTurn_testData_shortGame(final int turnNumber, final int expectedResult) {
        // Given
        final List<Integer> input = Arrays.asList(0, 3, 6);

        // When
        final int result = MemoryGame.runToTurn(turnNumber, input);

        // Then
        then(result).isEqualTo(expectedResult);
    }

    @Test
    void testRunToTurn_testData_fullGame() {
        // Given
        final List<Integer> input = Arrays.asList(0, 3, 6);

        // When
        final int result = MemoryGame.runToTurn(2020, input);

        // Then
        then(result).isEqualTo(436);
    }

    @Test
    void testRunToTurn_part1() {
        // When
        final int result = MemoryGame.runToTurn(2020, INPUT);

        // Then
        then(result).isEqualTo(436);
    }

    private static List<Arguments> longTestDataScenarios() {
        final List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(Arrays.asList(0, 3, 6), 175594));
        scenarios.add(Arguments.of(Arrays.asList(1, 3, 2), 2578));
        scenarios.add(Arguments.of(Arrays.asList(2, 1, 3), 3544142));
        scenarios.add(Arguments.of(Arrays.asList(1, 2, 3), 261214));
        scenarios.add(Arguments.of(Arrays.asList(2, 3, 1), 6895259));
        scenarios.add(Arguments.of(Arrays.asList(3, 2, 1), 18));
        scenarios.add(Arguments.of(Arrays.asList(3, 1, 2), 362));
        return scenarios;
    }

    @ParameterizedTest
    @MethodSource("longTestDataScenarios")
    void testRunToTurn_testData_longGame(final List<Integer> input, final int expectedResult) {
        // When
        final int result = MemoryGame.runToTurn(30000000, input);

        // Then
        then(result).isEqualTo(expectedResult);
    }



    @Test
    void testRunToTurn_part2() {
        // When
        final int result = MemoryGame.runToTurn(30000000, INPUT);

        // Then
        then(result).isEqualTo(1708310);
    }
}