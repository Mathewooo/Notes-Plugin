package gg.matthew.command;

import gg.matthew.menu.menus.NotesMenu;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
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
