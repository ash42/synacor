package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Not implements Operation {

    private VM vm;

    public Not(VM vm) {
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
        return 14;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));
        vm.getStorage().writeToRegister(a, Math.floorMod(~b, vm.getStorage().getMemorySize()));
    }

    @Override
    public String log() {
        return vm.getPtr() + " - not (" + getParams().get(0) + ","
                + getParams().get(1) + ")";
    }
}
