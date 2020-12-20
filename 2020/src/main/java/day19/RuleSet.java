package day19;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Data;

public class RuleSet {

    private final Map<Integer, MessageRule> rules = new HashMap<>();
    private final Map<Integer, Set<String>> validMessages = new HashMap<>();

    public void addMessageRule(final MessageRule rule) {
        this.rules.put(rule.getRuleNumber(), rule);
    }

    public Set<String> getValidMessagesForRule(int ruleNumber) {
        if (!rules.containsKey(ruleNumber)) {
            throw new IllegalArgumentException("Rule not part of this ruleset.");
        }
        return validMessages.computeIfAbsent(ruleNumber, n -> rules.get(n).getValidMessages(this));
    }

    public boolean isValidMessageForRule(int ruleNumber, String message) {
        if (!rules.containsKey(ruleNumber)) {
            throw new IllegalArgumentException("Rule not part of this ruleset.");
        }
        return rules.get(ruleNumber).isValidMessage(this, message);
    }
}
