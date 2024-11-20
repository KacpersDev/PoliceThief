package me.kacper.policeThief.arena.manager;

import lombok.Getter;
import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.PoliceThief;
import me.kacper.policeThief.arena.Arena;
import me.kacper.policeThief.arena.ArenaMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ArenaManager {

    private final List<Arena> arenas = new ArrayList<>();

    public void loadArenas() {
        if (PoliceThief.getInstance().getArenas().getConfiguration().getConfigurationSection("arena") == null) return;
        for (final String arena : Objects.requireNonNull(PoliceThief.getInstance().getArenas().getConfiguration().getConfigurationSection("arena")).getKeys(false)) {
            PoliceThief.getInstance().getArenaManager().getArenas().add(
                    new Arena(PoliceThief.getInstance().getArenas().getConfiguration().getString("arena." + arena + ".name"),
                            ModeType.valueOf(Objects.requireNonNull(PoliceThief.getInstance().getArenas().getConfiguration().getString("arena." + arena + ".modType")).toUpperCase()),
                            PoliceThief.getInstance().getArenas().getConfiguration().getLocation("arena."  +arena + ".lobbySpawnLocation"),
                            PoliceThief.getInstance().getArenas().getConfiguration().getLocation("arena."  +arena + ".gameSpawnLocation"),
                            PoliceThief.getInstance().getArenas().getConfiguration().getLocation("arena."  +arena + ".policeGameLobbyLocation"))
            );
        }
    }

    public void saveArenas() {
        arenas.forEach(arena -> {
            PoliceThief.getInstance().getArenas().getConfiguration().set("arena." + arena.getName() + ".name", arena.getName());
            PoliceThief.getInstance().getArenas().getConfiguration().set("arena." + arena.getName() + ".modeType", arena.getModeType().getConfigType().toUpperCase());
            PoliceThief.getInstance().getArenas().getConfiguration().set("arena." + arena.getName() + ".lobbySpawnLocation", arena.getLobbySpawnLocation());
            PoliceThief.getInstance().getArenas().getConfiguration().set("arena." + arena.getName() + ".gameSpawnLocation", arena.getGameSpawnLocation());
            PoliceThief.getInstance().getArenas().getConfiguration().set("arena." + arena.getName() + ".policeGameLobbyLocation", arena.getPoliceGameLobbyLocation());
            PoliceThief.getInstance().getArenas().save();
        });
    }

    public void createArena(Arena arena) {
        arenas.add(arena);
    }

    public Arena getArena(String name) {
        for (final Arena arena : arenas) {
            if (arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }

        return null;
    }

    public Arena getNewArena(ModeType modeType) {
        for (final Arena arena : arenas) {
            if (arena.getModeType().equals(modeType) && arena.getArenaMode() == ArenaMode.WAITING) {
                return arena;
            }
        }

        return null;
    }
}
