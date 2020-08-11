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
        @JsonSubTypes.Type(value = PlayerDecksResponse.class, name = "decks"),
        @JsonSubTypes.Type(value = UpdateDrawingPanelResponse.class, name = "drawing"),
        @JsonSubTypes.Type(value = CreateNewDeckResponse.class, name = "createnewdeck"),
        @JsonSubTypes.Type(value = PurchasedCardsResponse.class, name = "purchased"),
        @JsonSubTypes.Type(value = NotPurchasedCardsResponse.class, name = "notpurchased"),
        @JsonSubTypes.Type(value = PlayerHerosResponse.class, name = "heros"),
        @JsonSubTypes.Type(value = Col_ChangeResponse.class, name = "col"),
        @JsonSubTypes.Type(value = CollectionResponse.class, name = "collection"),
        @JsonSubTypes.Type(value = RemoveDeckResponse.class, name = "remove"),
        @JsonSubTypes.Type(value = PassiveResponse.class, name = "passive"),
        @JsonSubTypes.Type(value = ThreeCardResponse.class, name = "three"),
        @JsonSubTypes.Type(value = ChangeCardResponse.class, name = "changecard"),
        @JsonSubTypes.Type(value = CreateNormalResponse.class, name = "normal"),
        @JsonSubTypes.Type(value = BoardResponse.class, name = "board"),
        @JsonSubTypes.Type(value = WantToPlayResponse.class, name = "wanttoplay"),
        @JsonSubTypes.Type(value = HeroCanAttackResponse.class, name = "herocanattack"),
        @JsonSubTypes.Type(value = TargetListResponse.class, name = "targetlist"),
        @JsonSubTypes.Type(value = HeroPowerResponse.class, name = "heropower"),
        @JsonSubTypes.Type(value = CanBePlayedResponse.class, name = "canbeplayed"),
        @JsonSubTypes.Type(value = CanDoActionResponse.class, name = "candoaction"),
        @JsonSubTypes.Type(value = notifyAttack.class, name = "notifyattack"),
})
public interface Response {
    public void process(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object);
}
