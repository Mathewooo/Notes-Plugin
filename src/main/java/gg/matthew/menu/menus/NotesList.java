package gg.matthew.menu.menus;

import gg.matthew.models.Note;
import gg.matthew.util.NotesStorage;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
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
        List<Note> notes = NotesStorage.findAllNotes();
        for (Note note : notes) {
            ItemStack itemStack = makeItem(Material.PAPER, "Poznámka #" + note.getId(), note.getMessage(), "Vytvorené hráčom " + note.getPlayerName());
            inventory.addItem(itemStack);
        }
        ItemStack close = makeItem(Material.BARRIER, "Zatvoriť");
        inventory.setItem(49, close);
    }
}
