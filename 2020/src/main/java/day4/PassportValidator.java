package day4;

import day4.fields.PassportField;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class PassportValidator {

    private static final Set<Function<Passport, PassportField<?>>> MANDATORY_FIELDS = new HashSet<>();

    static {
        MANDATORY_FIELDS.add(Passport::getBirthYear);
        MANDATORY_FIELDS.add(Passport::getIssueYear);
        MANDATORY_FIELDS.add(Passport::getExpirationYear);
        MANDATORY_FIELDS.add(Passport::getHeight);
        MANDATORY_FIELDS.add(Passport::getHairColour);
        MANDATORY_FIELDS.add(Passport::getEyeColour);
        MANDATORY_FIELDS.add(Passport::getId);
    }

    /** Part 1 solution */
    public static boolean isValidNullCheckOnly(Passport passport) {
        boolean isValid = true;
        for (Function<Passport, PassportField<?>> mandatoryField : MANDATORY_FIELDS) {
            isValid = isValid && isFieldNull(passport, mandatoryField);
        }
        return MANDATORY_FIELDS.stream()
            .noneMatch(passportFieldFunction -> isFieldNull(passport, passportFieldFunction));
    }

    /** Part 2 solution */
    public static boolean isValid(Passport passport) {
        boolean isValid = true;
        for (Function<Passport, PassportField<?>> mandatoryField : MANDATORY_FIELDS) {
            isValid = isValid && isFieldValid(passport, mandatoryField);
        }
        return MANDATORY_FIELDS.stream()
            .allMatch(passportFieldFunction -> isFieldValid(passport, passportFieldFunction));
    }

    private static boolean isFieldNull(final Passport passport, final Function<Passport, PassportField<?>> mandatoryField) {
        return mandatoryField.apply(passport) == null;
    }

    private static boolean isFieldValid(final Passport passport, final Function<Passport, PassportField<?>> mandatoryField) {
        PassportField<?> passportField = mandatoryField.apply(passport);
        return passportField != null && passportField.isValid();
    }

}
