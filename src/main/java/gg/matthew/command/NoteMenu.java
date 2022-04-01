package gg.matthew.command;

import gg.matthew.menu.menus.NotesMenu;
import gg.matthew.util.NotesStorage;
import gg.ree.api.command.SubCommand;
import gg.ree.api.exceptions.MenuManagerException;
import gg.ree.api.exceptions.MenuManagerNotSetupException;
import gg.ree.api.menu.MenuManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class NoteMenu extends SubCommand {
    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Otvor menu pre poznámky";
    }

    @Override
    public String getSyntax() {
        return "/notes menu";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        NotesStorage.setSortedNotesForPlayer(player.getUniqueId());
        try {
            MenuManager.openMenu(NotesMenu.class, (Player) sender);

        } catch (MenuManagerException | MenuManagerNotSetupException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
