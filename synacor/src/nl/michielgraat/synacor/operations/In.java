package nl.michielgraat.synacor.operations;

import java.util.List;

import nl.michielgraat.synacor.VM;

public class In implements Operation {

    private VM vm;

    private boolean waitingForInput = false;

    public In(VM vm) {
        this.vm = vm;
    }

    @Override
    public int getPtrIncr() {
        return waitingForInput ? 0 : 2;
    }

    @Override
    public List<Integer> getParams() {
        int ptr = vm.getPtr();
        return List.of(vm.getStorage().readFromMemory(ptr + 1));
    }

    @Override
    public int getOpCode() {
        return 20;
    }

    @Override
    public void execute() {
        if (vm.getStorage().hasInput()) {
            waitingForInput = false;
            int a = getParams().get(0);
            char input = (char) vm.getStorage().readInput();
            vm.getStorage().writeToRegister(a, input);
        } else {
            waitingForInput = true;
            vm.waitForInput();
        }
    }

    @Override
    public String log() {
        return vm.getPtr() + " - in (" + getParams().get(0) + ")";
    }
   
}
