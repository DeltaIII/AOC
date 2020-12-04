package day4;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import day4.fields.AlwaysValidField;
import day4.fields.EyeColourField;
import day4.fields.HeightField;
import day4.fields.PatternMatchField;
import day4.fields.YearField;
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
            .eyeColour(new EyeColourField("gry"))
            .id(PatternMatchField.idField("860033327"))
            .expirationYear(YearField.expiryYear(2020))
            .hairColour(PatternMatchField.hairColourField("#fffffd"))
            .birthYear(YearField.birthYear(1937))
            .issueYear(YearField.issueYear(2017))
            .countryId(new AlwaysValidField<>(147L))
            .height(HeightField.parse("183cm"))
            .build());
        expectedPassports.add(Passport.builder()
            .issueYear(YearField.issueYear(2013))
            .eyeColour(new EyeColourField("amb"))
            .countryId(new AlwaysValidField<>(350L))
            .expirationYear(YearField.expiryYear(2023))
            .id(PatternMatchField.idField("028048884"))
            .hairColour(PatternMatchField.hairColourField("#cfa07d"))
            .birthYear(YearField.birthYear(1929))
            .build());
        expectedPassports.add(Passport.builder()
            .hairColour(PatternMatchField.hairColourField("#ae17e1"))
            .issueYear(YearField.issueYear(2013))
            .expirationYear(YearField.expiryYear(2024))
            .eyeColour(new EyeColourField("brn"))
            .id(PatternMatchField.idField("760753108"))
            .birthYear(YearField.birthYear(1931))
            .height(HeightField.parse("179cm"))
            .build());
        expectedPassports.add(Passport.builder()
            .hairColour(PatternMatchField.hairColourField("#cfa07d"))
            .expirationYear(YearField.expiryYear(2025))
            .id(PatternMatchField.idField("166559648"))
            .issueYear(YearField.issueYear(2011))
            .eyeColour(new EyeColourField("brn"))
            .height(HeightField.parse("59in"))
            .build());

        // When
        Collection<Passport> passports = PassportBatchParser.parse(input);

        then(passports).isNotNull();
        then(passports).isEqualTo(expectedPassports);
    }
}