package nl.michielgraat.synacor.operations;

import java.util.Collections;
import java.util.List;

import nl.michielgraat.synacor.VM;

public class Ret implements Operation {

    private VM vm;

    public Ret(VM vm) {
        this.vm = vm;
    }

    @Override
    public List<Integer> getParams() {
        return Collections.emptyList();
    }

    @Override
    public int getPtrIncr() {
        int ptr = vm.getPtr();
        int val = vm.getStorage().popFromStack();
        return val - ptr;
    }

    @Override
    public int getOpCode() {
        return 18;
    }

    @Override
    public void execute() {
    }

    @Override
    public String log() {
        return "ret - jump to " + vm.getStorage().peekAtStack();
    }
}
