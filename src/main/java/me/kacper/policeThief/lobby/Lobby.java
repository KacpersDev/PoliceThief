package me.kacper.policeThief.lobby;

import lombok.Getter;
import lombok.Setter;
import me.kacper.policeThief.ModeType;
import org.bukkit.Location;

@Getter
@Setter
public class Lobby {

    private ModeType modeType;
    private Location spawnPoint;
    private boolean canMove;

    public Lobby(ModeType modeType, Location spawnPoint, boolean canMove) {
        this.modeType = modeType;
        this.spawnPoint = spawnPoint;
        this.canMove = canMove;
    }
}
