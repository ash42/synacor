package nl.michielgraat.synacor.operations;

import nl.michielgraat.synacor.VM;

public class OperationFactory {

    private OperationFactory() {}

    public static Operation getOperation(int opcode, VM vm) {
        switch (opcode) {
            case 0:
                return new Halt(vm);
            case 1:
                 return new Set(vm);
            case 2:
                 return new Push(vm);
            case 3:
                 return new Pop(vm);
            case 4:
                 return new Eq(vm);
            case 5:
                 return new Gt(vm);
            case 6:
                 return new Jmp(vm);
            case 7:
                 return new Jt(vm);
            case 8:
                 return new Jf(vm);
            case 9:
                 return new Add(vm);
            case 10:
                 return new Mult(vm);
            case 11:
                 return new Mod(vm);
            case 12:
                 return new And(vm);
            case 13:
                 return new Or(vm);
            case 14:
                 return new Not(vm);
            case 15:
                 return new Rmem(vm);
            case 16:
                 return new Wmem(vm);
            case 17:
                 return new Call(vm);
            case 18:
                 return new Ret(vm);
            case 19:
                return new Out(vm);
            case 20:
                 return new In(vm);
            case 21:
                return new Noop(vm);
            default: 
                throw new UnsupportedOperationException("Unsupported opcode: " + String.valueOf(opcode));
        }
    }
}
