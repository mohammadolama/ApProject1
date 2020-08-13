package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("notifywinner")
public class NotifyWinner implements Response {
    private String name;

    public NotifyWinner(String name) {
        this.name = name;
    }

    public NotifyWinner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
