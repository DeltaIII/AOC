package day4;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import util.InputFileReader;

class PassportBatchParserTest {

    @Test
    void testParse() throws IOException {
        Stream<String> input = InputFileReader.readStrings("day4/testData.txt");

        List<Passport> expectedPassports = new LinkedList<>();
        expectedPassports.add(Passport.builder()
            .eyeColour("gry")
            .id(860033327L)
            .expirationYear(2020)
            .hairColour("#fffffd")
            .birthYear(1937)
            .issueYear(2017)
            .countryId(147L)
            .height("183cm")
            .build());
        expectedPassports.add(Passport.builder()
            .issueYear(2013)
            .eyeColour("amb")
            .countryId(350L)
            .expirationYear(2023)
            .id(28048884L)
            .hairColour("#cfa07d")
            .birthYear(1929)
            .build());
        expectedPassports.add(Passport.builder()
            .hairColour("#ae17e1")
            .issueYear(2013)
            .expirationYear(2024)
            .eyeColour("brn")
            .id(760753108L)
            .birthYear(1931)
            .height("179cm")
            .build());
        expectedPassports.add(Passport.builder()
            .hairColour("#cfa07d")
            .expirationYear(2025)
            .id(166559648L)
            .issueYear(2011)
            .eyeColour("brn")
            .height("59in")
            .build());

        // When
        Collection<Passport> passports = PassportBatchParser.parse(input);

        then(passports).isNotNull();
        then(passports).isEqualTo(expectedPassports);
    }
}