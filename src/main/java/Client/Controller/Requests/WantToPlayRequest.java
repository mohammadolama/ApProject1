package Client.Controller.Requests;

import Client.View.View.Panels.InfoPassivePanel;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wanttoplay")
public class WantToPlayRequest implements Request {
    public WantToPlayRequest() {

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
