package me.kacper.policeThief.arena;

import lombok.Getter;
import lombok.Setter;
import me.kacper.policeThief.ModeType;

@Getter
@Setter
public class Arena {

    private String name;
    private ModeType modeType;
    private ArenaMode arenaMode;

    public Arena(String name, ModeType modeType) {
        this.name = name;
        this.modeType = modeType;

        this.arenaMode = ArenaMode.WAITING;
    }
}
