package com.aoc.intcode.memory;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Int Code memory object
 */
public class Memory {

    private List<Integer> memory;
    private int pointer = 0;
    private final Queue<Integer> inputs = new LinkedList<>();
    private final List<Integer> outputs = new LinkedList<>();

    public Memory(List<Integer> initialState){
        this.memory = new ArrayList<>(initialState);
    }

    public List<Integer> getCurrentState(){
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

    public Integer getValueAtAddress(int address){
        return this.memory.get(address);
    }

    public int getInput(){
        if(!this.inputs.isEmpty()) {
            return this.inputs.poll();
        }
        throw new IllegalStateException("No further input provided.");
    }

    public void addToInputs(Integer valueToAdd){
        this.inputs.add(valueToAdd);
    }

    public List<Integer> getOutputs() {
        return new LinkedList<>(this.outputs);
    }

    public void addToOutputs(Integer valueToAdd){
        this.outputs.add(valueToAdd);
    }

    public void setValueAtAddress(int address, Integer valueToSet){
        this.memory.set(address,valueToSet);
    }

    public int size(){
        return this.memory.size();
    }

}
