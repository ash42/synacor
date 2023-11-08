package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Call implements Operation {

    private VM vm;

    public Call(VM vm) {
        this.vm = vm;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1));
    }

    @Override
    public int getPtrIncr() {
        int ptr = vm.getPtr();
        int a = vm.getStorage().read(getParams().get(0));
        return a - ptr;
    }

    @Override
    public int getOpCode() {
        return 17;
    }

    @Override
    public void execute() {
        int ptr = vm.getPtr();
        vm.getStorage().pushToStack(ptr + 2);
    }

    @Override
    public String log() {
        int ptr = vm.getPtr();
        return "call - write to stack: " + vm.getStorage().readFromMemory(ptr + 1) + " and jump to "
                + (vm.getStorage().read(getParams().get(0)) - ptr);
    }
}
