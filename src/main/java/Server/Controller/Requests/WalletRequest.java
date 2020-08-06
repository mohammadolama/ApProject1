package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wallet")
public class WalletRequest implements Request {

    private long wallet;

    public WalletRequest() {
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(clientHandler.getPlayer().getMoney()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
