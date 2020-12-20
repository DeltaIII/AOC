package day19;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class RuleSet {

    private final Map<Integer, MessageRule> rules = new HashMap<>();
    private final Map<Integer, String> rawPatterns = new HashMap<>();
    private final Map<Integer, Pattern> compiledPatterns = new HashMap<>();

    public void addMessageRule(final MessageRule rule) {
        this.rules.put(rule.getRuleNumber(), rule);
    }

    public String getRawPatternForRule(int ruleNumber) {
        if (!rules.containsKey(ruleNumber)) {
            throw new IllegalArgumentException("Rule not part of this ruleset.");
        }
        return rawPatterns.computeIfAbsent(ruleNumber, n -> rules.get(n).getRawPattern(this));
    }

    public boolean isValidMessageForRule(int ruleNumber, String message) {
        if (!rules.containsKey(ruleNumber)) {
            throw new IllegalArgumentException("Rule not part of this ruleset.");
        }
        return compiledPatterns
            .computeIfAbsent(ruleNumber, r -> compilePattern(getRawPatternForRule(ruleNumber)))
            .matcher(message).matches();
    }

    public void setRule(final MessageRule rule) {
        this.rules.put(rule.getRuleNumber(), rule);
        this.rawPatterns.clear();
        this.compiledPatterns.clear();
    }

    private Pattern compilePattern(final String rawPattern) {
        String regex = "^" + rawPattern + "$";
        System.out.println(regex);
        return Pattern.compile(regex);
    }
}
