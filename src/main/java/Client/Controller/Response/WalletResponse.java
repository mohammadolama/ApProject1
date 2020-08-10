package Client.Controller.Response;

import Client.Controller.Responses;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wallet")
public class WalletResponse implements Response {

    private long wallet;

    public WalletResponse() {
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }

    @Override
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        Responses.getInstance().setWallet(wallet);
        synchronized (object) {
            object.notify();
        }
    }
}
