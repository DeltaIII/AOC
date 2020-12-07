package day7;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class BagRuleProcessorTest {

    public static final String TEST_DATA_TXT = "day7/testData.txt";
    public static final String INPUT_TXT = "day7/input.txt";
    public static final String PART2_TEST_DATA_TXT = "day7/part2TestData.txt";

    @Test
    void testCountPossibleOuterTypesForColour_testData() throws IOException {
        // Given
        BaggageTypeParser parser = new BaggageTypeParser();
        List<BaggageType> baggageTypes =
            InputFileReader.readObjects(TEST_DATA_TXT, parser::parseIndividualType).collect(Collectors.toList());

        String colour = "shiny gold";

        // When
        int outerTypesForColour = BagRuleProcessor.countPossibleOuterTypesForColour(colour, baggageTypes);

        // Then
        then(outerTypesForColour).isEqualTo(4);
    }

    @Test
    void testCountPossibleOuterTypesForColour_part1Input() throws IOException {
        // Given
        BaggageTypeParser parser = new BaggageTypeParser();
        List<BaggageType> baggageTypes =
            InputFileReader.readObjects(INPUT_TXT, parser::parseIndividualType).collect(Collectors.toList());

        String colour = "shiny gold";

        // When
        int outerTypesForColour = BagRuleProcessor.countPossibleOuterTypesForColour(colour, baggageTypes);

        // Then
        then(outerTypesForColour).isEqualTo(378);
    }

    @Test
    void testCountMaxTotalContent_part2Input() throws IOException {
        // Given
        BaggageTypeParser parser = new BaggageTypeParser();
        List<BaggageType> baggageTypes =
            InputFileReader.readObjects(INPUT_TXT, parser::parseIndividualType).collect(Collectors.toList());

        String colour = "shiny gold";
        Optional<BaggageType> shinyGoldBagType = baggageTypes.stream().filter(t -> t.getColour().equals(colour)).findFirst();
        if (!shinyGoldBagType.isPresent()) {
            throw new IllegalStateException("Test state invalid");
        }

        // When
        int outerTypesForColour = BagRuleProcessor.countMaxTotalContent(shinyGoldBagType.get());

        // Then
        then(outerTypesForColour).isEqualTo(27526);
    }

    @Test
    void testCountMaxTotalContent_part2testData() throws IOException {
        // Given
        BaggageTypeParser parser = new BaggageTypeParser();
        List<BaggageType> baggageTypes =
            InputFileReader.readObjects(PART2_TEST_DATA_TXT, parser::parseIndividualType).collect(Collectors.toList());

        String colour = "shiny gold";
        Optional<BaggageType> shinyGoldBagType = baggageTypes.stream().filter(t -> t.getColour().equals(colour)).findFirst();
        if (!shinyGoldBagType.isPresent()) {
            throw new IllegalStateException("Test state invalid");
        }

        // When
        int outerTypesForColour = BagRuleProcessor.countMaxTotalContent(shinyGoldBagType.get());

        // Then
        then(outerTypesForColour).isEqualTo(126);
    }
}