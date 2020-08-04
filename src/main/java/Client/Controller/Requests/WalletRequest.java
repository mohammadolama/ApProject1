package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("wallet")
public class WalletRequest implements Request {

    private int wallet;

    public WalletRequest() {
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream) {

    }
}
