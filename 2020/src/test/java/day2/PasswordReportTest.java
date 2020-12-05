package day2;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class PasswordReportTest {

    private static final String INPUT = "day2/input.txt";
    private static final String TEST_DATA = "day2/testData.txt";

    @Test
    void testCountValidPasswords_numberPolicy_part1TestData() throws IOException {
        // Given
        Stream<String> passwordData = InputFileReader.readStrings(TEST_DATA);
        PasswordReport passwordReport = new PasswordReport(PolicyParser::parseNumberPolicy);

        // When
        long numberOfValidPasswords = passwordReport.countValidPasswords(passwordData);

        // Then
        then(numberOfValidPasswords).isEqualTo(2);
    }

    @Test
    void testCountValidPasswords_numberPolicy_part1Input() throws IOException {
        // Given
        Stream<String> passwordData = InputFileReader.readStrings(INPUT);
        PasswordReport passwordReport = new PasswordReport(PolicyParser::parseNumberPolicy);

        // When
        long numberOfValidPasswords = passwordReport.countValidPasswords(passwordData);

        // Then
        then(numberOfValidPasswords).isEqualTo(536);
    }

    @Test
    void part2() throws IOException {
        // Given
        Stream<String> passwordData = InputFileReader.readStrings(INPUT);
        PasswordReport passwordReport = new PasswordReport(PolicyParser::parsePositionPolicy);

        // When
        long numberOfValidPasswords = passwordReport.countValidPasswords(passwordData);

        // Then
        then(numberOfValidPasswords).isEqualTo(558);
    }

}