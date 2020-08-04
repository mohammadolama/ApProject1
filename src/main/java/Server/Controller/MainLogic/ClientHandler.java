package Server.Controller.MainLogic;

import Server.Controller.Manager.Managers;
import Server.Controller.Requests.Request;
import Server.Model.Player;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private Server server;
    private Managers gameManager;
    private final Socket socket;
    private Player player;
    private PrintWriter output;
    private Scanner input;
    private final ObjectMapper objectMapper;


    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            while (socket.isConnected()) {
                excuteReq(input.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excuteReq(String string) {
        try {
            System.out.println(string);
            Request request = objectMapper.readValue(string, Request.class);
            request.excute(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}