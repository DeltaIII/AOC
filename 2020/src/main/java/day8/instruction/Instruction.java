package day8.instruction;

import day8.Memory;
import java.util.function.Consumer;

public interface Instruction extends Consumer<Memory> {

    int getTimesRun();

    void reset();

}
