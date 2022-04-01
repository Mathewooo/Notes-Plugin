package gg.matthew.models;

public class Note {
    private final String playerName;
    private final String message;
    private final String id;

    public Note(String playerName, String message, String id) {
        this.playerName = playerName;
        this.message = message;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }

}
