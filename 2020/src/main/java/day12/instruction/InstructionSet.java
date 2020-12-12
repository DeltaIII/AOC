package day12.instruction;

import day12.Ferry;
import java.util.function.BiConsumer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InstructionSet {
    private BiConsumer<Ferry, Integer> instruction;
    private int increment;
}
