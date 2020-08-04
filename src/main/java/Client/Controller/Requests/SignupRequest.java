package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("signup")
public class SignupRequest implements Request {
    private String username;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
