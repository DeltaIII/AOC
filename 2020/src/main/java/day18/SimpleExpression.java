package day18;

import java.util.Queue;

public class SimpleExpression implements Operand {

    private final Queue<Operand> operandQueue;
    private final Queue<Operator> operatorQueue;
    private Long value;

    public SimpleExpression(final Queue<Operand> operandQueue, final Queue<Operator> operatorQueue) {
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
        Operand currentOperand = operandQueue.peek() != null? operandQueue.poll() : new SimpleOperand(0L); //handle single value and empty expressions
        while (operandQueue.peek() != null && operatorQueue.peek() != null) {
            Operand nextOperand = operandQueue.poll();
            Operator operator = operatorQueue.poll();
            currentOperand = operator.apply(currentOperand, nextOperand);
        }
        value = currentOperand.getValue();
    }

}
