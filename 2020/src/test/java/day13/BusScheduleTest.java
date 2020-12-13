package day13;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class BusScheduleTest {

    private static final String TEST_DATA = "day13/testData.txt";
    private static final String INPUT = "day13/input.txt";

    @Test
    void testGetEarliestBusTime_testData() throws IOException {
        // Given
        List<String> inputs = InputFileReader.readStrings(TEST_DATA).collect(Collectors.toList());
        Integer timeStamp = Integer.parseInt(inputs.get(0));
        SortedSet<Integer> schedule = ScheduleParser.parse(inputs.get(1));
        BusSchedule busSchedule = new BusSchedule(schedule);

        //When
        BusTime earliestBusTime = busSchedule.getEarliestBusTime(timeStamp);

        // Then
        then(earliestBusTime).isNotNull();
        then(earliestBusTime.getId()).isEqualTo(59);
        then(earliestBusTime.getTime()).isEqualTo(944);
        then(getBusVerficiationNumber(timeStamp, earliestBusTime)).isEqualTo(295);
    }

    @Test
    void testGetEarliestBusTime_part1() throws IOException {
        // Given
        List<String> inputs = InputFileReader.readStrings(INPUT).collect(Collectors.toList());
        Integer timeStamp = Integer.parseInt(inputs.get(0));
        SortedSet<Integer> schedule = ScheduleParser.parse(inputs.get(1));
        BusSchedule busSchedule = new BusSchedule(schedule);

        //When
        BusTime earliestBusTime = busSchedule.getEarliestBusTime(timeStamp);

        // Then
        then(earliestBusTime).isNotNull();
        then(earliestBusTime.getId()).isEqualTo(863);
        then(earliestBusTime.getTime()).isEqualTo(1001943);
        then(getBusVerficiationNumber(timeStamp, earliestBusTime)).isEqualTo(4315);
    }

    private static int getBusVerficiationNumber(final int timeStamp, final BusTime busTime) {
        int wait = busTime.getTime() - timeStamp;
        return wait * busTime.getId();
    }
}