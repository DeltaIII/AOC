package day10;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class JoltageAdapterChainBuilderTest {

    private static final String SHORT_EXAMPLE = "day10/shortExample.txt";
    private static final String LONG_EXAMPLE = "day10/longExample.txt";
    private static final String INPUT = "day10/input.txt";

    @Test
    void testBuild_shortExample() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(SHORT_EXAMPLE);

        // When
        JoltageAdapterChain joltageAdapterChain = JoltageAdapterChainBuilder.build(input);

        // Then
        Map<Integer, AtomicInteger> joltageDifferences = joltageAdapterChain.getJoltageDifferences();
        then(joltageDifferences.size()).isEqualTo(2);
        then(joltageDifferences.get(1).get()).isEqualTo(7);
        then(joltageDifferences.get(3).get()).isEqualTo(5);
    }

    @Test
    void testBuild_longExample() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(LONG_EXAMPLE);

        // When
        JoltageAdapterChain joltageAdapterChain = JoltageAdapterChainBuilder.build(input);

        // Then
        Map<Integer, AtomicInteger> joltageDifferences = joltageAdapterChain.getJoltageDifferences();
        then(joltageDifferences.size()).isEqualTo(2);
        then(joltageDifferences.get(1).get()).isEqualTo(22);
        then(joltageDifferences.get(3).get()).isEqualTo(10);
    }

    @Test
    void testBuild_part1() throws IOException {
        // Given
        Stream<Integer> input = InputFileReader.readInts(INPUT);

        // When
        JoltageAdapterChain joltageAdapterChain = JoltageAdapterChainBuilder.build(input);

        // Then
        Map<Integer, AtomicInteger> joltageDifferences = joltageAdapterChain.getJoltageDifferences();
        then(joltageDifferences.size()).isEqualTo(2);
        then(joltageDifferences.get(1).get()).isEqualTo(65);
        then(joltageDifferences.get(3).get()).isEqualTo(27);
    }

}