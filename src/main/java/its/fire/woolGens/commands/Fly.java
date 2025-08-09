package its.fire.woolGens.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("This command can only be executed by players!").color(NamedTextColor.RED));
            return false;
        }

        if (command.getName().equalsIgnoreCase("fly")) {

            ((Player) sender).setAllowFlight(!((Player) sender).getAllowFlight());

            return true;
        }
        return false;
    }
}
