package Client.Controller.Requests;

import Client.View.View.Sounds.SoundAdmin;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

public class PlayMusic implements Request {

    private String music;

    public PlayMusic(String music) {
        this.music = music;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        new Thread(() -> SoundAdmin.playSound(music)).start();
    }
}
