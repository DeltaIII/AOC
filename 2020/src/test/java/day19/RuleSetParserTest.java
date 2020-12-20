package day19;


import static org.assertj.core.api.BDDAssertions.then;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class RuleSetParserTest {

    @Test
    void testParse() {
        // Given
        Stream<String> input = Stream.of("0: 1 2", "1: \"a\"", "2: 1 3 | 3 1",  "3: \"b\"", "");
        Set<String> expectedRuleStrings0 = ImmutableSet.of("aab", "aba");
        Set<String> expectedRuleStrings1 = ImmutableSet.of("a");
        Set<String> expectedRuleStrings2 = ImmutableSet.of("ab", "ba");
        Set<String> expectedRuleStrings3 = ImmutableSet.of("b");

        // When
        RuleSet ruleSet = RuleSetParser.parse(input.collect(Collectors.toList()));

        // Then
        then(ruleSet.getValidMessagesForRule(0)).isEqualTo(expectedRuleStrings0);
        then(ruleSet.getValidMessagesForRule(1)).isEqualTo(expectedRuleStrings1);
        then(ruleSet.getValidMessagesForRule(2)).isEqualTo(expectedRuleStrings2);
        then(ruleSet.getValidMessagesForRule(3)).isEqualTo(expectedRuleStrings3);
    }
}