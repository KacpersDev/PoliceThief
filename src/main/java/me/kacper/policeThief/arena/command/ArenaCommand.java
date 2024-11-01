package me.kacper.policeThief.arena.command;

import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.PoliceThief;
import me.kacper.policeThief.arena.Arena;
import me.kacper.policeThief.arena.ArenaMode;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ArenaCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender.hasPermission("policethief.arena.admin"))) {
            return false;
        }

        if (args.length == 0) {
            usage(sender);
        } else {
            if (args[0].equalsIgnoreCase("create")) {
                if (args.length == 1) {
                    usage(sender);
                } else {
                    String name = args[1];
                    if (args.length == 2) {
                        usage(sender);
                    } else {
                        ModeType modeType = ModeType.valueOf(args[2].toUpperCase());

                        if (PoliceThief.getInstance().getArenaManager().getArena(name) != null) {
                            sender.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(PoliceThief.getInstance()
                                    .getLanguage().getConfiguration().getString("arena.exists")).replace("{name}", name)));
                            return false;
                        }

                        PoliceThief.getInstance().getArenaManager().createArena(new Arena(name, modeType, null, null));
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(Objects.requireNonNull(PoliceThief.getInstance().getLanguage()
                                .getConfiguration().getString("arena.created")).replace("{arena}", name))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("delete")) {
                if (args.length == 1) {
                    usage(sender);
                } else {
                    String name = args[1];

                    if (PoliceThief.getInstance().getArenaManager().getArena(name) == null) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(PoliceThief.getInstance()
                                .getLanguage().getConfiguration().getString("arena.not-exists")).replace("{name}", name)));
                        return false;
                    }

                    Arena arena = PoliceThief.getInstance().getArenaManager().getArena(name);

                    if (arena.getArenaMode() == ArenaMode.IN_GAME || !arena.getCurrentPlayers().isEmpty()) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(PoliceThief.getInstance()
                                .getLanguage().getConfiguration().getString("arena.delete-fail")).replace("{name}", name)));
                        return false;
                    }

                    PoliceThief.getInstance().getArenaManager().getArenas().remove(arena);
                }
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }

    private void usage(CommandSender sender) {
        for (final String arenaUsage : PoliceThief.getInstance().getLanguage().getConfiguration().getStringList("arena.wrong-usage")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(arenaUsage));
        }
    }
}
