package gg.matthew.util;

import com.google.gson.Gson;
import gg.matthew.Main;
import gg.matthew.models.Note;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;

public class NotesStorage {
    public static HashMap<UUID, List<Note>> sortedNotesForPlayer = new HashMap<>();
    private static ArrayList<Note> notes = new ArrayList<>();

    public static void createNote(Player player, String message) {
        Note note = new Note(player.getDisplayName(), message, generateId());
        notes.add(note);
        try {
            saveNotes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void deleteNote(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                notes.remove(note);
                break;
            }
        }
        try {
            saveNotes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void saveNotes() throws IOException {
        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writter = new FileWriter(file, false);
        gson.toJson(notes, writter);
        writter.flush();
        writter.close();
        Main.getPlugin().getLogger().info("Notes saved!");
    }

    public static void loadNotes() throws IOException {
        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            Note[] note = gson.fromJson(reader, Note[].class);
            notes = new ArrayList<>(Arrays.asList(note));
            Main.getPlugin().getLogger().info("Notes loaded!");
        }
    }

    private static int generateId() {
        return (notes.size() == 0 ? 1 : notes.get(notes.size() - 1).getId() + 1);
    }

    public static void setSortedNotesForPlayer(UUID uuid) {
        ArrayList<Note> playerNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getPlayerName().equalsIgnoreCase(Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName())) {
                playerNotes.add(note);
            }
        }
        sortedNotesForPlayer.put(uuid, playerNotes);
    }
}
