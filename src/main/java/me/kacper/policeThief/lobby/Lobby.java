package me.kacper.policeThief.lobby;

import lombok.Getter;
import lombok.Setter;
import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.PoliceThief;
import org.bukkit.Location;

@Getter
@Setter
public class Lobby {

    private ModeType modeType;
    private Location spawnPoint;
    private boolean canMove;

    private int currentPlayers, maxPlayers, minPlayers, countdownTimer;

    public Lobby(ModeType modeType, Location spawnPoint, boolean canMove) {
        this.modeType = modeType;
        this.spawnPoint = spawnPoint;
        this.canMove = canMove;

        this.assignValues();
        this.currentPlayers = 0;
    }

    private void assignValues() {
        this.maxPlayers = PoliceThief.getInstance().getConfiguration().getConfiguration()
                .getInt("lobbies." + modeType.getConfigType() + ".max-players");
        this.minPlayers = PoliceThief.getInstance().getConfiguration().getConfiguration()
                .getInt("lobbies." + modeType.getConfigType() + ".min-players");
        this.countdownTimer = PoliceThief.getInstance().getConfiguration().getConfiguration()
                .getInt("lobbies." + modeType.getConfigType() + ".countdown-timer");
    }
}
