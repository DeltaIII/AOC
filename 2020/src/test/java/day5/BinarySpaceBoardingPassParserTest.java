package day5;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BinarySpaceBoardingPassParserTest {

    private static final String INPUT1 = "BFFFBBFRRR";
    private static final String INPUT2 = "FFFBBBFRRR";
    private static final String INPUT3 = "BBFFBBFRLL";

    @ParameterizedTest
    @MethodSource(value = "part1BoardingScenarios")
    void parseBoardingPass(final String input, final BoardingPass expected) {
        // When
        BoardingPass boardingPass = BinarySpaceBoardingPassParser.parseBoardingPass(input);

        // Then
        then(boardingPass).isNotNull();
        then(boardingPass).isEqualTo(expected);
    }

    private static List<Arguments> part1BoardingScenarios() {
        List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(INPUT1, new BoardingPass(567, 70, 7)));
        scenarios.add(Arguments.of(INPUT2, new BoardingPass(119, 14, 7)));
        scenarios.add(Arguments.of(INPUT3, new BoardingPass(820, 102, 4)));
        return scenarios;
    }
}