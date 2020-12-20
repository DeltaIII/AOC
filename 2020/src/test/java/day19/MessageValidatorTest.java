package day19;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class MessageValidatorTest {

    private static final String TEST_DATA = "day19/testData.txt";
    private static final String INPUT = "day19/input.txt";

    @Test
    void testMatchesRule_testData() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(TEST_DATA).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        MessageValidator validator = new MessageValidator(ruleSet);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> validator.matchesRule(0, m)).count();

        // Then
        then(count).isEqualTo(2);
    }
    @Test
    void testMatchesRule_part1() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(INPUT).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        MessageValidator validator = new MessageValidator(ruleSet);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> validator.matchesRule(0, m)).count();

        // Then
        then(count).isEqualTo(195);
    }
}
