package me.kacper.policeThief.arena;

import lombok.Getter;
import lombok.Setter;
import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.PoliceThief;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Arena {

    private String name;
    private ModeType modeType;
    private ArenaMode arenaMode;
    private Location lobbySpawnLocation, gameSpawnLocation, policeGameLobbyLocation;

    private int minPlayers, maxPlayers, waitingTimer, policeTimer, currentWaitingTimer, gameTimer;

    private final Set<UUID> currentPlayers;
    private final Set<UUID> policePlayers;

    public Arena(String name, ModeType modeType, Location lobbySpawnLocation, Location gameSpawnLocation, Location policeGameLobbyLocation) {
        this.name = name;
        this.modeType = modeType;
        this.lobbySpawnLocation = lobbySpawnLocation;
        this.gameSpawnLocation = gameSpawnLocation;
        this.policeGameLobbyLocation = policeGameLobbyLocation;

        this.currentWaitingTimer = 0;
        this.gameTimer = 0;

        this.minPlayers = PoliceThief.getInstance().getConfiguration().getConfiguration().getInt("arena-type." + modeType.getConfigType() + ".min-players");
        this.maxPlayers = PoliceThief.getInstance().getConfiguration().getConfiguration().getInt("arena-type." + modeType.getConfigType() + ".max-players");

        this.waitingTimer = PoliceThief.getInstance().getConfiguration().getConfiguration().getInt("arena-type." + modeType.getConfigType() + ".waiting-timer");
        this.policeTimer = PoliceThief.getInstance().getConfiguration().getConfiguration().getInt("arena-type." + modeType.getConfigType() + ".police-timer");

        this.arenaMode = ArenaMode.WAITING;

        this.currentPlayers = new HashSet<>();
        this.policePlayers = new HashSet<>();
    }

    public void increaseGameTimer() {
        this.gameTimer += 1;
    }

    public void decreaseWaitingTimer() {
        this.waitingTimer -= 1;
    }

    public void resetWaitingTimer() {
        this.waitingTimer = PoliceThief.getInstance().getConfiguration().getConfiguration().getInt("arena-type." + modeType.getConfigType() + ".waiting-timer");
    }
}
