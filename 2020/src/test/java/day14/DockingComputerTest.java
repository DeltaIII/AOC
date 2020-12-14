package day14;


import static org.assertj.core.api.BDDAssertions.then;

import day14.command.DockingComputerCommandParser;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class DockingComputerTest {

    private static final String TEST_DATA = "day14/testData.txt";
    private static final String INPUT = "day14/input.txt";
    private static final String TEST_DATA_PART2 = "day14/testDataPart2.txt";

    @Test
    public void testGetMemory_part1TestData() throws IOException {
        // Given
        final Stream<Consumer<DockingComputer>> commands =
            InputFileReader.readObjects(TEST_DATA, DockingComputerCommandParser::parseVersion1);

        final DockingComputer computer = new DockingComputer(36);
        commands.forEach(c -> c.accept(computer));

        // When
        Map<Long, Long> memory = computer.getMemory();

        // Then
        then(memory.size()).isEqualTo(2);
        then(memory.values().stream().reduce(0L, Long::sum)).isEqualTo(165);
    }

    @Test
    public void testGetMemory_part1() throws IOException {
        // Given
        final Stream<Consumer<DockingComputer>> commands =
            InputFileReader.readObjects(INPUT, DockingComputerCommandParser::parseVersion1);

        final DockingComputer computer = new DockingComputer(36);
        commands.forEach(c -> c.accept(computer));

        // When
        Map<Long, Long> memory = computer.getMemory();

        // Then
        then(memory.size()).isEqualTo(392);
        then(memory.values().stream().reduce(0L, Long::sum)).isEqualTo(12135523360904L);
    }

    @Test
    public void testGetMemory_part2TestData() throws IOException {
        // Given
        final Stream<Consumer<DockingComputer>> commands =
            InputFileReader.readObjects(TEST_DATA_PART2, DockingComputerCommandParser::parseVersion2);

        final DockingComputer computer = new DockingComputer(36);
        commands.forEach(c -> c.accept(computer));

        // When
        Map<Long, Long> memory = computer.getMemory();

        // Then
        then(memory.size()).isEqualTo(10);
        then(memory.values().stream().reduce(0L, Long::sum)).isEqualTo(208);
    }

    @Test
    public void testGetMemory_part2() throws IOException {
        // Given
        final Stream<Consumer<DockingComputer>> commands =
            InputFileReader.readObjects(INPUT, DockingComputerCommandParser::parseVersion2);

        final DockingComputer computer = new DockingComputer(36);
        commands.forEach(c -> c.accept(computer));

        // When
        Map<Long, Long> memory = computer.getMemory();

        // Then
        then(memory.size()).isEqualTo(77076);
        then(memory.values().stream().reduce(0L, Long::sum)).isEqualTo(2741969047858L);
    }

}