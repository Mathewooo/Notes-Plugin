package gg.matthew.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private final String playerName;
    private final String message;
    private final String date;
    private final int id;

    public Note(String playerName, String message, int id) {
        this.playerName = playerName;
        this.message = message;
        this.date = new SimpleDateFormat("dd.MM.yy").format(new Date());
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }
}
