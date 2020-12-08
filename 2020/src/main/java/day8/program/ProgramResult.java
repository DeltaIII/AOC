package day8.program;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgramResult {
    private final HaltReason haltReason;
    private final int accumulatorResult;
    private Optional<ErrorDump> errorDump;
}
