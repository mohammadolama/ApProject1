package Server.Controller.MainLogic;

import Client.View.Configs.DeckReader;
import Server.Model.Player;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;

public class JsonReaders {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static DeckReader deckReader() {
        String path = "resources/Properties/deckreader.json";
        DeckReader deckReader = null;
        try {
            FileReader fileReader = new FileReader(path);
            deckReader = objectMapper.readValue(fileReader, DeckReader.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deckReader;
    }

    public static Player deckReaderPlayer(String name) {
        String path = String.format("resources/Properties/%s.json", name);
        Player player = null;
        try {
            FileReader fileReader = new FileReader(path);
            player = objectMapper.readValue(fileReader, Player.class);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }
}
