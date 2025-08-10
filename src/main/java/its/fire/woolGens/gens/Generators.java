package its.fire.woolGens.gens;

import org.bukkit.Location;

public class Generators {
    private String name;
    private int level;
    private Location location;

    public Generators(String name, int startLevel, Location location){
        this.name = name;
        this.level = startLevel;
        this.location = location;
    }

}
