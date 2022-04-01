package gg.matthew.models;

import java.util.UUID;

public class Note {
    private final String id;
    private String playerName;
    private String message;

    public Note(String playerName, String message) {
        this.playerName = playerName;
        this.message = message;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
