package day16;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class TicketDecoderTest {

    private static final String TEST_DATA = "day16/part2TestData.txt";
    private static final String INPUT = "day16/input.txt";

    @Test
    void testMapFieldToIndex_testData() throws IOException {
        //Given
        TicketNotes notes = TicketNotesParser.parseNotes(InputFileReader.readStrings(TEST_DATA));
        List<List<Integer>> validTickets =
            InvalidTicketValidator.getValidTickets(notes.getRules(), notes.getOtherTickets());

        // When
        Map<String, Integer> ruleToIndexMap =
            TicketDecoder.mapFieldToIndex(notes.getRules(), validTickets);

        // Then
        then(ruleToIndexMap.get("row")).isEqualTo(0);
        then(ruleToIndexMap.get("class")).isEqualTo(1);
        then(ruleToIndexMap.get("seat")).isEqualTo(2);
    }



    @Test
    void testMapFieldToIndex_part2() throws IOException {
        //Given
        TicketNotes notes = TicketNotesParser.parseNotes(InputFileReader.readStrings(INPUT));
        List<List<Integer>> validTickets =
            InvalidTicketValidator.getValidTickets(notes.getRules(), notes.getOtherTickets());

        // When
        Map<String, Integer> ruleToIndexMap =
            TicketDecoder.mapFieldToIndex(notes.getRules(), validTickets);


        Map<String, Integer> departureIndices = ruleToIndexMap.entrySet().stream()
            .filter(e -> e.getKey().startsWith("departure")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));




        // Then
        then(departureIndices.size()).isEqualTo(6);
        long departureProduct = departureIndices.entrySet().stream()
            .map(e -> e.getValue())
            .map(i -> (long) notes.getTicket().get(i))
            .reduce(1L, (i1,i2) -> i1*i2);
        then(departureProduct).isEqualTo(453459307723L);
    }
}