package day18;

import java.util.function.BiFunction;

public enum Operator implements BiFunction<Operand, Operand, Operand> {

    ADDITION(Long::sum), MULTIPLICATION((l1, l2) -> l1*l2);

    private final BiFunction<Long, Long, Long> operation;

    Operator(final BiFunction<Long, Long, Long> operation) {
        this.operation = operation;
    }

    @Override
    public Operand apply(final Operand firstOperand, final Operand otherOperand) {
        return new SimpleOperand(operation.apply(firstOperand.getValue(), otherOperand.getValue()));
    }
}
