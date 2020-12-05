package day2;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class PasswordReport {

    private final BiFunction<String, String, PasswordPolicy> policyFactory;

    public PasswordReport(final BiFunction<String, String, PasswordPolicy> policyFactory) {
        this.policyFactory = policyFactory;
    }

    public long countValidPasswords(Stream<String> passwordData) {
        return passwordData.map(this::isValidPassword).filter(Boolean::booleanValue).count();
    }

    private boolean isValidPassword(String passwordData) {
        String[] splitData = passwordData.split(":");
        String password = splitData[1].trim();
        String[] policyDefinition = splitData[0].split(" ");
        PasswordPolicy passwordPolicy = policyFactory.apply(policyDefinition[0], policyDefinition[1]);
        return passwordPolicy.isValid(password);
    }
}
