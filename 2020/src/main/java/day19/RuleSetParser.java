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
        if (ruleDefinition.contains("|")) {
            return parseCombinationRule(ruleNumber, ruleDefinition);
        } else {
            return parseSingleRule(ruleNumber, ruleDefinition);
        }
    }

    private static MessageRule parseSingleRule(final int ruleNumber, final String ruleDefinition) {
        Matcher matcher = SINGLE_CHARACTER_PATTERN.matcher(ruleDefinition);
        if (matcher.matches()) {
            return new SingleCharacterRule(ruleNumber, matcher.group(1));
        } else {
            final List<Integer> referenceRuleNumbers = new ArrayList<>();
            for (String ruleNumberString : ruleDefinition.trim().split("\\s")) {
                referenceRuleNumbers.add(Integer.parseInt(ruleNumberString));
            }
            return new CompositeRule(ruleNumber, referenceRuleNumbers);
        }
    }

    private static MessageRule parseCombinationRule(final int ruleNumber, final String ruleDefinition) {
        List<MessageRule> rules = new ArrayList<>();
        for (String individualRuleDefinintion : ruleDefinition.split("\\|")) {
            rules.add(parseSingleRule(ruleNumber, individualRuleDefinintion));
        }
        return new CombinationRule(ruleNumber, rules);
    }
}
