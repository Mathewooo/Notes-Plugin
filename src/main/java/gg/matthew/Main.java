package gg.matthew;

import gg.matthew.command.CreateNote;
import gg.matthew.command.NoteMenu;
import gg.matthew.util.NotesStorage;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        try {
            CommandManager.createCoreCommand(this, "notes", "Vytvor a uchovaj si poznámky pre tvoje plány na serveri", "/notes", (CommandList) (sender, subCommandList) -> {
                sender.sendMessage("------------");
                for (SubCommand subCommand : subCommandList) {
                    sender.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
                }
                sender.sendMessage("------------");
            }, CreateNote.class, NoteMenu.class);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        MenuManager.setup(getServer(), this);
        try {
            NotesStorage.loadNotes();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
