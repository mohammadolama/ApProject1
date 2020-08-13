package Client.Controller.Response;

import Client.View.View.Panels.BoardPanel;
import Client.View.View.Panels.MyFrame;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("notifygame")
public class NotifyStartGame implements Response {
    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        BoardPanel boardPanel = new BoardPanel(true);
        boardPanel.setBounds(0, 0, 1600, 1000);
        MyFrame.getInstance().setBoardPanel(boardPanel);
        MyFrame.getPanel().add("play", boardPanel);
        MyFrame.getInstance().changePanel("play");
    }
}
