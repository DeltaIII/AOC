package day16;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.*;

public class TicketDecoder {

    public static Map<String, Integer> mapFieldToIndex(final Collection<TicketFieldRule> rules,
                                                       final List<List<Integer>> validTickets) {
        Map<String, Integer> fieldToIndexMap = new HashMap<>();
        Map<TicketFieldRule, Set<Integer>> potentialIndicesMap = getPotentialIndices(rules, validTickets);

        Map<TicketFieldRule, Integer> validCombination =
            getValidCombination(potentialIndicesMap, potentialIndicesMap.keySet(), new HashSet<>());

        if (validCombination != null) {
            return validCombination.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getFieldName(), Map.Entry::getValue));
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
        return potentialIndices;
    }
}
