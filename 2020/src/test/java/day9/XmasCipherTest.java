package day9;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class XmasCipherTest {

    private static final String TEST_DATA = "day9/testData.txt";
    private static final String INPUT = "day9/input.txt";

    @Test
    void testDecode_testData() throws IOException {
        // Given
        final int preambleLength = 5;
        final List<Long> inputData =
            InputFileReader.readObjects(TEST_DATA, Long::parseLong).collect(Collectors.toList());
        final XmasCipher cipher = new XmasCipher(preambleLength);

        // When
        final CipherResponse cipherResponse = cipher.decode(inputData);

        // Then
        then(cipherResponse).isNotNull();
        then(cipherResponse.isValid()).isFalse();
        final Optional<Long> invalidNumber = cipherResponse.getInvalidNumber();
        then(invalidNumber).isPresent();
        then(invalidNumber.get()).isEqualTo(127L);
        Optional<Integer> invalidNumberIndex = cipherResponse.getInvalidNumberIndex();
        then(invalidNumberIndex).isPresent();
        then(invalidNumberIndex.get()).isEqualTo(14);
    }

    @Test
    void testDecode_part1Input() throws IOException {
        // Given
        final int preambleLength = 25;
        final List<Long> inputData =
            InputFileReader.readObjects(INPUT, Long::parseLong).collect(Collectors.toList());
        final XmasCipher cipher = new XmasCipher(preambleLength);

        // When
        final CipherResponse cipherResponse = cipher.decode(inputData);

        // Then
        then(cipherResponse).isNotNull();
        then(cipherResponse.isValid()).isFalse();
        final Optional<Long> invalidNumber = cipherResponse.getInvalidNumber();
        then(invalidNumber).isPresent();
        then(invalidNumber.get()).isEqualTo(756008079L);
        Optional<Integer> invalidNumberIndex = cipherResponse.getInvalidNumberIndex();
        then(invalidNumberIndex).isPresent();
        then(invalidNumberIndex.get()).isEqualTo(631);
    }

}
