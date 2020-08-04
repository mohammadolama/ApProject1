package Client.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class InfoPassive {

    private String name;
    private String description;

    private InfoPassive(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
