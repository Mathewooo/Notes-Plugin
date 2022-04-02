package gg.matthew;

import gg.matthew.command.CreateNote;
import gg.matthew.command.NoteMenu;
import gg.matthew.listener.JoinListener;
import gg.matthew.util.NotesStorage;
import gg.ree.api.colors.RgbGradient;
import gg.ree.api.command.CommandManager;
import gg.ree.api.command.SubCommand;
import gg.ree.api.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;

import static gg.ree.api.colors.RgbGradient.rgbGradient;

public final class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        try {
            CommandManager.createCoreCommand(this, "notes", "Vytvor a uchovaj si poznámky pre tvoje plány na serveri", "/notes", (sender, subCommandList) -> {
                sender.sendMessage(rgbGradient("------------", new Color(192, 211, 229), new Color(242, 242, 242), new RgbGradient.LinearInterpolator()));
                for (SubCommand subCommand : subCommandList) {
                    sender.sendMessage(rgbGradient(subCommand.getSyntax() + " - " + subCommand.getDescription(), new Color(192, 211, 229), new Color(242, 242, 242), new RgbGradient.LinearInterpolator()));
                }
                sender.sendMessage(rgbGradient("------------", new Color(192, 211, 229), new Color(242, 242, 242), new RgbGradient.LinearInterpolator()));
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
