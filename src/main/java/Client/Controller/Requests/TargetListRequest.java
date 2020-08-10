package Client.Controller.Requests;

import Client.View.View.Panels.BoardPanel;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("targetlist")
public class TargetListRequest implements Request {
    private BoardPanel boardPanel;
    private ArrayList<Integer> list;

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

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {

    }
}
