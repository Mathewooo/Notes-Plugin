package gg.matthew.listener;

import gg.matthew.models.Note;

public class Compare {
    public static Note max(Note a, Note b) {
        return a.getId() > b.getId() ? a : b;
    }
}
