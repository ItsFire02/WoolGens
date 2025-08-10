// Hier ist der korrigierte und vereinfachte Code.
// Du solltest diese Version verwenden, um das Scoreboard aus dem Bild zu erstellen.

package its.fire.woolGens.listener;

import its.fire.woolGens.database.SQLiteManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class JoinListener implements Listener {

    private final SQLiteManager dbManager;
    public JoinListener(SQLiteManager dbManager) {
        this.dbManager = dbManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        event.joinMessage(
                player.name().color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                        .append(Component.text(" has joined")).color(NamedTextColor.GREEN));

        if (!dbManager.hasPlayer(playerUUID)) {
            dbManager.createPlayer(playerUUID, 500.0);
            player.sendMessage(Component.text("Welcome! You have received a starting balance of $500!").color(NamedTextColor.YELLOW));
        }

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        org.bukkit.scoreboard.Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        // Dynamische Werte abrufen
        double money = dbManager.getMoney(playerUUID);
        int gens = 120;
        double multiplier = 1.8;
        int joins = 8006;

        Objective objective = scoreboard.registerNewObjective("WoolGensBoard", "dummy",
                Component.text("WOOLGENS ").color(NamedTextColor.RED).decorate(TextDecoration.BOLD)
                        .append(Component.text(String.format(" %d/%d", Bukkit.getOnlinePlayers().size(), Bukkit.getMaxPlayers()))
                                .color(NamedTextColor.WHITE)));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore(ChatColor.WHITE + "").setScore(15);
        objective.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "PLAYER").setScore(14);
        objective.getScore(ChatColor.WHITE + "  Money: " + ChatColor.GREEN + "$" + money).setScore(13);
        objective.getScore(ChatColor.WHITE + "  Gens: " + ChatColor.GREEN + gens + "/120").setScore(12);
        objective.getScore(ChatColor.WHITE + "  Multiplier: " + ChatColor.GREEN + multiplier + "x").setScore(11);
        objective.getScore(ChatColor.WHITE + "" + ChatColor.BLACK + "").setScore(10); // Leere Zeile
        objective.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "SERVER").setScore(9);
        objective.getScore(ChatColor.WHITE + "  Joins: " + ChatColor.GREEN + joins).setScore(8);
        objective.getScore(ChatColor.WHITE + "  Server: " + ChatColor.GREEN + "woolgens.net").setScore(7);
        objective.getScore(ChatColor.WHITE + "" + ChatColor.RED + "").setScore(6);


        player.setScoreboard(scoreboard);
    }
}