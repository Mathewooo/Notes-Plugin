package gg.matthew.listener;

import gg.matthew.models.Note;
import gg.matthew.util.NotesStorage;
import gg.ree.api.colors.RgbGradient;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.util.List;

import static gg.ree.api.colors.RgbGradient.rgbGradient;

public class JoinListener implements Listener {
    private Note getLatestNote(Player player) {
        NotesStorage.setSortedNotesForPlayer(player.getUniqueId());
        List<Note> notes = NotesStorage.sortedNotesForPlayer.get(player.getUniqueId());
        if (!(notes.isEmpty())) {
            return notes.stream().reduce(Compare::max).get();
        } else {
            return null;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Note latestNote = getLatestNote(player);
        if (latestNote != null) {
            player.sendMessage(rgbGradient("Tvoja najnovšia poznámka - " + latestNote.getMessage(), new Color(192, 211, 229), new Color(242, 242, 242), new RgbGradient.LinearInterpolator()));
        } else player.sendMessage(rgbGradient("Nemáš žiadne poznámky - poprípade si ich vytvor pomocou /notes", new Color(192, 211, 229), new Color(242, 242, 242), new RgbGradient.LinearInterpolator()));
    }
}

