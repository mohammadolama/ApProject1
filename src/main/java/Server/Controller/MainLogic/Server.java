package Server.Controller.MainLogic;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private ArrayList<ClientHandler> clientHandlerList;
    public Server(int port) {
        try {
            this.port = port;
            serverSocket = new ServerSocket(port);
            clientHandlerList = new ArrayList<>();
            Thread thread = new Thread(new myRun());
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("server started at port : " + port);
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clientHandlerList.add(clientHandler);
                clientHandler.start();
            } catch (IOException e) {
                System.out.println("Server stopped because of some Errors. ");
            }
        }
    }

    class myRun implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    Session session = DataBaseManagment.sessionFactory.openSession();
                    session.close();
                    Thread.sleep(10000);
                }
            } catch (HibernateException | InterruptedException | IllegalStateException h) {
                for (ClientHandler clientHandler : clientHandlerList) {
                    clientHandler.notifyDatabseError();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clientHandler.interrupt();
                }
                Server.this.interrupt();
            }
        }
    }
}

