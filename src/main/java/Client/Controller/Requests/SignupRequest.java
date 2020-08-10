package Client.Controller.Requests;

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
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
//        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(this);
            outputStream.println(s);
//            String result = inputStream.nextLine();
//            if (result.equalsIgnoreCase("ok")) {
//                LoginPanel.getInstance().getError().setText("Account Created.");
//            } else if (result.equalsIgnoreCase("user already exist")) {
//                LoginPanel.getInstance().getError().setText("User already exists.");
//            }
//            Update.render();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
