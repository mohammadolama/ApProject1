package Server.Controller.Requests;

import Client.View.View.Panels.AlternativePanel;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
