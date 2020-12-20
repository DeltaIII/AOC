package day19;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleSetParser {

    private static final Pattern SINGLE_CHARACTER_PATTERN = Pattern.compile("\\s*\"(\\w)\"\\s*");

    public static RuleSet parse(final List<String> inputStream) {
        RuleSet ruleSet = new RuleSet();
        for (final String ruleInput : inputStream) {
            if (ruleInput.isEmpty()) {
                break;
            }
            ruleSet.addMessageRule(parseRule(ruleInput));
        }
        return ruleSet;
    }

    private static MessageRule parseRule(final String ruleInput) {
        String[] keyValuePair = ruleInput.split(":");
        int ruleNumber = Integer.parseInt(keyValuePair[0]);
        String ruleDefinition = keyValuePair[1];
        Matcher matcher = SINGLE_CHARACTER_PATTERN.matcher(ruleDefinition);
        if (matcher.matches()) {
            return new SingleCharacterRule(ruleNumber, matcher.group(1));
        } else {
            List<List<Integer>> referenceRuleCombinations = new ArrayList<>();
            for (String individualCombination : ruleDefinition.split("\\|")) {
                List<Integer> referenceRuleNumbers = new ArrayList<>();
                for (String ruleNumberString : individualCombination.trim().split("\\s")) {
                    referenceRuleNumbers.add(Integer.parseInt(ruleNumberString));
                }
                referenceRuleCombinations.add(referenceRuleNumbers);
            }
            return new CombinationRule(ruleNumber, referenceRuleCombinations);
        }
    }
}
