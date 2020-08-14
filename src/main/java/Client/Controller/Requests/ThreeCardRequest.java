package Client.Controller.Requests;

import Client.Model.InfoPassive;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("threecard")
public class ThreeCardRequest implements Request {
    private InfoPassive infoPassive;
    private int i;

    public ThreeCardRequest(InfoPassive infoPassive, int i) {
        this.infoPassive = infoPassive;
        this.i = i;
    }

    public ThreeCardRequest() {
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
