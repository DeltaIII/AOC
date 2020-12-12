package day12;

import day12.instruction.InstructionSet;
import java.util.List;

public class FerryNavigator {

    private final List<InstructionSet> course;

    public FerryNavigator(final List<InstructionSet> course) {
        this.course = course;
    }

    public void navigateCourse(final Ferry ferry) {
        course.forEach(instructionSet ->
            instructionSet.getInstruction().accept(ferry, instructionSet.getIncrement()));
    }
}
