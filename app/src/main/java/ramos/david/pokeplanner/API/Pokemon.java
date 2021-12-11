package ramos.david.pokeplanner.API;

import android.media.Image;

import java.util.HashMap;

public class Pokemon {
    private int id;
    private String name;
    private String sprite;

    public Pokemon(int id, String name, String sprite) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
