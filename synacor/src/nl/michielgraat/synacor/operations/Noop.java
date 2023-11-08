package nl.michielgraat.synacor.operations;

import java.util.Collections;
import java.util.List;

import nl.michielgraat.synacor.VM;

public class Noop implements Operation {

    private VM vm;

    public Noop(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 1;
    }

    @Override
    public List<Integer> getParams() {
        return Collections.emptyList();
    }

    @Override
    public int getOpCode() {
        return 21;
    }

    @Override
    public void execute() {
    }

    @Override
    public String log() {
        return "noop";
    }

}