package day5;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class BoardingPassProcessorTest {

    private static final String INPUT = "day5/input.txt";

    @Test
    void getLargestIdPass() throws IOException {
        // Given
        Stream<BoardingPass> boardingPassStream = BinarySpaceBoardingPassBatchParser.parseBatch(INPUT);

        // When
        BoardingPass largestIdPass = BoardingPassProcessor.getLargestIdPass(boardingPassStream);

        //Then
        then(largestIdPass.getId()).isEqualTo(978);
    }

    @Test
    void testFindMissingBoardingPassId() throws IOException {
        // Given
        Stream<BoardingPass> boardingPassStream = BinarySpaceBoardingPassBatchParser.parseBatch(INPUT);

        // When
        int missingID = BoardingPassProcessor.findMissingBoardingPassId(boardingPassStream);

        //Then
        then(missingID).isEqualTo(727);

    }
}