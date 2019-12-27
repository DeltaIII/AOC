package com.aoc.intcode.memory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Int Code memory object
 */
public class Memory {

    private List<Long> memory;
    private int pointer = 0;
    private int relativeBase = 0;
    private final Queue<Long> inputs = new LinkedList<>();
    private final List<Long> outputs = new LinkedList<>();

    public Memory(List<Long> program){
        this.memory = new ArrayList<>(program);
    }

    public List<Long> getCurrentState(){
        return new ArrayList<>(memory);
    }

    public int getPointerAddress(){
        return this.pointer;
    }

    public void incrementAddress(int steps){
        this.pointer += steps;
    }

    public void setPointerAddress(int newAddress){
        if(newAddress<0) {
            throw new IllegalArgumentException("Invalid pointer: "+ newAddress);
        }
        this.pointer = newAddress;

    }
    public boolean isEndOfMemory(){
        return this.pointer>=this.memory.size();
    }

    public Long getValueAtAddress(int address){
        return this.memory.get(address);
    }

    public void setValueAtAddress(int address, Long valueToSet){
        if(address<0 && address>=this.size()){
            throw new IllegalArgumentException("Invalid memory pointer received: "+address);
        }
        this.memory.set(address,valueToSet);
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
        return this.memory.size();
    }

    public int getRelativeBase() {
        return this.relativeBase;
    }

    public void addToRelativeBase(int valueToAdd){
        this.relativeBase += valueToAdd;
    }
}
