package Server.Controller.Requests;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginRequest.class, name = "login"),
        @JsonSubTypes.Type(value = SignupRequest.class, name = "signup"),
        @JsonSubTypes.Type(value = LogoutRequest.class, name = "logout"),
        @JsonSubTypes.Type(value = DeleteAccountRequest.class, name = "deleteaccount"),
        @JsonSubTypes.Type(value = ExitRequest.class, name = "exit"),
        @JsonSubTypes.Type(value = MostUsedCardRequest.class, name = "mostusedcard"),
        @JsonSubTypes.Type(value = UnlockHeroRequest.class, name = "unlockhero"),
        @JsonSubTypes.Type(value = BestDecksRequest.class, name = "bestdecks"),
        @JsonSubTypes.Type(value = GetDeckRequest.class, name = "getdeck"),
        @JsonSubTypes.Type(value = LogRequest.class, name = "log"),
        @JsonSubTypes.Type(value = ChangeCardRequest.class, name = "changecard"),
        @JsonSubTypes.Type(value = AylarActionRequest.class, name = "aylaraction"),
        @JsonSubTypes.Type(value = CreateGameRequest.class, name = "creategame"),
        @JsonSubTypes.Type(value = FinishGameRequest.class, name = "finishgame"),
        @JsonSubTypes.Type(value = PlayerHerosRequest.class, name = "playerheros"),
        @JsonSubTypes.Type(value = ValidateDeckNameRequest.class, name = "validdeck"),
        @JsonSubTypes.Type(value = CreateDeckRequest.class, name = "createdeck"),
        @JsonSubTypes.Type(value = ChangeDeckRequest.class, name = "changedeck"),
        @JsonSubTypes.Type(value = SelectedDeckCardsRequest.class, name = "selecteddeckcards"),
        @JsonSubTypes.Type(value = BuyCardRequest.class, name = "buycard"),
        @JsonSubTypes.Type(value = SellCardRequest.class, name = "sellcard"),
        @JsonSubTypes.Type(value = PureModelViewRequest.class, name = "puremodelview"),
        @JsonSubTypes.Type(value = ProperCardsRequest.class, name = "propercards"),
        @JsonSubTypes.Type(value = PurchasedCardsRequest.class, name = "purchasedcard"),
        @JsonSubTypes.Type(value = NotPurchasedCardsRequest.class, name = "notPurchasedcard"),
        @JsonSubTypes.Type(value = PlayerDecksRequest.class, name = "playerdecks"),
        @JsonSubTypes.Type(value = CreateNewDeckRequest.class, name = "createnewdeck"),
        @JsonSubTypes.Type(value = UpdateDrawingPanelRequest.class, name = "updatedrawingpanel"),
        @JsonSubTypes.Type(value = SelectDeckRequest.class, name = "selectdeck"),
        @JsonSubTypes.Type(value = SelectedDeckRequest.class, name = "selecteddeck"),
        @JsonSubTypes.Type(value = SelectFirstHeroRequest.class, name = "selectfirsthero"),
        @JsonSubTypes.Type(value = CreateGameModeRequest.class, name = "creategamemode"),
        @JsonSubTypes.Type(value = visiblePanelRequest.class, name = "visiblepanel"),
        @JsonSubTypes.Type(value = WantToPlayRequest.class, name = "wanttoplay"),
})
public interface Request {
    public abstract void excute(Scanner inputStream, PrintWriter outputStream);
}