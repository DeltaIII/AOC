package day18;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdvancedExpression implements Operand {

    private static final List<Operator> PRECEDANCE = Arrays.asList(Operator.ADDITION, Operator.MULTIPLICATION);

    private final Queue<Operand> operandQueue;
    private final Queue<Operator> operatorQueue;
    private Long value;

    public AdvancedExpression(final Queue<Operand> operandQueue, final Queue<Operator> operatorQueue) {
        this.operandQueue = operandQueue;
        this.operatorQueue = operatorQueue;
    }

    @Override
    public long getValue() {
        if (value == null) {
            evaluateExpression();
        }
        return value;
    }

    private void evaluateExpression() {
        Queue<Operand> operands = new LinkedList<>(operandQueue);
        Queue<Operator> operators = new LinkedList<>(operatorQueue);
        for (Operator operatorToEvaluate : PRECEDANCE) {
            Operand currentOperand = operands.peek() != null? operands.poll() : new SimpleOperand(0L); //handle single value and empty expressions
            Queue<Operand> remainingOperands = new LinkedList<>();
            Queue<Operator> remainingOperators = new LinkedList<>();
            while (operands.peek() != null && operators.peek() != null) {
                Operand nextOperand = operands.poll();
                Operator operator = operators.poll();
                if (operator == operatorToEvaluate) {
                    currentOperand = operator.apply(currentOperand, nextOperand);
                } else {
                    remainingOperands.add(currentOperand);
                    remainingOperators.add(operator);
                    currentOperand = nextOperand;
                }
            }

            operands = remainingOperands;
            operands.add(currentOperand);
            operators = remainingOperators;
        }
        value = operands.peek() != null? operands.poll().getValue() : 0;
    }
}
