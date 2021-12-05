package Zorkgreus;

public class Boon {
    private String god;
    private String boonName;
    private String colour;
    private String flavour;
    private String stats;
    private int level;

    public Boon() {
        god = "???";
        boonName = "???";
        colour = "???";
        flavour = "???";
        stats = "???";
        level = 0;
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

    public String getColour() {
        return colour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int level() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void myBoons(String boons){
        System.out.println("Your boons are: ");
    }
}
