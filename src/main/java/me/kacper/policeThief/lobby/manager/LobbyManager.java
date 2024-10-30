package me.kacper.policeThief.lobby.manager;

import lombok.Getter;
import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.lobby.Lobby;

import java.util.HashMap;
import java.util.Map;

@Getter
public class LobbyManager {

    private final Map<ModeType, Lobby> lobbies = new HashMap<>();
}
