package day14;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BitMaskTest {

    @ParameterizedTest
    @MethodSource("testScenarios")
    void testMask(final long input, final long expected) {
        // Given
        final BitMask bitMask = new BitMask(36);
        bitMask.overrideZero(1);
        bitMask.overrideOne(6);

        // When
        final long result = bitMask.mask(input);

        // Then
        then(result).isEqualTo(expected);
    }

    private static List<Arguments> testScenarios() {
        List<Arguments> scenarios = new LinkedList<>();
        scenarios.add(Arguments.of(11L, 73L));
        scenarios.add(Arguments.of(101L, 101L));
        scenarios.add(Arguments.of(0, 64));
        return scenarios;
    }
}