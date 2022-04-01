package gg.matthew.menu;

import org.bukkit.entity.Player;

public class PlayerMenuUtility extends gg.ree.api.menu.PlayerMenuUtility {
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
