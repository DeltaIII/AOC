package day18;

import java.util.Arrays;
import java.util.Queue;
import java.util.function.BiFunction;

public class ExpressionParser {

    private final BiFunction<Queue<Operand>, Queue<Operator>, Operand> expressionFactory;

    public ExpressionParser(final BiFunction<Queue<Operand>, Queue<Operator>, Operand> expressionFactory) {
        this.expressionFactory = expressionFactory;
    }

    public Operand parse(final String input) {
        return parse(input.replaceAll("\\s", "").replaceAll("(\\d+|\\W)", "$1,").split(","));
    }

    public Operand parse(final String[] splitInput) {
        ExpressionBuilder expressionBuilder = new ExpressionBuilder(expressionFactory);
        int openBrackets = 0;
        int openBracketIndex = 0;

        for (int index = 0; index < splitInput.length; index ++) {
            switch (splitInput[index]) {
                case "(": {
                    openBrackets++;
                    if (openBrackets == 1) {
                        openBracketIndex = index;
                    }
                    break;
                }
                case ")": {
                    openBrackets--;
                    if (openBrackets == 0) {
                        expressionBuilder.addOperand(parse(Arrays.copyOfRange(splitInput, openBracketIndex + 1, index)));
                    }
                    break;
                }
                case "*": {
                    if (openBrackets == 0) {
                        expressionBuilder.addOperator(Operator.MULTIPLICATION);
                    }
                    break;
                }
                case "+": {
                    if (openBrackets == 0) {
                        expressionBuilder.addOperator(Operator.ADDITION);
                    }
                    break;
                }
                default:
                    if (openBrackets == 0) {
                        expressionBuilder.addOperand(new SimpleOperand(Long.parseLong(splitInput[index])));
                    }
            }
        }
        return expressionBuilder.build();
    }


}
