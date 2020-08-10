package Server.Controller.Response;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("login")
public class LoginSignupResponse implements Response {

    private int i;
    private String res;

    public LoginSignupResponse(int i, String res) {
        this.i = i;
        this.res = res;
    }

    public LoginSignupResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
