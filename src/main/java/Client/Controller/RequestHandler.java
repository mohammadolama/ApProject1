package Client.Controller;

import Client.Controller.Requests.Request;
import Client.View.View.Panels.ConnectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Sounds.SoundAdmin;
import Server.Controller.MainLogic.ThreadColor;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class RequestHandler {
    //    private static Admin admin = Admin.getInstance();
    private static RequestHandler requestHandler;
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;
    private ObjectMapper objectMapper;
    private ResponseHandler responseHandler;
    private final Object object = new Object();

    private RequestHandler(Socket socket) {
        try {
            this.socket = socket;
            this.scanner = new Scanner(socket.getInputStream());
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            objectMapper = new ObjectMapper();
            responseHandler = new ResponseHandler(scanner, printWriter, objectMapper, object);
            responseHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createInstance(Socket socket) {
        requestHandler = new RequestHandler(socket);
    }

    public static RequestHandler getInstance() {
        return requestHandler;
    }


    public synchronized void sendRequest(Request request) {
        if (socket.isConnected()) {
            System.out.println(ThreadColor.ANSI_GREEN + request.toString() + ThreadColor.ANSI_RESET);
            request.excute(scanner, printWriter, objectMapper, object);
        } else {
            SoundAdmin.play1("resources/Sounds/login.wav");
            ConnectionPanel connectionPanel = new ConnectionPanel();
            MyFrame.getPanel().add("connection", connectionPanel);
            MyFrame.getInstance().changePanel("connection");
        }
    }

    public static void Connect(String ip, int port, ConnectionPanel panel) {
        try {
            Socket socket = new Socket(ip, port);
            createInstance(socket);
            MyFrame.getInstance().createLoginPanel();
        } catch (IOException e) {
            panel.label.setText("Connection Error");
        }
    }

    public void playSound(String music) {
        new Thread(() -> SoundAdmin.playSound(music)).start();
    }

}
