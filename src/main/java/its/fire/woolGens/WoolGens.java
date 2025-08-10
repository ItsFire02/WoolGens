package its.fire.woolGens;

import its.fire.woolGens.commands.Fly;
import its.fire.woolGens.commands.Vanish;
import its.fire.woolGens.commands.admincommands.GetDye;
import its.fire.woolGens.commands.admincommands.openScoreboardCommand;
import its.fire.woolGens.commands.playercommands.Sell;
import its.fire.woolGens.database.SQLiteManager;
import its.fire.woolGens.gens.Dyes;
import its.fire.woolGens.listener.JoinListener;
import its.fire.woolGens.listener.QuitListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public final class WoolGens extends JavaPlugin {
    private SQLiteManager dbManager;

    @Override
    public void onEnable() {
        getLogger().info("WoolGens is enabled!");

        File dataFolder = this.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        //Database
        this.dbManager = new SQLiteManager(this.getDataFolder().getPath() + "/database.db");
        this.dbManager.createTables();

        //Registering Events
        getServer().getPluginManager().registerEvents(new JoinListener(this.dbManager), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this.dbManager), this);


        //Creating Gens


        //Creating Dyes
        Dyes whiteDye = new Dyes(Material.WHITE_DYE, 5, "White Dye");
        Dyes orangeDye = new Dyes(Material.ORANGE_DYE, 15, "Orange Dye");
        Dyes magentaDye = new Dyes(Material.MAGENTA_DYE, 30, "Magenta Dye");

        HashMap<String, Dyes> dyes = new HashMap<>();
        dyes.put("White Dye" , whiteDye);
        dyes.put("Orange Dye", orangeDye);
        dyes.put("Magenta Dye", magentaDye);

        //Registering Commands
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new Vanish());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new Fly());

        //Registering Player Commands
        Objects.requireNonNull(getCommand("sell")).setExecutor(new Sell());


        //Registering Development Commands
        Objects.requireNonNull(getCommand("getwhitedye")).setExecutor(new GetDye(dyes));
        Objects.requireNonNull(getCommand("getorangedye")).setExecutor(new GetDye(dyes));
        Objects.requireNonNull(getCommand("getmagentadye")).setExecutor(new GetDye(dyes));
        Objects.requireNonNull(getCommand("sb")).setExecutor(new openScoreboardCommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("WoolGens is deactivated!");
        this.dbManager.closeConnection();

    }
}
