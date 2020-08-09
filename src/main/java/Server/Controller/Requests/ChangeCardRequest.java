package Server.Controller.Requests;

import Client.View.View.Panels.AlternativePanel;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("changecard")
public class ChangeCardRequest implements Request {

    private int i;
    private AlternativePanel alternativePanel;

    public ChangeCardRequest(int i, AlternativePanel alternativePanel) {
        this.i = i;
        this.alternativePanel = alternativePanel;
    }

    public ChangeCardRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public AlternativePanel getAlternativePanel() {
        return alternativePanel;
    }

    public void setAlternativePanel(AlternativePanel alternativePanel) {
        this.alternativePanel = alternativePanel;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {

    }
}
