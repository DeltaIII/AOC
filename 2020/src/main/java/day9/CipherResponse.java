package day9;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CipherResponse {

    private boolean valid;
    private Optional<Long> invalidNumber;
    private Optional<Integer> invalidNumberIndex;
}
