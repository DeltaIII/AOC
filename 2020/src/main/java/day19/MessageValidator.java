package day19;

public class MessageValidator {

    private final RuleSet ruleSet;

    public MessageValidator(final RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public boolean matchesRule(final int ruleNumber, final String message) {
        return ruleSet.isValidMessageForRule(ruleNumber, message);
    }
}
