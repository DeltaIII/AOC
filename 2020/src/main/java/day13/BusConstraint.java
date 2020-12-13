package day13;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class BusConstraint {
    private final int busId;
    private final int referenceBusId;
    private final int constraintMinutes;

    public boolean isTimeValid(final long timeStamp) {
        return (timeStamp + constraintMinutes) % busId == 0 && timeStamp % referenceBusId == 0;
    }
}
