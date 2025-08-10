package its.fire.woolGens.commands.admincommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class openScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(commandSender instanceof Player){
            if (command.getName().equalsIgnoreCase("sb")) {
                Player player = (Player) commandSender;

                ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
                org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

                Server server = commandSender.getServer();
                int onlinePlayers = server.getOnlinePlayers().size();


                Objective objective = scoreboard.registerNewObjective
                        ("Title", "dummy", Component.text("WOOLGENS ").color(NamedTextColor.RED)
                                .append(Component.text("(").color(NamedTextColor.DARK_GRAY)
                                .append(Component.text(onlinePlayers).color(NamedTextColor.WHITE)
                                .append(Component.text("/").color(NamedTextColor.DARK_GRAY)
                                .append(Component.text(server.getMaxPlayers()).color(NamedTextColor.WHITE)
                                .append(Component.text(")").color(NamedTextColor.DARK_GRAY)))))));
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                Score score = objective.getScore(ChatColor.GOLD + "Money: $" + ChatColor.GREEN + 0);

                player.setScoreboard(scoreboard);
                return true;
            }

            }




        return true;
    }
}
