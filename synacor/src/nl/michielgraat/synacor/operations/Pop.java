package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class Pop implements Operation {

    private VM vm;

    public Pop(VM vm) {
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
        return 3;
    }

    @Override
    public void execute() {
        int a = getParams().get(0);
        int val = vm.getStorage().popFromStack();
        vm.getStorage().writeToRegister(a, val);
    }

    @Override
    public String log() {
        return "pop - write to register " + vm.getStorage().getRegisterNr(getParams().get(0)) + " "
                + vm.getStorage().peekAtStack();
    }

}
