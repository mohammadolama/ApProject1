package Configs;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;

public class ConfigsLoader {

    private BoardConfig boardConfig;
    private ShopConfig shopConfig;
    private Col_ChangeConfig col_changeConfig;
    private CollectionConfig collectionConfig;
    private CollectionDrawingConfig collectionDrawingConfig;
    private StatusConfig statusConfig;
    private InfoConfig infoConfig;
    private FirstHeroConfig firstHeroConfig;
    private MenuConfig menuConfig;
    private LoginConfig loginConfig;
    private SummonedConfig summonedConfig;
    private String path = String.format("resources/Properties/");


    private static ConfigsLoader configsLoader = new ConfigsLoader();

    private ConfigsLoader() {
        LoadConfigs();
    }

    public static ConfigsLoader getInstance() {
        return configsLoader;
    }

    private void LoadConfigs() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileReader fileReader = new FileReader(path + "col_change.json");
            col_changeConfig = objectMapper.readValue(fileReader, Col_ChangeConfig.class);
            fileReader = new FileReader(path + "board.json");
            boardConfig = objectMapper.readValue(fileReader, BoardConfig.class);
            fileReader = new FileReader(path + "collection.json");
            collectionConfig = objectMapper.readValue(fileReader, CollectionConfig.class);
            fileReader = new FileReader(path + "shop.json");
            shopConfig = objectMapper.readValue(fileReader, ShopConfig.class);
            fileReader = new FileReader(path + "login.json");
            loginConfig = objectMapper.readValue(fileReader, LoginConfig.class);
            fileReader = new FileReader(path + "info.json");
            infoConfig = objectMapper.readValue(fileReader, InfoConfig.class);
            fileReader = new FileReader(path + "menu.json");
            menuConfig = objectMapper.readValue(fileReader, MenuConfig.class);
            fileReader = new FileReader(path + "collectiondraw.json");
            collectionDrawingConfig = objectMapper.readValue(fileReader, CollectionDrawingConfig.class);
            fileReader = new FileReader(path + "status.json");
            statusConfig = objectMapper.readValue(fileReader, StatusConfig.class);
            fileReader = new FileReader(path + "firsthero.json");
            firstHeroConfig = objectMapper.readValue(fileReader, FirstHeroConfig.class);
            fileReader = new FileReader(path + "summonedconfig.json");
            summonedConfig = objectMapper.readValue(fileReader, SummonedConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    public ShopConfig getShopConfig() {
        return shopConfig;
    }

    public Col_ChangeConfig getCol_changeConfig() {
        return col_changeConfig;
    }

    public CollectionConfig getCollectionConfig() {
        return collectionConfig;
    }

    public CollectionDrawingConfig getCollectionDrawingConfig() {
        return collectionDrawingConfig;
    }

    public StatusConfig getStatusConfig() {
        return statusConfig;
    }

    public InfoConfig getInfoConfig() {
        return infoConfig;
    }

    public FirstHeroConfig getFirstHeroConfig() {
        return firstHeroConfig;
    }

    public MenuConfig getMenuConfig() {
        return menuConfig;
    }

    public LoginConfig getLoginConfig() {
        return loginConfig;
    }

    public SummonedConfig getSummonedConfig() {
        return summonedConfig;
    }
}
