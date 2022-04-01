package gg.matthew.util;

import com.google.gson.Gson;
import gg.matthew.Main;
import gg.matthew.models.Note;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesStorage {
    private static ArrayList<Note> notes = new ArrayList<>();

    public static void createNote(Player player, String message) {
        Note note = new Note(player.getDisplayName(), message);
        notes.add(note);
        try {
            saveNotes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Note findNote(String id) {
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
                return note;
            }
        }
        return null;
    }

    public static void deleteNote(String id) {
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
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

    public static Note updateNote(String id, Note newNote) {
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
                note.setPlayerName(newNote.getPlayerName());
                note.setMessage(newNote.getMessage());
                try {
                    saveNotes();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                return note;
            }
        }
        return null;
    }

    public static List<Note> findAllNotes() {
        return notes;
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
        System.out.println("Notes saved!");
    }

    public static void loadNotes() throws IOException {
        Gson gson = new Gson();
        File file = new File(Main.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            Note[] note = gson.fromJson(reader, Note[].class);
            notes = new ArrayList<>(Arrays.asList(note));
            System.out.println("Notes Loaded!");
        }
    }
}
