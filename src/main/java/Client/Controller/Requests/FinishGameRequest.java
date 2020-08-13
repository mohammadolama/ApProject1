package Client.Controller.Requests;

import Client.Controller.RequestHandler;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("finishgame")
public class FinishGameRequest implements Request {

    public FinishGameRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            MyFrame.getInstance().getBoardPanel().getRequests().stop();
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Back Button"));
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
