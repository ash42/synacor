package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Rmem implements Operation {

    private VM vm;

    public Rmem(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 3;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1), vm.getStorage().readFromMemory(ptr + 2));
    }

    @Override
    public int getOpCode() {
        return 15;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));

        vm.getStorage().writeToRegister(a, vm.getStorage().readFromMemory(b));
    }

    @Override
    public String log() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));
        return "rmem to register " + vm.getStorage().getRegisterNr(a) + ": " + b;
    }
}
