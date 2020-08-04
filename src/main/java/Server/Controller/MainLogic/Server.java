package Server.Controller.MainLogic;


import Server.Model.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<Player> players;
    private ArrayList waitingPlayers;
    private int port;

    public Server(int port) {
        try {
            this.port = port;
            players = new ArrayList<>();
            waitingPlayers = new ArrayList<>();
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("server started at port : " + port);
//        new myThread().start();
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

