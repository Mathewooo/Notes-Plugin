package gg.matthew.menu.menus;

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

public class NotesMenu extends Menu {
    public NotesMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Tvoje poznámky";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException {
        switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
            case PAPER:
                MenuManager.openMenu(NotesList.class, playerMenuUtility.getOwner());
                break;
            case BARRIER:
                playerMenuUtility.getOwner().closeInventory();
                break;
            case LAVA_BUCKET:
                MenuManager.openMenu(NoteDelete.class, playerMenuUtility.getOwner());
                break;
            case WRITABLE_BOOK:
                playerMenuUtility.getOwner().closeInventory();
                playerMenuUtility.getOwner().sendMessage(ChatColor.GREEN + "Vytvor poznámku pomocou: /notes create (poznámka)");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack create = makeItem(Material.WRITABLE_BOOK, "Vytvoriť poznámku");
        ItemStack list = makeItem(Material.PAPER, "Vylistovať poznámky");
        ItemStack delete = makeItem(Material.LAVA_BUCKET, "Vymazať poznámku");
        ItemStack close = makeItem(Material.BARRIER, "Zatvoriť");
        inventory.setItem(11, create);
        inventory.setItem(13, list);
        inventory.setItem(15, delete);
        inventory.setItem(31, close);
        setFillerGlass();
    }
}
