package Server.Controller.Requests;

import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("creategamemode")
public class CreateGameModeRequest implements Request {
    private int i;
    private InfoPassive infoPassive;

    public CreateGameModeRequest(int i, InfoPassive infoPassive) {
        this.i = i;
        this.infoPassive = infoPassive;
    }

    public CreateGameModeRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
