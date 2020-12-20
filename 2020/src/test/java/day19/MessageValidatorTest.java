package day19;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class MessageValidatorTest {

    private static final String TEST_DATA = "day19/testData.txt";
    private static final String TEST_DATA_PART2 = "day19/testData2.txt";
    private static final String INPUT = "day19/input.txt";

    @Test
    void testMatchesRule_testData() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(TEST_DATA).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> ruleSet.isValidMessageForRule(0, m)).count();

        // Then
        then(count).isEqualTo(2);
    }

    @Test
    void testMatchesRule_part1() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(INPUT).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> ruleSet.isValidMessageForRule(0, m)).count();

        // Then
        then(count).isEqualTo(195);
    }



    @Test
    void testMatchesRule_part2_testData() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(TEST_DATA_PART2).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> ruleSet.isValidMessageForRule(0, m)).count();

        // Then
        then(count).isEqualTo(3);

        // Update rules
//        ruleSet.setRule(new LoopingRule(8, Collections.singletonList(42), Arrays.asList(42, 8)));
//        ruleSet.setRule(new LoopingRule(8, Arrays.asList(42, 31), Arrays.asList(42, 11, 31)));
//
//        // When
//        final Pattern updatedMatcher = ruleSet.getRulePattern(0);
//        long updatedCount = messages.stream().filter(m -> updatedMatcher.matcher(m).matches()).count();
//
//        then(updatedCount).isEqualTo(12);

    }

    @Test
    void testMatchesRule_part2() throws IOException {
        // Given
        List<String> data = InputFileReader.readStrings(INPUT).collect(Collectors.toList());
        RuleSet ruleSet = RuleSetParser.parse(data);
        List<String> messages = data.stream().filter(s->!s.contains(":")).collect(Collectors.toList());

        // When
        long count = messages.stream().filter(m -> ruleSet.isValidMessageForRule(0, m)).count();

        // Then
        then(count).isEqualTo(195);
    }
}
