package day18;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.InputFileReader;

class AdvancedExpressionTest {

    private static String INPUT = "day18/input.txt";

    @ParameterizedTest
    @MethodSource(value = "expressionExamples")
    void testGetValue(final String input, final long expectedValue) {
        // Given
        ExpressionParser parser = new ExpressionParser(AdvancedExpression::new);
        Operand advancedExpression = parser.parse(input);

        // When
        long value = advancedExpression.getValue();

        // Then
        then(value).isEqualTo(expectedValue);
    }

    private static List<Arguments> expressionExamples() {
        List<Arguments> examples = new LinkedList<>();
        examples.add(Arguments.of("3 + 4 * 5", 35L));
        examples.add(Arguments.of("3 + 4 * 5 + 6", 77L));
        examples.add(Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 231L));
        examples.add(Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L));
        examples.add(Arguments.of("2 * 3 + (4 * 5)", 46L));
        examples.add(Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445L));
        examples.add(Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669060L));
        examples.add(Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23340L));
        return examples;
    }

    @Test
    void part2() throws IOException {
        // Given
        ExpressionParser parser = new ExpressionParser(AdvancedExpression::new);
        Stream<Operand> expressions = InputFileReader.readObjects(INPUT, parser::parse);

        // When
        final Long sum = expressions.map(Operand::getValue).reduce(0L, Long::sum);


        // Then
        then(sum).isEqualTo(60807587180737L);
    }

}