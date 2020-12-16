package day16;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TicketNotes {

    private List<TicketFieldRule> rules;
    private List<Integer> ticket;
    private List<List<Integer>> otherTickets;
}
