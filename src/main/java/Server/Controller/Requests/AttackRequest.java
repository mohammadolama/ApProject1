package Server.Controller.Requests;

import Client.View.View.Panels.BoardPanel;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("attack")
public class AttackRequest implements Request {
    private int attacker;
    private int target;
    private BoardPanel boardPanel;

    public AttackRequest(int attacker, int target, BoardPanel boardPanel) {
        this.attacker = attacker;
        this.target = target;
        this.boardPanel = boardPanel;
    }

    public AttackRequest() {
    }

    public int getAttacker() {
        return attacker;
    }

    public void setAttacker(int attacker) {
        this.attacker = attacker;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {

    }
}
