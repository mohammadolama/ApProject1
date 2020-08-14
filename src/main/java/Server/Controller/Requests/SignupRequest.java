package Server.Controller.Requests;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.LoginSignupResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("signup")
public class SignupRequest implements Request {
    private String username;
    private String password;

    public SignupRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        String res = Admin.getInstance().signUp(this.username, this.password);
        try {
            outputStream.println(objectMapper.writeValueAsString(new LoginSignupResponse(1, res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        outputStream.println(res);
    }
}
