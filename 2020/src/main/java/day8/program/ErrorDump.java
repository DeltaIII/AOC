package day8.program;

import day8.instruction.Instruction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDump {
    private Instruction erroneousInstruction;
    private int instructionNumber;
    private ProgramMemory memory;
}
