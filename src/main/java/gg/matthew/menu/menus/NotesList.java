package gg.matthew.menu.menus;

import gg.matthew.models.Note;
import gg.matthew.util.NotesStorage;
import gg.ree.api.exceptions.MenuManagerException;
import gg.ree.api.exceptions.MenuManagerNotSetupException;
import gg.ree.api.menu.Menu;
import gg.ree.api.menu.MenuManager;
import gg.ree.api.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class NotesList extends Menu {
    public NotesList(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "List Poznámok";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException {
        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.BARRIER) {
            MenuManager.openMenu(NotesMenu.class, playerMenuUtility.getOwner());
        }
    }

    @Override
    public void setMenuItems() {
        List<Note> notes = NotesStorage.sortedNotesForPlayer.get(playerMenuUtility.getOwner().getUniqueId());
        if (!(notes.isEmpty())) {
            for (Note note : notes) {
                ItemStack itemStack = makeItem(Material.PAPER, note.getMessage(), "Poznámka #" + note.getId(), "Vytvorené hráčom " + note.getPlayerName() + " - " + note.getDate());
                inventory.addItem(itemStack);
            }
            ItemStack close = makeItem(Material.BARRIER, "Zatvoriť");
            inventory.setItem(49, close);
        }
    }
}
