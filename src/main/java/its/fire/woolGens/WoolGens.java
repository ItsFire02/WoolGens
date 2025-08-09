package its.fire.woolGens;

import its.fire.woolGens.commands.Fly;
import its.fire.woolGens.commands.Vanish;
import its.fire.woolGens.listener.JoinListener;
import its.fire.woolGens.listener.QuitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WoolGens extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("WoolGens is enabled!");

        //Registering Events
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);


        //Registering Commands
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new Vanish());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

    }

    @Override
    public void onDisable() {
        getLogger().info("WoolGens is deactivated!");
    }
}
