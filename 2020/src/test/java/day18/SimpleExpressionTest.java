package day18;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.InputFileReader;

class SimpleExpressionTest {

    private static String INPUT = "day18/input.txt";

    @ParameterizedTest
    @MethodSource(value = "expressionExamples")
    void testGetValue(final String input, final long expectedValue) {
        // Given
        ExpressionParser parser = new ExpressionParser(SimpleExpression::new);
        Operand simpleExpression = parser.parse(input);

        // When
        long value = simpleExpression.getValue();

        // Then
        then(value).isEqualTo(expectedValue);
    }

    private static List<Arguments> expressionExamples() {
        List<Arguments> examples = new LinkedList<>();
        examples.add(Arguments.of("2 * 3", 6L));
        examples.add(Arguments.of("2 * 3 + (4 * 5)", 26L));
        examples.add(Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 71L));
        examples.add(Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L));
        examples.add(Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437L));
        examples.add(Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240L));
        examples.add(Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632L));
        return examples;
    }
    
    @Test
    void part1() throws IOException {
        // Given
        ExpressionParser parser = new ExpressionParser(SimpleExpression::new);
        Stream<Operand> expressions = InputFileReader.readObjects(INPUT, parser::parse);
        
        // When
        final Long sum = expressions.map(Operand::getValue).reduce(0L, Long::sum);


        // Then
        then(sum).isEqualTo(7293529867931L);
    }
}