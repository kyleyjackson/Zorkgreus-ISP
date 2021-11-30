package Zorkgreus;

public class Boon {
    private String god;
    private String boonName;
    private String colour;
    private String stats;
    private int level;

    public Boon() {
        god = "Unknown";
        boonName = "Invalid";
        colour = null;
        stats = null;
        level = 0;
    }

    /* displays the information of the boon to the player */
    public String displayBoon() {
        return "Boon of " + god + " :" + boonName + "\n\n" + stats + "\n\n" + colour;
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

    public void setColour(String colour) {
        this.colour = colour;
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
}
