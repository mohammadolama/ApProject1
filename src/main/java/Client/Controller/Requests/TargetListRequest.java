package Client.Controller.Requests;

import Client.Controller.Responses;
import Client.View.View.Panels.BoardPanel;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("targetlist")
public class TargetListRequest implements Request {
    @JsonIgnore
    private BoardPanel boardPanel;

    public TargetListRequest(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public TargetListRequest() {
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            synchronized (object) {
                object.wait();
            }
            boardPanel.drawTargetsForAttack(Responses.getInstance().getTargets());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
