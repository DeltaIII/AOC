package day16;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InvalidTicketValidator {

    public static List<List<Integer>> getValidTickets(final Collection<TicketFieldRule> ticketFields,
                                                      final List<List<Integer>> tickets) {
        Map<Integer, Boolean> isValid = new HashMap<>();
        List<List<Integer>> validTickets = new LinkedList<>(tickets);
        for (List<Integer> ticket : tickets) {
            for (Integer ticketValue : ticket) {
                Boolean isValidValue = isValid.computeIfAbsent(ticketValue, v -> isValid(v, ticketFields));
                if (!isValidValue) {
                    validTickets.remove(ticket);
                    break;
                }
            }
        }
        return validTickets;
    }

    public static List<Integer> getInvalidValues(final Collection<TicketFieldRule> ticketFields,
                                                 final List<List<Integer>> tickets) {
        Map<Integer, Boolean> isValid = new HashMap<>();
        List<Integer> invalidValues = new LinkedList<>();
        for (List<Integer> ticket : tickets) {
            for (Integer ticketValue : ticket) {
                Boolean isValidValue = isValid.computeIfAbsent(ticketValue, v -> isValid(v, ticketFields));
                if (!isValidValue) {
                    invalidValues.add(ticketValue);
                }
            }
        }
        return invalidValues;
    }

    private static boolean isValid(final Integer ticketValue, final Collection<TicketFieldRule> ticketFields) {
        return ticketFields.stream().anyMatch(r -> r.isValid(ticketValue));
    }

}
