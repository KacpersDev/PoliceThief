package me.kacper.policeThief.arena.task;

import me.kacper.policeThief.PoliceThief;
import me.kacper.policeThief.arena.Arena;
import me.kacper.policeThief.arena.ArenaMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ArenaTask implements Runnable {

    @Override
    public void run() {
        for (final Arena arena : PoliceThief.getInstance().getArenaManager().getArenas()) {
            if (arena.getArenaMode() == ArenaMode.IN_GAME) {
                arena.increaseGameTimer();
            }

            if (arena.getArenaMode() == ArenaMode.WAITING && arena.getCurrentPlayers().size() >= arena.getMinPlayers()) {
                arena.decreaseWaitingTimer();
            }  else {
                arena.resetWaitingTimer();
            }

            if (arena.getArenaMode() == ArenaMode.WAITING && arena.getCurrentWaitingTimer() <= 0) {
                for (final UUID uuid : arena.getCurrentPlayers()) {
                    Player uuidPlayer = Bukkit.getPlayer(uuid);

                    // Player is getting removed in onQuitEvent, but check is made just to make sure.
                    if (uuidPlayer != null && uuidPlayer.isOnline() && !arena.getPolicePlayers().contains(uuid)) {
                        uuidPlayer.teleport(arena.getGameSpawnLocation());
                    }

                    if (uuidPlayer != null && arena.getPolicePlayers().contains(uuid)) {
                        uuidPlayer.teleport(arena.getPoliceGameLobbyLocation());
                    }
                }

                arena.setArenaMode(ArenaMode.STARTING);
            }

            if (arena.getArenaMode() == ArenaMode.STARTING) {
                arena.setPoliceTimer(arena.getPoliceTimer() - 1);

                if (arena.getPoliceTimer() <= 0) {
                    arena.setArenaMode(ArenaMode.IN_GAME);
                }
            }
        }
    }
}
