package me.kacper.policeThief.arena.command;

import me.kacper.policeThief.ModeType;
import me.kacper.policeThief.PoliceThief;
import me.kacper.policeThief.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

                        PoliceThief.getInstance().getArenaManager().createArena(new Arena(name, modeType));
                        sender.sendMessage("Arena created.");
                    }
                }
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }

    private void usage(CommandSender sender){

    }
}
