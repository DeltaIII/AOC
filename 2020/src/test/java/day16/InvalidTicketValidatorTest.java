package day16;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class InvalidTicketValidatorTest {

    private static final String TEST_DATA = "day16/testData.txt";
    private static final String INPUT = "day16/input.txt";

    @Test
    void testIsValid_testData() throws IOException {
        // Given
        TicketNotes notes = TicketNotesParser.parseNotes(InputFileReader.readStrings(TEST_DATA));

        // When
        List<Integer> invalids = InvalidTicketValidator.getInvalidValues(notes.getRules(), notes.getOtherTickets());
        Integer sumOfInvalidValues = invalids.stream().reduce(0, Integer::sum);

        // Then
        then(sumOfInvalidValues).isEqualTo(71);
    }

    @Test
    void testIsValid_input() throws IOException {
        // Given
        TicketNotes notes = TicketNotesParser.parseNotes(InputFileReader.readStrings(INPUT));

        // When
        List<Integer> invalids = InvalidTicketValidator.getInvalidValues(notes.getRules(), notes.getOtherTickets());
        Integer sumOfInvalidValues = invalids.stream().reduce(0, Integer::sum);

        // Then
        then(sumOfInvalidValues).isEqualTo(23954);
    }
}