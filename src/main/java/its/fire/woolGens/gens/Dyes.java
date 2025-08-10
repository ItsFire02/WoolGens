package its.fire.woolGens.gens;

import org.bukkit.Material;

public class Dyes {

    private Material type;
    private int sellPrice;
    private String name;

    public Dyes(Material type, int price, String name){
        this.type = type;
        this.sellPrice = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Material getType() {
        return type;
    }
}
