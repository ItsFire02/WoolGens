package its.fire.woolGens.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.joinMessage(
                player.name().color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                        .append(Component.text(" has joined")).color(NamedTextColor.GREEN));


    }
}
