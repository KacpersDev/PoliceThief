package me.kacper.policeThief;

import lombok.Getter;
import me.kacper.policeThief.arena.command.ArenaCommand;
import me.kacper.policeThief.arena.manager.ArenaManager;
import me.kacper.policeThief.utils.config.Config;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

@Getter
public final class PoliceThief extends JavaPlugin {

    @Getter private static PoliceThief instance;

    private Config configuration, arenas;

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfiguration();
        this.loadCommands();

        this.arenaManager = new ArenaManager();
        this.arenaManager.loadArenas();
    }

    @Override
    public void onDisable() {
        this.arenaManager.saveArenas();

        instance = null;
    }

    private void loadConfiguration() {
        this.configuration = new Config(this, new File(getDataFolder(), "configuration.yml"),
                new YamlConfiguration(), "configuration.yml");
        this.arenas = new Config(this, new File(getDataFolder(), "arenas.yml"),
                new YamlConfiguration(), "arenas.yml");

        this.configuration.create();
        this.arenas.create();
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("arena")).setExecutor(new ArenaCommand());
    }
}
