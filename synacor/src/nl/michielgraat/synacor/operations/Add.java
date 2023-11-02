package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Add implements Operation {

    private VM vm;

    public Add(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return 4;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1), vm.getStorage().readFromMemory(ptr + 2),
                vm.getStorage().readFromMemory(ptr + 3));
    }

    @Override
    public int getOpCode() {
        return 9;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));
        int c = vm.getStorage().read(getParams().get(2));

        vm.getStorage().writeToRegister(a, Math.floorMod(b + c, vm.getStorage().getMemorySize()));
    }

    @Override
    public String log() {
        return vm.getPtr() + " - add (" + getParams().get(0) + ","
                + getParams().get(1) + ","
                + getParams().get(2) + ")";
    }

}
