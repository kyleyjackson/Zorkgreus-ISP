package Zorkgreus;

import java.util.ArrayList;

public class Boon {
    private String god;
    private String boonName;
    private String colour;
    private String flavour;
    private ArrayList<String> stats;
    private ArrayList<Integer> levels;

    public Boon() {
        god = "???";
        boonName = "???";
        colour = "???";
        flavour = "???";
        stats = new ArrayList<String>();
        levels = new ArrayList<Integer>();
    }

    /* displays the information of the boon to the player */
    public String displayBoon() {
        String desc = "\n" + "Boon of " + god + ": " + boonName + "\n" + flavour + "\n" + stats;
        String sep = "\n" + "----------------------------------------------------------------------------------------------------------";
        return desc + sep;
    }

    public String getGodName() {
        return god;
    }

    public void setGod(String godName) {
        god = godName;
    }

    public String getBoonName() {
        return boonName;
    }

    public void setBoonName(String boonName) {
        this.boonName = boonName;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public ArrayList<String> getStats() {
        return stats;
    }

    public void setStats(ArrayList<String> stats) {
        this.stats = stats;
    }

    public ArrayList<Integer> level() {
        return levels;
    }

    public void setLevel(ArrayList<Integer> levels) {
        this.levels = levels;
    }

    public int boonLevel(){
        return 1; 
    }

    public void myBoons(String boons){
        System.out.println("Your boons are: ");
    }
}
