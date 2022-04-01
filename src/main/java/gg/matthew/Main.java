package gg.matthew;

import gg.matthew.command.CreateNote;
import gg.matthew.command.NoteMenu;
import gg.matthew.util.NotesStorage;
import gg.ree.api.command.CommandManager;
import gg.ree.api.command.SubCommand;
import gg.ree.api.menu.MenuManager;
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
            CommandManager.createCoreCommand(this, "notes", "Vytvor a uchovaj si poznámky pre tvoje plány na serveri", "/notes", (sender, subCommandList) -> {
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
