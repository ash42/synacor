package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Eq implements Operation {

    private VM vm;

    public Eq(VM vm) {
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
        return 4;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int b = vm.getStorage().read(getParams().get(1));
        int c = vm.getStorage().read(getParams().get(2));

        vm.getStorage().writeToRegister(a, b == c ? 1 : 0);
    }

    @Override
    public String log() {
        return "eq in register " + vm.getStorage().getRegisterNr(getParams().get(0)) + ": "
                + vm.getStorage().read(getParams().get(1)) + " == "
                + vm.getStorage().read(getParams().get(2));
    }

}
