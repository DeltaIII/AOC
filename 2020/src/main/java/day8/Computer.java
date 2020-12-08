package day8;

import day8.instruction.Instruction;
import day8.program.Program;
import day8.program.ProgramResult;
import day8.program.ProgramMemory;
import day8.program.SingleRunProgram;
import java.util.List;

public class Computer {

    public ProgramResult runProgramToCompletion(List<Instruction> programInstructions) {
        Program program = new SingleRunProgram(programInstructions);
        return program.run();
    }

}
