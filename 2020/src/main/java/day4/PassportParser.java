package day4;

import day4.fields.AlwaysValidField;
import day4.fields.EyeColourField;
import day4.fields.HeightField;
import day4.fields.PatternMatchField;
import day4.fields.InvalidField;
import day4.fields.PassportField;
import day4.fields.YearField;
import java.util.function.Function;

public class PassportParser {

    public static Passport parse(String passportString){
        String[] keyValuePairs = passportString.split("\\s");
        Passport.PassportBuilder passportBuilder = Passport.builder();

        for (String keyValuePairCombined : keyValuePairs) {
            String[] keyValuePair = keyValuePairCombined.trim().split(":");
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            switch (key) {
                case "pid":
                    passportBuilder.id(PatternMatchField.idField(value));
                    break;
                case "cid":
                    passportBuilder.countryId(parseCountryId(value));
                    break;
                case "byr":
                    passportBuilder.birthYear(parseYear(value, YearField::birthYear));
                    break;
                case "iyr":
                    passportBuilder.issueYear(parseYear(value, YearField::issueYear));
                    break;
                case "eyr":
                    passportBuilder.expirationYear(parseYear(value, YearField::expiryYear));
                    break;
                case "ecl":
                    passportBuilder.eyeColour(new EyeColourField(value));
                    break;
                case "hcl":
                    passportBuilder.hairColour(PatternMatchField.hairColourField(value));
                    break;
                case "hgt":
                    passportBuilder.height(HeightField.parse(value));
                    break;
                default:
                    throw new IllegalArgumentException("Bad input field: <" + key + ">");
            }
        }

        return passportBuilder.build();
    }

    private static AlwaysValidField<Long> parseCountryId(String idString) {
        Long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            id = null;
        }
        return new AlwaysValidField<>(id);
    }

    private static PassportField<Integer> parseYear(String yearString, Function<Integer, PassportField<Integer>> factory) {
        try {
            int year = Integer.parseInt(yearString);
            return factory.apply(year);
        } catch (NumberFormatException ex) {
            return new InvalidField<>();
        }

    }

}
