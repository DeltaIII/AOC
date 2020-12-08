package day8;

import day8.instruction.BootInstruction;
import day8.instruction.Instruction;
import java.util.List;

public class Memory {

    private final List<BootInstruction> instructions;
    private int accumulator = 0;
    private int pointer = 0;

    public Memory(final List<BootInstruction> instructions) {
        this.instructions = instructions;
    }

    public boolean isEndOfMemory() {
        return this.pointer == instructions.size();
    }

    public int getPointerAddress(){
        return this.pointer;
    }

    public void incrementAddress(int steps){
        this.pointer += steps;
        checkForNegativeMemoryPointer(this.pointer, "Invalid pointer: ");
    }

    public void setPointerAddress(int newAddress){
        checkForNegativeMemoryPointer(newAddress, "Invalid pointer: ");
        this.pointer = newAddress;
    }

    public int getAccumulator() {
        return this.accumulator;
    }

    public void updateAccumulator(final int updateValue) {
        this.accumulator += updateValue;
    }

    public Instruction getNextInstruction() {
        return this.instructions.get(this.pointer);
    }

    private void checkForNegativeMemoryPointer(int address, String s) {
        if (address < 0) {
            throw new IllegalArgumentException(s + address);
        }
    }
}
