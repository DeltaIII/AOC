package day16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TicketNotesParser {

    private static final Pattern TICKET_RULE_PATTERN = Pattern.compile("([^:]+):\\s+(\\d+)-(\\d+)\\s+or\\s+(\\d+)-(\\d+)");

    public static TicketNotes parseNotes(final Stream<String> notes) {
        TicketNotes.TicketNotesBuilder ticketNotesBuilder = TicketNotes.builder();

        Iterator<String> iterator = notes.iterator();
        List<TicketFieldRule> ticketFieldRuleList = new LinkedList<>();
        while (iterator.hasNext()) {
            String ruleString = iterator.next();
            if (ruleString.isEmpty()) {
                break;
            }
            ticketFieldRuleList.add(parseTicketFieldRule(ruleString));
        }

        iterator.next(); // skip "your ticket:" line

        List<Integer> ticket = parseTicket(iterator.next());

        iterator.next();
        iterator.next(); // skip two lines to reach neary ticket data.

        List<List<Integer>> otherTickets = new LinkedList<>();
        while (iterator.hasNext()) {
            otherTickets.add(parseTicket(iterator.next()));
        }

        return ticketNotesBuilder
            .rules(ticketFieldRuleList)
            .ticket(ticket)
            .otherTickets(otherTickets)
            .build();
    }

    private static List<Integer> parseTicket(final String ticketLine) {
        List<Integer> ticketNumbers = new ArrayList<>();
        for (String ticketNumber : ticketLine.split(",")) {
            ticketNumbers.add(Integer.parseInt(ticketNumber));
        }
        return ticketNumbers;
    }

    private static TicketFieldRule parseTicketFieldRule(final String ruleString) {
        Matcher matcher = TICKET_RULE_PATTERN.matcher(ruleString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Not a rule string -> " + ruleString);
        }
        String fieldName = matcher.group(1);
        int[] lowerRange = new int[] {Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))};
        int[] upperRange = new int[] {Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5))};
        return new TicketFieldRule(fieldName, lowerRange, upperRange);
    }
}
