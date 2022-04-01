package gg.matthew.menu;

import org.bukkit.entity.Player;

public class PlayerMenuUtility extends me.kodysimpson.simpapi.menu.PlayerMenuUtility {
    private static String noteToDelete;

    public PlayerMenuUtility(Player player) {
        super(player);
    }

    public static String getNoteToDelete() {
        return noteToDelete;
    }

    public static void setNoteToDelete(String noteToDelete) {
        PlayerMenuUtility.noteToDelete = noteToDelete;
    }
}
