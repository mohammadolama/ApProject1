package Client.Controller.Requests;

import java.io.PrintWriter;
import java.util.Scanner;

public class SoundManagmentRequest implements Request {

    private int value;

    public SoundManagmentRequest(int value) {
        this.value = value;
    }

    public SoundManagmentRequest() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
