package day4;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class PassportValidatorTest {

    public static final String DAY_4_TEST_DATA_TXT = "day4/testData.txt";

    @Test
    void isValid_part1_testData() throws IOException {
        Stream<String> testData = InputFileReader.readStrings(DAY_4_TEST_DATA_TXT);

        Collection<Passport> passports = PassportBatchParser.parse(testData);

        int numberValid = 0;
        for (Passport passport : passports) {
            numberValid += PassportValidator.isValidNullCheckOnly(passport)? 1 : 0;
        }

        then(numberValid).isEqualTo(2);
    }


    @Test
    void isValid_part1() throws IOException {
        Stream<String> testData = InputFileReader.readStrings("day4/input.txt");

        Collection<Passport> passports = PassportBatchParser.parse(testData);

        int numberValid = 0;
        for (Passport passport : passports) {
            numberValid += PassportValidator.isValidNullCheckOnly(passport)? 1 : 0;
        }

        then(numberValid).isEqualTo(250);
    }


    @Test
    void isValid_part2() throws IOException {
        Stream<String> testData = InputFileReader.readStrings("day4/input.txt");

        Collection<Passport> passports = PassportBatchParser.parse(testData);

        int numberValid = 0;
        for (Passport passport : passports) {
            numberValid += PassportValidator.isValid(passport)? 1 : 0;
        }

        then(numberValid).isEqualTo(158);
    }
}