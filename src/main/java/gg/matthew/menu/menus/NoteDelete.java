package gg.matthew.menu.menus;

import gg.matthew.Main;
import gg.matthew.models.Note;
import gg.matthew.util.NotesStorage;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class NoteDelete extends Menu {
    public NoteDelete(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Vyber si poznámku, ktorú chceš vymazať";
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
        } else if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.PAPER) {
            PersistentDataContainer container = Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getPersistentDataContainer();
            String noteID = container.get(new NamespacedKey(Main.getPlugin(), "noteID"), PersistentDataType.STRING);
            gg.matthew.menu.PlayerMenuUtility.setNoteToDelete(noteID);
            MenuManager.openMenu(ConfirmDelete.class, playerMenuUtility.getOwner());
        }
    }

    @Override
    public void setMenuItems() {
        List<Note> notes = NotesStorage.findAllNotes();
        for (Note note : notes) {
            ItemStack itemStack = makeItem(Material.PAPER, "Poznámka #" + note.getId(), note.getMessage(), "Vytvorené hráčom " + note.getPlayerName());
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "noteID"), PersistentDataType.STRING, note.getId());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        ItemStack close = makeItem(Material.BARRIER, "Zatvoriť");
        inventory.setItem(49, close);
    }
}
