package day7;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BagRuleProcessor {

    public static int countPossibleOuterTypesForColour(final String colour,
                                                       final Collection<BaggageType> baggageTypes) {
        int count = 0;
        for (BaggageType baggageType : baggageTypes) {
            if (canContain(baggageType, colour)) {
                count++;
            }
        }
        return count;
    }

    public static int countMaxTotalContent(final BaggageType baggageType) {
        return countMaxTotalContent(baggageType, new HashSet<>(), new HashMap<>());
    }

    private static int countMaxTotalContent(final BaggageType baggageType,
                                            final Set<BaggageType> seenTypes,
                                            final Map<BaggageType, Integer> maxContentTypeMap) {
        int count = 0;
        seenTypes.add(baggageType);
        for (BagRule bagRule : baggageType.getBagRules()) {
            BaggageType ruleBaggageType = bagRule.getBaggageType();

            // Break infinite loops
            if (seenTypes.contains(ruleBaggageType)) {
                throw new IllegalArgumentException("Infinite loop in this branch!");
            }

            // Cache known limits by BaggageType
            if (!maxContentTypeMap.containsKey(ruleBaggageType)) {
                Set<BaggageType> typesInBranch = new HashSet<>();
                typesInBranch.addAll(seenTypes);
                maxContentTypeMap.put(
                    ruleBaggageType,
                    countMaxTotalContent(ruleBaggageType, typesInBranch, maxContentTypeMap));
            }

            // Count nested bags
            count += bagRule.getLimit() * (1 + maxContentTypeMap.get(ruleBaggageType));

        }
        maxContentTypeMap.put(baggageType, count);
        return count;
    }


    private static boolean canContain(final BaggageType type, final String colour) {
        if (type.getRuleForColour(colour).isPresent()) {
            return true;
        } else {
            return type.getBagRules().stream()
                .map(BagRule::getBaggageType)
                .anyMatch(t -> canContain(t, colour));
        }
    }
}
