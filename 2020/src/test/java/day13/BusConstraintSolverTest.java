package day13;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.InputFileReader;

class BusConstraintSolverTest {

    private static final String INPUT = "day13/input.txt";

    private static List<Arguments> testData() {
        final List<Arguments> arguments = new LinkedList<>();
        arguments.add(Arguments.of("7,13,x,x,59,x,31,19", 1068781L));
        arguments.add(Arguments.of("17,x,13,19", 3417L));
        arguments.add(Arguments.of("67,7,59,61", 754018L));
        arguments.add(Arguments.of("67,x,7,59,61", 779210L));
        arguments.add(Arguments.of("67,7,x,59,61", 1261476L));
        arguments.add(Arguments.of("1789,37,47,1889", 1202161486L));
        return arguments;
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testGetEarliestSolutionTime_testData(final String input, final long expected) {
        // Given
        SortedSet<BusConstraint> busConstraints = BusConstraintParser.parse(input);

        // When
        long earliestSolutionTime =
            BusConstraintSolver.getEarliestSolutionTime(busConstraints, busConstraints.first().getBusId());

        // Then
        then(earliestSolutionTime).isEqualTo(expected);
    }

    @Test
    void testGetEarliesSolutionTime_part2() throws IOException {
        // Given
        List<String> inputs = InputFileReader.readStrings(INPUT).collect(Collectors.toList());
        SortedSet<BusConstraint> busConstraints = BusConstraintParser.parse(inputs.get(1));

        // When
        long earliestSolutionTime = BusConstraintSolver.getEarliestSolutionTime(busConstraints, 556100168221141L);

        // Then
        then(earliestSolutionTime).isEqualTo(1);

    }
}