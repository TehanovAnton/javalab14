package classes;

import java.io.Serializable;

public class Channel implements Serializable {
    private String name;
    private String Owner;

    public String getName() {
        return name;
    }

    public String getOwner() {
        return Owner;
    }

    public Channel(String name, String owner) {
        this.name = name;
        Owner = owner;
    }
}
