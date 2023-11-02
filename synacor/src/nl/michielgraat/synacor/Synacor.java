package nl.michielgraat.synacor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Synacor {

    private byte[] getInput() {
        try {
            return Files.readAllBytes(Paths.get("resources", "challenge.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public void run() throws IOException {
        VM vm = new VM(getInput());
        vm.run();
    }

    public static void main(String[] args) throws IOException {
        new Synacor().run();
    }
}
