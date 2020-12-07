package day7;

import java.util.HashMap;
import java.util.Map;

public class BaggageTypeParser {

    private Map<String, BaggageType> parsedTypes = new HashMap<>();

    public BaggageType parseIndividualType(final String inputRule) {
        final String[] colourRulePair = inputRule.split("bags contain");
        final String colour = colourRulePair[0].trim();
        BaggageType baggageType = parsedTypes.computeIfAbsent(colour, BaggageType::new);

        final String rules = tidyUpRules(colourRulePair[1]);
        if (!rules.isEmpty()) {
            parseBagRules(baggageType, rules);
        }
        return baggageType;
    }

    private void parseBagRules(final BaggageType baggageType, final String rules) {
        final String[] individualBagRules = rules.split(",");
        for (String individualBagRule : individualBagRules) {
            String[] numberColourPair = individualBagRule.replaceAll("(\\d+)", "$1:").split(":");
            int limit = Integer.parseInt(numberColourPair[0].trim());
            String typeColour = numberColourPair[1].trim();

            baggageType.addBagRule(parsedTypes.computeIfAbsent(typeColour, BaggageType::new), limit);
        }
    }

    private static String tidyUpRules(final String rawRuleString) {
        return rawRuleString.trim()
            .replace("no other bags.", "") // Empty rule
            .replaceAll("\\.", "") // Period dirties input
            .replaceAll("bags", "").replaceAll("bag", ""); // bag and bags are irrelavant to input
    }
}
