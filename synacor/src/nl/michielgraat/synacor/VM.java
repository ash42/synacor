package nl.michielgraat.synacor;

import java.nio.ByteBuffer;
import java.util.Scanner;

import nl.michielgraat.synacor.operations.Operation;
import nl.michielgraat.synacor.operations.OperationFactory;

public class VM {
    
    private byte[] input;
    private Storage storage;

    private boolean halted = false;
    private boolean waitingForInput = false;
    private boolean logCommands = false;
    private int ptr = 0;

    public VM(byte[] input) {
        this.input = input;
        storage = new Storage();
        parseInput();
    }

    public void run() {
        for (String cmd : Solution.commands) {
            cmd.chars().forEach(c -> getStorage().addInput(c));
        }
        Scanner scanner = new Scanner(System.in);
        while (!halted) {
            if (!waitingForInput) {
                int opcode = storage.readFromMemory(ptr);
                Operation o = OperationFactory.getOperation(opcode, this);
                if (logCommands) {
                    System.out.println(ptr + " - " + o.log());
                }
                o.execute();
                setPtrPos(o.getPtrIncr());
            } else {
                readInput(scanner);
                waitingForInput = false;
            }
        }
        scanner.close();
    }

    public void halt() {
        halted = true;
    }

    public void waitForInput() {
        waitingForInput = true;
    }

    private void setPtrPos(int incr) {
        ptr += incr;
        ptr = Math.floorMod(ptr, storage.getMemorySize());
    }

    private int getNr(int startIdx) {
        byte[] b = new byte[2];
        b[0] = input[startIdx];
        b[1] = input[startIdx+1];
        return ByteBuffer.wrap(b).order(java.nio.ByteOrder.LITTLE_ENDIAN).getChar();
    }

    private void parseInput() {
        int memIdx = 0;
        for (int inputIdx = 0; inputIdx < input.length; inputIdx+=2) {
            storage.writeToMemory(memIdx++, getNr(inputIdx));
        }
    }

    private void readInput(Scanner scanner) {
        System.out.print("> ");
        String input = scanner.nextLine();
        if (input.equals("quit") || input.equals("exit")) {
            halt();
        } else if (input.equals("log")) {
            logCommands = true;
        } else if (input.equals("nolog")) {
            logCommands = false;
        } else {
            input += "\n";
            input.chars().forEach(c -> getStorage().addInput(c));
        }
    }

    public Storage getStorage() {
        return this.storage;
    }

    public int getPtr() {
        return this.ptr;
    }

}
