package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Mult implements Operation {

    private VM vm;

    public Mult(VM vm) {
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
        return 10;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));
        int c = vm.getStorage().read(getParams().get(2));

        vm.getStorage().writeToRegister(a, Math.floorMod(b * c, vm.getStorage().getMemorySize()));
    }

    @Override
    public String log() {
        return "mult in register " + vm.getStorage().getRegisterNr(getParams().get(0)) + ": "
                + vm.getStorage().read(getParams().get(1)) + " * "
                + vm.getStorage().read(getParams().get(2));
    }

}
