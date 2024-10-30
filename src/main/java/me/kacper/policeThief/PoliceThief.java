package me.kacper.policeThief;

import lombok.Getter;
import me.kacper.policeThief.lobby.Lobby;
import me.kacper.policeThief.lobby.manager.LobbyManager;
import me.kacper.policeThief.utils.config.Config;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class PoliceThief extends JavaPlugin {

    @Getter private static PoliceThief instance;

    private Config configuration;

    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfiguration();
        this.loadLobbies();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadConfiguration() {
        this.configuration = new Config(this, new File(getDataFolder(), "configuration.yml"),
                new YamlConfiguration(), "configuration.yml");
        this.configuration.create();
    }

    private void loadLobbies() {
        this.lobbyManager = new LobbyManager();

        boolean canMove = getConfiguration().getConfiguration().getBoolean("lobbies.can_move");

        for (final ModeType modeType : ModeType.values()) {
            this.lobbyManager.getLobbies().put(modeType, new Lobby(modeType,
                    new Location(null,
                            getConfiguration().getConfiguration().getInt("lobbies." + modeType.getConfigType() + ".x"),
                            getConfiguration().getConfiguration().getInt("lobbies." + modeType.getConfigType() + ".y"),
                            getConfiguration().getConfiguration().getInt("lobbies." + modeType.getConfigType() + ".z")), canMove));
        }
    }
}
