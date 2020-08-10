package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("wallet")
public class WalletResponse implements Response {

    private long wallet;

    public WalletResponse(long wallet) {
        this.wallet = wallet;
    }

    public WalletResponse() {
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }
}
