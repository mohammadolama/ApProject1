package Server.Controller.Response;

import Server.Model.Enums.Heroes;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.*;

@JsonTypeName("heros")
public class PlayerHerosResponse implements Response {

    private List<Heroes> heros;

    public PlayerHerosResponse(List<Heroes> heros) {
        this.heros = heros;
    }

    public PlayerHerosResponse() {
    }

    public List<Heroes> getHeros() {
        return heros;
    }

    public void setHeros(List<Heroes> heros) {
        this.heros = heros;
    }
}
