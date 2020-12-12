package direction;


import static org.assertj.core.api.BDDAssertions.then;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ManhattanDirectionTest {

    @ParameterizedTest
    @MethodSource(value = "turnLeftScenarios")
    void testLeft(final ManhattanDirection originalDirection,
                  final int degrees,
                  final ManhattanDirection expected) {
        // When
        ManhattanDirection left = ManhattanDirection.left(originalDirection, degrees);

        // Then
        then(left).isSameAs(expected);
    }

    private static List<Arguments> turnLeftScenarios() {
        List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 90, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 180, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 270, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 360, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 450, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 90, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 180, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 270, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 360, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 450, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 90, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 180, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 270, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 360, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 450, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 90, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 180, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 270, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 360, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 450, ManhattanDirection.EAST));
        return scenarios;
    }

    @ParameterizedTest
    @MethodSource(value = "turnRightScenarios")
    void testRight(final ManhattanDirection originalDirection,
                   final int degrees,
                   final ManhattanDirection expected) {
        // When
        ManhattanDirection right = ManhattanDirection.right(originalDirection, degrees);

        // Then
        then(right).isSameAs(expected);
    }

    private static List<Arguments> turnRightScenarios() {
        List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 90, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 180, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 270, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 360, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.EAST, 450, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 90, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 180, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 270, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 360, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.NORTH, 450, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 90, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 180, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 270, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 360, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.WEST, 450, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 90, ManhattanDirection.WEST));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 180, ManhattanDirection.NORTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 270, ManhattanDirection.EAST));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 360, ManhattanDirection.SOUTH));
        scenarios.add(Arguments.of(ManhattanDirection.SOUTH, 450, ManhattanDirection.WEST));
        return scenarios;
    }
}