package day18;

import lombok.Data;

@Data
public class SimpleOperand implements Operand {
    private final long value;
}
