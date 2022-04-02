package gg.matthew.menu.menus;

import gg.matthew.Main;
import gg.matthew.models.Note;
import gg.matthew.util.NotesStorage;
import gg.ree.api.exceptions.MenuManagerException;
import gg.ree.api.exceptions.MenuManagerNotSetupException;
import gg.ree.api.menu.Menu;
import gg.ree.api.menu.MenuManager;
import gg.ree.api.menu.PlayerMenuUtility;
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
        return "Vymazanie poznámok";
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
            Integer noteID = container.get(new NamespacedKey(Main.getPlugin(), "noteID"), PersistentDataType.INTEGER);
            gg.matthew.menu.PlayerMenuUtility.setNoteToDelete(noteID);
            MenuManager.openMenu(ConfirmDelete.class, playerMenuUtility.getOwner());
        }
    }

    @Override
    public void setMenuItems() {
        List<Note> notes = NotesStorage.sortedNotesForPlayer.get(playerMenuUtility.getOwner().getUniqueId());
        if (!(notes.isEmpty())) {
            for (Note note : notes) {
                ItemStack itemStack = makeItem(Material.PAPER, note.getMessage(), "Poznámka #" + note.getId(), "Vytvorené hráčom " + note.getPlayerName() + " - " + note.getDate());
                ItemMeta itemMeta = itemStack.getItemMeta();
                assert itemMeta != null;
                itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "noteID"), PersistentDataType.INTEGER, note.getId());
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            }
            ItemStack close = makeItem(Material.BARRIER, "Zatvoriť");
            inventory.setItem(49, close);
        }
    }
}
