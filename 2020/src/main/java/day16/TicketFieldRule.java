package day16;

import lombok.ToString;

@ToString
public class TicketFieldRule {

    private final String fieldName;
    private final int[] upperRange;
    private final int[] lowerRange;

    public TicketFieldRule(final String fieldName, final int[] lowerRange, final int[] upperRange) {
        this.fieldName = fieldName;
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean isValid(final int number) {
        return (lowerRange[0] <= number && number <= lowerRange[1])
            ||  (upperRange[0] <= number && number <= upperRange[1]);
    }
}
