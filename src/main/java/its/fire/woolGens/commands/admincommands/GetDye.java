package its.fire.woolGens.commands.admincommands;

import its.fire.woolGens.gens.Dyes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GetDye implements CommandExecutor {
    private final HashMap<String, Dyes> dyesHashMap;

    public GetDye(HashMap<String, Dyes> dyesHashMap) {
        this.dyesHashMap = dyesHashMap;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(Component.text("This command can only be executed by players!").color(NamedTextColor.RED));
            return false;
        }


        if (command.getName().equalsIgnoreCase("getwhitedye")) {

            Dyes whiteDye = dyesHashMap.get("White Dye");
            Material whiteDyeMaterial = whiteDye.getType();
            ItemStack whiteDyeItemStack = new ItemStack(whiteDyeMaterial);
            ItemMeta whiteDyeItemMeta = whiteDyeItemStack.getItemMeta();

            whiteDyeItemMeta.displayName(Component.text("White Dye"));
            whiteDyeItemStack.setItemMeta(whiteDyeItemMeta);

            player.give(whiteDyeItemStack);
            return true;

        } else if (command.getName().equalsIgnoreCase("getorangedye")) {
            Dyes orangeDye = dyesHashMap.get("Orange Dye");
            Material orangeDyeMaterial = orangeDye.getType();

            ItemStack orangeDyeItemStack = new ItemStack(orangeDyeMaterial);
            ItemMeta orangeDyeItemMeta = orangeDyeItemStack.getItemMeta();

            orangeDyeItemMeta.displayName(Component.text("Orange Dye"));
            orangeDyeItemStack.setItemMeta(orangeDyeItemMeta);

            player.give(orangeDyeItemStack);
            return true;

        } else if (command.getName().equalsIgnoreCase("getmagentadye")) {
            Dyes magentaDye = dyesHashMap.get("Magenta Dye");
            Material magentaDyeMaterial = magentaDye.getType();

            ItemStack magentaDyeItemStack = new ItemStack(magentaDyeMaterial);
            ItemMeta magentaDyeItemMeta = magentaDyeItemStack.getItemMeta();

            magentaDyeItemMeta.displayName(Component.text("Magenta Dye"));
            magentaDyeItemStack.setItemMeta(magentaDyeItemMeta);

            player.give(magentaDyeItemStack);
            return true;
        }


        return false;
    }
}
