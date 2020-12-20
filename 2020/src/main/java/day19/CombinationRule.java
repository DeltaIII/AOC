package day19;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CombinationRule implements MessageRule {

    private final int ruleNumber;
    private final List<List<Integer>> ruleCombinations;


    @Override
    public int getRuleNumber() {
        return ruleNumber;
    }

    @Override
    public Set<String> getValidMessages(final RuleSet ruleSet) {
        Set<String> validMessages = new HashSet<>();
        ruleCombinations.stream()
            .map(r -> this.getValidMessages(ruleSet, r))
            .forEach(validMessages::addAll);
        return validMessages;
    }

    @Override
    public boolean isValidMessage(final RuleSet ruleSet, final String message) {
        return ruleSet.getValidMessagesForRule(ruleNumber).contains(message);
    }

    private Set<String> getValidMessages(final RuleSet ruleSet, final List<Integer> ruleNumbers) {
        Set<String> validMessages = new HashSet<>();
        validMessages.add("");
        for (Integer ruleNumber : ruleNumbers) {
            Set<String> validMessagesForRule = ruleSet.getValidMessagesForRule(ruleNumber);
            validMessages = (appendRuleCombinations(validMessagesForRule, validMessages));
        }
        return validMessages;
    }

    private Set<String> appendRuleCombinations(final Set<String> validMessagesForRule,
                                               final Set<String> validMessagesForGroup) {
        Set<String> combinations = new HashSet<>();
        for (String prefix : validMessagesForGroup) {
            validMessagesForRule.forEach(m -> combinations.add(prefix + m));
        }
        return combinations;
    }


}
