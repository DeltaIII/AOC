package com.aoc.intcode.memory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Int Code memory object
 */
public class Memory {

    private long[] memory;
    private int pointer = 0;
    private int relativeBase = 0;
    private final Queue<Long> inputs = new LinkedList<>();
    private final List<Long> outputs = new LinkedList<>();

    public Memory(List<Long> program){
        this.memory = new long[program.size()];
        for (int i=0; i<program.size(); i++){
            this.memory[i] = program.get(i);
        }
    }

    public List<Long> getCurrentState(){
        return Arrays.stream(this.memory).collect(LinkedList::new, List::add, List::addAll);
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

    public Long getValueAtAddress(int address){
        this.checkAddress(address);
        return this.memory[address];
    }

    public void setValueAtAddress(int address, Long valueToSet){
        this.checkAddress(address);
        this.memory[address] = valueToSet;
    }

    private void checkAddress(int address){
        checkForNegativeMemoryPointer(address, "Cannot have negative memory pointer: ");
        dynamicallyExpandMemoryIfNeeded(address);
    }

    private void dynamicallyExpandMemoryIfNeeded(int address) {
        if(address >= this.size()){
            int sizeMultiplier = address/this.size()+1;
            this.memory = Arrays.copyOf(memory,this.size()*sizeMultiplier);
        }
    }

    private void checkForNegativeMemoryPointer(int address, String s) {
        if (address < 0) {
            throw new IllegalArgumentException(s + address);
        }
    }

    public long getInput(){
        if(!this.inputs.isEmpty()) {
            return this.inputs.poll();
        }
        throw new IllegalStateException("No further input provided.");
    }

    public void addToInputs(Long valueToAdd){
        this.inputs.add(valueToAdd);
    }

    public List<Long> getOutputs() {
        return new LinkedList<>(this.outputs);
    }

    public void addToOutputs(Long valueToAdd){
        this.outputs.add(valueToAdd);
    }

    public int size(){
        return this.memory.length;
    }

    public int getRelativeBase() {
        return this.relativeBase;
    }

    public void addToRelativeBase(int valueToAdd){
        this.relativeBase += valueToAdd;
    }
}
