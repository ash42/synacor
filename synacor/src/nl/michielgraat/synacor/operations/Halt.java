package nl.michielgraat.synacor.operations;

import java.util.Collections;
import java.util.List;

import nl.michielgraat.synacor.VM;

public class Halt implements Operation {

    private VM vm;

    public Halt(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 0;
    }

    @Override
    public List<Integer> getParams() {
        return Collections.emptyList();
    }

    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    public void execute() {
        vm.halt();
    }

    @Override
    public String log() {
        return "halt";
    }

}
