package day4;


import static org.assertj.core.api.BDDAssertions.then;

import day4.fields.AlwaysValidField;
import day4.fields.EyeColourField;
import day4.fields.HeightField;
import day4.fields.PatternMatchField;
import day4.fields.YearField;
import org.junit.jupiter.api.Test;

class PassportParserTest {

    private static final String INPUT1 = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd " +
        "byr:1937 iyr:2017 cid:147 hgt:183cm";

    private static final Passport PASSPORT_1 = Passport.builder()
                                                        .eyeColour(new EyeColourField("gry"))
                                                            .id(PatternMatchField.idField("860033327"))
                                                            .expirationYear(YearField.expiryYear(2020))
                                                            .hairColour(PatternMatchField.hairColourField("#fffffd"))
                                                            .birthYear(YearField.birthYear(1937))
                                                            .issueYear(YearField.issueYear(2017))
                                                            .countryId(new AlwaysValidField<>(147L))
                                                            .height(HeightField.parse("183cm"))
                                                            .build();

    private static final String INPUT2 = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 " +
        "hcl:#cfa07d byr:1929";

    private static final Passport PASSPORT_2 = Passport.builder()
                                                        .issueYear(YearField.issueYear(2013))
                                                        .eyeColour(new EyeColourField("amb"))
                                                        .countryId(new AlwaysValidField<>(350L))
                                                        .expirationYear(YearField.expiryYear(2023))
                                                        .id(PatternMatchField.idField("028048884"))
                                                        .hairColour(PatternMatchField.hairColourField("#cfa07d"))
                                                        .birthYear(YearField.birthYear(1929))
                                                        .build();

    @Test
    void testParse_input1() {
        //When
        Passport passport = PassportParser.parse(INPUT1);

        then(passport).isEqualTo(PASSPORT_1);
    }

    @Test
    void testParse_input2() {
        //When
        Passport passport = PassportParser.parse(INPUT2);

        then(passport).isEqualTo(PASSPORT_2);
    }
}