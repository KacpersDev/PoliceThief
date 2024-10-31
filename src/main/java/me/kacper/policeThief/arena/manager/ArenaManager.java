package me.kacper.policeThief.arena.manager;

import lombok.Getter;
import me.kacper.policeThief.arena.Arena;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArenaManager {

    private final List<Arena> arenas = new ArrayList<>();

    public void loadArenas() {

    }

    public void saveArenas() {

    }

    public void createArena(Arena arena) {
        arenas.add(arena);
    }
}
