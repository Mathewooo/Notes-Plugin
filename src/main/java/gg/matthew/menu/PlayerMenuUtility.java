package gg.matthew.menu;

import org.bukkit.entity.Player;

public class PlayerMenuUtility extends gg.ree.api.menu.PlayerMenuUtility {
    private static Integer noteToDelete;

    public PlayerMenuUtility(Player player) {
        super(player);
    }

    public static Integer getNoteToDelete() {
        return noteToDelete;
    }

    public static void setNoteToDelete(Integer noteToDelete) {
        PlayerMenuUtility.noteToDelete = noteToDelete;
    }
}
