package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("createnewdeck")
public class CreateNewDeckResponse implements Response {
    private boolean response;

    public CreateNewDeckResponse(boolean response) {
        this.response = response;
    }

    public CreateNewDeckResponse() {
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
