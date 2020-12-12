package day12;

import static org.assertj.core.api.BDDAssertions.then;

import day12.instruction.InstructionSet;
import day12.instruction.InstructionSetParser;
import direction.ManhattanDirection;
import direction.ManhattanPoint;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class FerryNavigatorTest {

    private static final String TEST_DATA = "day12/testData.txt";
    private static final String INPUT = "day12/input.txt";

    @Test
    public void testNavigateCourse_testData_part1() throws IOException {
        // Given
        final InstructionSetParser parser = new InstructionSetParser(InstructionSetParser.PART1_INSTRUCTION_MAP);
        final List<InstructionSet> course =
            InputFileReader.readObjects(TEST_DATA, parser::parse).collect(Collectors.toList());
        final FerryNavigator navigator = new FerryNavigator(course);
        final Ferry ferry = new Ferry(new ManhattanPoint(ManhattanDirection.EAST), 0, 0);

        // When
        navigator.navigateCourse(ferry);

        // Then
        int xOrdinate = ferry.getxOrdinate();
        then(xOrdinate).isEqualTo(17);
        int yOrdinate = ferry.getyOrdinate();
        then(yOrdinate).isEqualTo(-8);
        then(Math.abs(xOrdinate) + Math.abs(yOrdinate)).isEqualTo(25);
    }

    @Test
    public void testNavigateCourse_part1() throws IOException {
        // Given
        final InstructionSetParser parser = new InstructionSetParser(InstructionSetParser.PART1_INSTRUCTION_MAP);
        final List<InstructionSet> course =
            InputFileReader.readObjects(INPUT, parser::parse).collect(Collectors.toList());
        final FerryNavigator navigator = new FerryNavigator(course);
        final Ferry ferry = new Ferry(new ManhattanPoint(ManhattanDirection.EAST), 0, 0);

        // When
        navigator.navigateCourse(ferry);

        // Then
        int xOrdinate = ferry.getxOrdinate();
        then(xOrdinate).isEqualTo(-655);
        int yOrdinate = ferry.getyOrdinate();
        then(yOrdinate).isEqualTo(-934);
        then(Math.abs(xOrdinate) + Math.abs(yOrdinate)).isEqualTo(1589);
    }
    @Test
    public void testNavigateCourse_testData_part2() throws IOException {
        // Given
        final InstructionSetParser parser = new InstructionSetParser(InstructionSetParser.PART2_INSTRUCTION_MAP);
        final List<InstructionSet> course =
            InputFileReader.readObjects(TEST_DATA, parser::parse).collect(Collectors.toList());
        final FerryNavigator navigator = new FerryNavigator(course);
        final ManhattanPoint startingWaypoint = new ManhattanPoint(10, 1);
        final Ferry ferry = new Ferry(startingWaypoint, 0, 0);

        // When
        navigator.navigateCourse(ferry);

        // Then
        int xOrdinate = ferry.getxOrdinate();
        then(xOrdinate).isEqualTo(214);
        int yOrdinate = ferry.getyOrdinate();
        then(yOrdinate).isEqualTo(-72);
        then(Math.abs(xOrdinate) + Math.abs(yOrdinate)).isEqualTo(286);
    }

    @Test
    public void testNavigateCourse_part2() throws IOException {
        // Given
        final InstructionSetParser parser = new InstructionSetParser(InstructionSetParser.PART2_INSTRUCTION_MAP);
        final List<InstructionSet> course =
            InputFileReader.readObjects(INPUT, parser::parse).collect(Collectors.toList());
        final FerryNavigator navigator = new FerryNavigator(course);
        final ManhattanPoint startingWaypoint = new ManhattanPoint(10, 1);
        final Ferry ferry = new Ferry(startingWaypoint, 0, 0);

        // When
        navigator.navigateCourse(ferry);

        // Then
        int xOrdinate = ferry.getxOrdinate();
        then(xOrdinate).isEqualTo(17379);
        int yOrdinate = ferry.getyOrdinate();
        then(yOrdinate).isEqualTo(6581);
        then(Math.abs(xOrdinate) + Math.abs(yOrdinate)).isEqualTo(23960);
    }

}