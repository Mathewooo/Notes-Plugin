package gg.matthew.command;

import gg.matthew.util.NotesStorage;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateNote extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Vytvor novú poznámku";
    }

    @Override
    public String getSyntax() {
        return "/notes create (poznámka)";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int index = 1; index < (args.length - 1); index++) {
                stringBuilder.append(args[index]).append(" ");
            }
            stringBuilder.append(args[args.length - 1]);
            NotesStorage.createNote((Player) sender, stringBuilder.toString());
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
