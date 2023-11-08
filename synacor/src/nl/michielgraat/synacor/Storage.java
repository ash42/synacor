package nl.michielgraat.synacor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import nl.michielgraat.synacor.operations.Operation;
import nl.michielgraat.synacor.operations.OperationFactory;

public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MEMORY_SIZE = 32768;
    private static final int NR_REGISTERS = 8;

    private int[] memory;
    private int[] registers;
    private Deque<Integer> stack;
    private Queue<Integer> input;

    public Storage() {
        memory = new int[MEMORY_SIZE];
        registers = new int[NR_REGISTERS];
        stack = new ArrayDeque<>();
        input = new LinkedList<>();
    }

    public void writeToMemory(int idx, int val) {
        memory[idx] = val;
    }

    public int readFromMemory(int idx) {
        return memory[idx];
    }

    public int getMemorySize() {
        return MEMORY_SIZE;
    }

    public int read(int idx) {
        if (isRegister(idx)) {
            return registers[idx - MEMORY_SIZE];
        } else {
            return idx;
        }
    }

    public void writeToRegister(int idx, int val) {
        if (isRegister(idx)) {
            registers[idx - MEMORY_SIZE] = val;
        }
    }

    public int getRegisterNr(int idx) {
        return idx - MEMORY_SIZE;
    }

    private boolean isRegister(int idx) {
        return idx >= MEMORY_SIZE && idx < MEMORY_SIZE + NR_REGISTERS;
    }

    public void pushToStack(int val) {
        this.stack.push(val);
    }

    public int popFromStack() {
        return this.stack.pop();
    }

    public int peekAtStack() {
        Integer val = this.stack.peek();
        return val != null ? val : Integer.MIN_VALUE;
    }

    public void addInput(int c) {
        this.input.add(c);
    }

    public int readInput() {
        return this.input.poll();
    }

    public int peekInput() {
        Integer val = this.input.peek();
        return val != null ? val : 0;
    }

    public boolean hasInput() {
        return !this.input.isEmpty();
    }

    public int getInputSize() {
        return this.input.size();
    }
}
