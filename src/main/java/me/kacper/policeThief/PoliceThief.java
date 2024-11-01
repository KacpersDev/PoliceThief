package me.kacper.policeThief;

import lombok.Getter;
import me.kacper.policeThief.arena.command.ArenaCommand;
import me.kacper.policeThief.arena.manager.ArenaManager;
import me.kacper.policeThief.arena.task.ArenaTask;
import me.kacper.policeThief.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

@Getter
public final class PoliceThief extends JavaPlugin {

    @Getter private static PoliceThief instance;

    private Config configuration, arenas, language;

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadConfiguration();
        this.loadCommands();

        this.arenaManager = new ArenaManager();
        this.arenaManager.loadArenas();

        this.loadTasks();
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
        this.language = new Config(this, new File(getDataFolder(), "language.yml"),
                new YamlConfiguration(), "language.yml");

        this.configuration.create();
        this.arenas.create();
        this.language.create();
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("arena")).setExecutor(new ArenaCommand());
    }

    private void loadTasks() {
        Bukkit.getScheduler().runTaskTimer(this, new ArenaTask(), 0L, 20L);
    }
}
