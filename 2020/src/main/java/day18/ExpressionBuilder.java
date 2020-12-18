package day18;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;

public class ExpressionBuilder {

    private final Queue<Operand> operandQueue = new LinkedList<>();
    private final Queue<Operator> operatorQueue = new LinkedList<>();
    private final BiFunction<Queue<Operand>, Queue<Operator>, Operand> expressionFactory;

    public ExpressionBuilder(final BiFunction<Queue<Operand>, Queue<Operator>, Operand> expressionBuilder) {
        this.expressionFactory = expressionBuilder;
    }

    public Operand build() {
        if (operandQueue.size() == operatorQueue.size() + 1) {
            return expressionFactory.apply(operandQueue, operatorQueue);
        }
        throw new IllegalStateException("Invalid expression! It must have 1 more operand than operators!");
    }

    public void addOperand(final Operand operand) {
        operandQueue.add(operand);
    }

    public void addOperator(final Operator operator) {
        operatorQueue.add(operator);
    }
}
