package Server.Controller.MainLogic;

import Server.Controller.Manager.Managers;
import Server.Controller.Requests.Request;
import Server.Controller.Response.notifyAttack;
import Server.Model.Player;
import Server.Model.GameState;
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

    public synchronized void excuteReq(String string) {
        new Thread(() -> {
            try {
                Request request = objectMapper.readValue(string, Request.class);
                request.excute(input, output, this, objectMapper, gameManager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public synchronized void notifyAttack(int i, int j, int k, int l) {
        try {
            output.println(objectMapper.writeValueAsString(new notifyAttack(i, j, k, l)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Managers getGameManager() {
        return gameManager;
    }

    public void setGameManager(Managers gameManager) {
        this.gameManager = gameManager;
    }

    public GameState gameState() {
        return gameManager.getState(this);
    }
}
