package Client.Controller.Response;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginSignupResponse.class, name = "login"),
        @JsonSubTypes.Type(value = ModelViewResponse.class, name = "modelview"),
        @JsonSubTypes.Type(value = FirstHeroResponse.class, name = "firsthero"),
        @JsonSubTypes.Type(value = PlayerModelResponse.class, name = "playermodel"),
        @JsonSubTypes.Type(value = BestDecksResponse.class, name = "bestdecks"),
        @JsonSubTypes.Type(value = DeckModelResponse.class, name = "deckmodel"),
        @JsonSubTypes.Type(value = ProperCardsResponse.class, name = "propercards"),
        @JsonSubTypes.Type(value = WalletResponse.class, name = "wallet"),
        @JsonSubTypes.Type(value = PriceResponse.class, name = "price"),
        @JsonSubTypes.Type(value = BuySellResponse.class, name = "buy"),
        @JsonSubTypes.Type(value = ExitLogoutResponse.class, name = "exit"),
})
public interface Response {
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object);
}
