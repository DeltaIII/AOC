package day8.program;

import java.util.Optional;

public class ProgramMemory {

    private int accumulator = 0;
    private int pointer = 0;
    private final int maxAddress;

    public ProgramMemory(final int maxAddress) {
        this.maxAddress = maxAddress;
    }

    public int getPointerAddress(){
        return this.pointer;
    }

    public Optional<HaltReason> incrementAddress(int steps){
        this.pointer += steps;
        return checkPointer(this.pointer);
    }

    public Optional<HaltReason> setPointerAddress(int newAddress){
        this.pointer = newAddress;
        return checkPointer(newAddress);
    }

    public int getAccumulator() {
        return this.accumulator;
    }

    public void updateAccumulator(final int updateValue) {
        this.accumulator += updateValue;
    }

    private Optional<HaltReason> checkPointer(int address) {
        if (address < 0 || address >= maxAddress) {
            return Optional.of(HaltReason.INVALID_ADDRESS);
        } else {
            return Optional.empty();
        }
    }
}
