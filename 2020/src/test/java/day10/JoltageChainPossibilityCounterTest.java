package day10;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class JoltageChainPossibilityCounterTest {

    private static final String SHORT_EXAMPLE = "day10/shortExample.txt";
    private static final String LONG_EXAMPLE = "day10/longExample.txt";
    private static final String INPUT = "day10/input.txt";

    @Test
    void testGetNumberOfPossiblePermutations_shortExample() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(SHORT_EXAMPLE);
        JoltageAdapterChain chain = JoltageAdapterChainBuilder.build(input);
        JoltageChainPossibilityCounter counter = new JoltageChainPossibilityCounter(3);

        // When
        long numberOfPossiblePermutations = counter.getNumberOfPossiblePermutations(chain);

        // Then
        then(numberOfPossiblePermutations).isEqualTo(8);
    }

    @Test
    void testGetNumberOfPossiblePermutations_longExample() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(LONG_EXAMPLE);
        JoltageAdapterChain chain = JoltageAdapterChainBuilder.build(input);
        JoltageChainPossibilityCounter counter = new JoltageChainPossibilityCounter(3);

        // When
        long numberOfPossiblePermutations = counter.getNumberOfPossiblePermutations(chain);

        // Then
        then(numberOfPossiblePermutations).isEqualTo(19208);
    }



    @Test
    void testGetNumberOfPossiblePermutations_part2() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(INPUT);
        JoltageAdapterChain chain = JoltageAdapterChainBuilder.build(input);
        JoltageChainPossibilityCounter counter = new JoltageChainPossibilityCounter(3);

        // When
        long numberOfPossiblePermutations = counter.getNumberOfPossiblePermutations(chain);

        // Then
        then(numberOfPossiblePermutations).isEqualTo(4049565169664L);
    }
}