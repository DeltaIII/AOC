package day4;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class PassportValidator {

    private static final Set<Function<Passport, Object>> MANDATORY_FIELDS = new HashSet<>();

    static {
        MANDATORY_FIELDS.add(Passport::getBirthYear);
        MANDATORY_FIELDS.add(Passport::getIssueYear);
        MANDATORY_FIELDS.add(Passport::getExpirationYear);
        MANDATORY_FIELDS.add(Passport::getHeight);
        MANDATORY_FIELDS.add(Passport::getHairColour);
        MANDATORY_FIELDS.add(Passport::getEyeColour);
        MANDATORY_FIELDS.add(Passport::getId);
    }

    public static boolean isValid(Passport passport) {
        boolean isValid = true;
        for (Function<Passport, Object> mandatoryField : MANDATORY_FIELDS) {
            Object apply = mandatoryField.apply(passport);
            isValid = isValid && apply != null;
        }
        return isValid;
    }
}
