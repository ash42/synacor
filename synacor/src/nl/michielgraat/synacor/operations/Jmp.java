package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Jmp implements Operation {

    private VM vm;

    public Jmp(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        int ptr = vm.getPtr();
        int a = vm.getStorage().read(getParams().get(0));
        return a - ptr;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1));
    }

    @Override
    public int getOpCode() {
        return 6;
    }

    @Override
    public void execute() {
    }

    @Override
    public String log() {
        int ptr = vm.getPtr();
        int a = vm.getStorage().read(getParams().get(0));
        return "jmp to " + (a - ptr);
    }

}
