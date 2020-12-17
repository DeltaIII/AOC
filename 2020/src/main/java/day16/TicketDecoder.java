package day16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDecoder {

    public static Map<String, Integer> mapFieldToIndex(final Collection<TicketFieldRule> rules,
                                                       final List<List<Integer>> validTickets) {
        long start = System.nanoTime();
        Map<String, Integer> fieldToIndexMap = new HashMap<>();
        Map<TicketFieldRule, Set<Integer>> potentialIndicesMap = getPotentialIndices(rules, validTickets);

        Map<TicketFieldRule, Integer> validCombination =
            getValidCombination(potentialIndicesMap, potentialIndicesMap.keySet(), new HashSet<>());

        if (validCombination != null) {
            Map<String, Integer> collect = validCombination.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getFieldName(), Map.Entry::getValue));
            System.out.println("time = " + (System.nanoTime()-start)/1000000.0 + "ms");
            return collect;
        }

        throw new IllegalArgumentException("No valid combination found :(");
    }

    private static Map<TicketFieldRule, Integer> getValidCombination(
                                    final Map<TicketFieldRule, Set<Integer>> possibleIndices,
                                    final Set<TicketFieldRule> remainingRules,
                                    final Set<Integer> previouslyUsedIndices) {
        final TicketFieldRule nextRule = remainingRules.iterator().next();
        final Set<Integer> possibleRuleIndices = possibleIndices.get(nextRule);
        final Set<TicketFieldRule> otherRules = new HashSet<>(remainingRules);
        otherRules.remove(nextRule);
        for (Integer index : possibleRuleIndices) {
            if (previouslyUsedIndices.contains(index)) {
                continue;
            }
            if (otherRules.isEmpty()) {
                Map<TicketFieldRule, Integer> validCombination = new HashMap<>();
                validCombination.put(nextRule, index);
                return validCombination;
            }
            Set<Integer> combinationIndices = new HashSet<>(previouslyUsedIndices);
            combinationIndices.add(index);
            Map<TicketFieldRule, Integer> validCombination =
                getValidCombination(possibleIndices, otherRules, combinationIndices);
            if (validCombination != null && validCombination.keySet().equals(otherRules)) {
                validCombination.put(nextRule, index);
                return validCombination;
            }
        }
        return null;
    }

    private static Map<TicketFieldRule, Set<Integer>> getPotentialIndices(final Collection<TicketFieldRule> rules, final List<List<Integer>> validTickets) {
        Map<TicketFieldRule, Set<Integer>> potentialIndices = new HashMap<>();
        for (int index = 0; index < rules.size(); index++) {
            final int finalIndex = index;
            for (TicketFieldRule rule : rules) {
                if (validTickets.stream().allMatch(t -> rule.isValid(t.get(finalIndex)))) {
                    potentialIndices.computeIfAbsent(rule, r -> new HashSet<>()).add(index);
                }
            }
        }
        Map<TicketFieldRule, Set<Integer>> sortedPotentialIndices = new LinkedHashMap<>();
        potentialIndices.entrySet().stream()
            .sorted(Comparator.comparingInt(e -> e.getValue().size()))
            .forEach(e -> sortedPotentialIndices.put(e.getKey(), e.getValue()));
        return sortedPotentialIndices;
    }
}
