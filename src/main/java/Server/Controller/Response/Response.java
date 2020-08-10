package Server.Controller.Response;

import Server.Controller.Response.*;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

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
})
public interface Response {
}
