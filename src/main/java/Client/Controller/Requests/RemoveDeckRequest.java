package Client.Controller.Requests;

import Client.Model.DeckModel;
import Client.View.View.Panels.Col_Change;
import Client.View.View.Panels.CollectionPanel;
import Client.View.View.Panels.MyFrame;
import Client.View.View.Update.Update;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("removedeck")
public class RemoveDeckRequest implements Request {

    private DeckModel deck;

    public RemoveDeckRequest(DeckModel deck) {
        this.deck = deck;
    }

    public RemoveDeckRequest() {
    }

    public DeckModel getDeck() {
        return deck;
    }

    public void setDeck(DeckModel deck) {
        this.deck = deck;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            String res = inputStream.nextLine();
            if (res.equalsIgnoreCase("ok1")) {
                CollectionPanel.getInstance().setSelectedDeck(null);
            }
            Update.render();
            Col_Change.getInstance().getBackButton().doClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
