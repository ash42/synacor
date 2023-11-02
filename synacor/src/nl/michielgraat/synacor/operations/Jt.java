package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Jt implements Operation {

    private VM vm;

    public Jt(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        int ptr = vm.getPtr();

        int a = vm.getStorage().read(getParams().get(0));
        int b = vm.getStorage().read(getParams().get(1));

        if (a != 0) {
            return Math.floorMod(b - ptr, vm.getStorage().getMemorySize());
        } else {
            return 3;
        }
    }

     @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1), vm.getStorage().readFromMemory(ptr + 2));
    }

    @Override
    public int getOpCode() {
        return 7;
    }

    @Override
    public void execute() {
    }

    @Override
    public String log() {
        return vm.getPtr() + " - jt (" + getParams().get(0) + ","
                + getParams().get(1) + ")";
    }

}
