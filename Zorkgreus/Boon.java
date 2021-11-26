package Zorkgreus;

import java.util.ArrayList;

public class Boon {
    private String god;
    private String boonName;
    private ArrayList<String> stats = new ArrayList<>();

    public Boon(){
        god = "Unknown";
        boonName = "Invalid";
        stats = null;
    }

    public Boon(String god, String boonName){
        this.god = god;
        this.boonName = boonName;
        this.stats = null;
    }

    public String displayBoon() {
        return "Boon of " + god + " :" + boonName + "\n\n" + stats;
    }

    public String getGodName() {
        return god;
    }

    public void setGod(String godName){
        god = godName;
    }

    public String getBoonName(){
        return boonName;
    }

    public void setBoonName(String boonName){
        this.boonName = boonName;
    }
}
