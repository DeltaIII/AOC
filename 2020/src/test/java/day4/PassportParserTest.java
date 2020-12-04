package day4;


import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

class PassportParserTest {

    private static final String INPUT1 = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd " +
        "byr:1937 iyr:2017 cid:147 hgt:183cm";

    private static final Passport PASSPORT_1 = Passport.builder()
                                                        .eyeColour("gry")
                                                            .id(860033327L)
                                                            .expirationYear(2020)
                                                            .hairColour("#fffffd")
                                                            .birthYear(1937)
                                                            .issueYear(2017)
                                                            .countryId(147L)
                                                            .height("183cm")
                                                            .build();

    private static final String INPUT2 = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 " +
        "hcl:#cfa07d byr:1929";

    private static final Passport PASSPORT_2 = Passport.builder()
                                                        .issueYear(2013)
                                                        .eyeColour("amb")
                                                        .countryId(350L)
                                                        .expirationYear(2023)
                                                        .id(28048884L)
                                                        .hairColour("#cfa07d")
                                                        .birthYear(1929)
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