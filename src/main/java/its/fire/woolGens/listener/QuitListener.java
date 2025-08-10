package its.fire.woolGens.listener;

import its.fire.woolGens.database.SQLiteManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class QuitListener implements Listener {

    private final SQLiteManager dbManager;

    public QuitListener(SQLiteManager dbManager) {
        this.dbManager = dbManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.quitMessage(
                player.name().color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(Component.text(" has quit!")).color(NamedTextColor.DARK_RED)
        );

    }
}
