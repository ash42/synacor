package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Out implements Operation {

    private VM vm;

    public Out(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 2;
    }

    @Override
    public List<Integer> getParams() {
        return List.of(vm.getStorage().readFromMemory(vm.getPtr() + 1));
    }

    @Override
    public int getOpCode() {
        return 19;
    }

    @Override
    public void execute() {
        int param = getParams().get(0);
        System.out.print((char) vm.getStorage().read(param));
    }

    @Override
    public String log() {
        int param = getParams().get(0);
        return "out " + param + " (" + (char) vm.getStorage().read(param) + ")";
    }
}