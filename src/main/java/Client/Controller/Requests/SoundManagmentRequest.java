package Client.Controller.Requests;

import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.map.ObjectMapper;

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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        if (value == -2) {
            SoundAdmin.decreaseSound();
        } else if (value == -1) {
            SoundAdmin.increaseSound();
        } else {
            SoundAdmin.stopStart(value);
        }
    }
}
