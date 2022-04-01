package gg.matthew.menu.menus;

import gg.matthew.util.NotesStorage;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ConfirmDelete extends Menu {
    public ConfirmDelete(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Potvrdenie: Vymazanie Poznámky";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException {
        switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
            case RED_BANNER:
                MenuManager.openMenu(NoteDelete.class, playerMenuUtility.getOwner());
                break;
            case GREEN_BANNER:
                String noteID = gg.matthew.menu.PlayerMenuUtility.getNoteToDelete();
                NotesStorage.deleteNote(noteID);
                playerMenuUtility.getOwner().sendMessage(ChatColor.GREEN + "Poznámka bola vymazaná!");
                playerMenuUtility.getOwner().closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack yes = makeItem(Material.GREEN_BANNER, "Vymazať poznámku");
        ItemStack no = makeItem(Material.RED_BANNER, "Zrušiť akciu");
        inventory.setItem(3, yes);
        inventory.setItem(5, no);
        setFillerGlass();
    }
}
