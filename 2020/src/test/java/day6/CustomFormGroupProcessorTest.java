package day6;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class CustomFormGroupProcessorTest {

    private static final String TEST_DATA = "day6/testData.txt";
    private static final String INPUT = "day6/input.txt";

    @Test
    void testCountUniqueQuestionsAnsweredByGroups_testData() throws IOException {
        // Given
        Collection<CustomFormGroup> formGroupInput =
            CustomFormGroupBatchParser.parse(InputFileReader.readStrings(TEST_DATA));

        // When
        int totalQuestionsAnswered = CustomFormGroupProcessor.countUniqueQuestionsAnsweredByGroups(formGroupInput);

        // Then
        then(totalQuestionsAnswered).isEqualTo(11);
    }

    @Test
    void testCountUniqueQuestionsAnsweredByGroups_part1() throws IOException {
        // Given
        Collection<CustomFormGroup> formGroupInput =
            CustomFormGroupBatchParser.parse(InputFileReader.readStrings(INPUT));

        // When
        int totalQuestionsAnswered = CustomFormGroupProcessor.countUniqueQuestionsAnsweredByGroups(formGroupInput);

        // Then
        then(totalQuestionsAnswered).isEqualTo(6534);
    }

    @Test
    void testCountQuestionsAnsweredByAllIndividualsInGroups_part2() throws IOException {
        // Given
        Collection<CustomFormGroup> formGroupInput =
            CustomFormGroupBatchParser.parse(InputFileReader.readStrings(INPUT));

        // When
        int totalQuestionsAnswered =
            CustomFormGroupProcessor.countQuestionsAnsweredByAllIndividualsInGroups(formGroupInput);

        // Then
        then(totalQuestionsAnswered).isEqualTo(3402);
    }
}