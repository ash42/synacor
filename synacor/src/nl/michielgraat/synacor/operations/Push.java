package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Push implements Operation {

    private VM vm;

    public Push(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 2;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1));
    }

    @Override
    public int getOpCode() {
        return 2;
    }

    @Override
    public void execute() {
        int a = vm.getStorage().read(getParams().get(0));
        vm.getStorage().pushToStack(a);
    }

    @Override
    public String log() {
        int a = vm.getStorage().read(getParams().get(0));
        return "push to stack " + a;
    }

}
