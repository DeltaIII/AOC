package day4;

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
                    passportBuilder.id(parseId(value));
                    break;
                case "cid":
                    passportBuilder.countryId(parseId(value));
                    break;
                case "byr":
                    passportBuilder.birthYear(parseYear(value));
                    break;
                case "iyr":
                    passportBuilder.issueYear(parseYear(value));
                    break;
                case "eyr":
                    passportBuilder.expirationYear(parseYear(value));
                    break;
                case "ecl":
                    passportBuilder.eyeColour(value);
                    break;
                case "hcl":
                    passportBuilder.hairColour(value);
                    break;
                case "hgt":
                    passportBuilder.height(value);
                    break;
                default:
                    throw new IllegalArgumentException("Bad input field: <" + key + ">");
            }
        }

        return passportBuilder.build();
    }

    private static Long parseId(String idString) {
        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return -1L;
        }
    }

    private static int parseYear(String yearString) {
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException ex) {
            return -1;
        }

    }

}
