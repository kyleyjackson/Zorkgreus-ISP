package Zorkgreus;

import java.util.ArrayList;

public class Boon {
    private String god;
    private String boonName;
    private String colour;
    private String flavour;
    private ArrayList<String> stats;
    private int level;

    public Boon() {
        god = "???";
        boonName = "???";
        colour = "???";
        flavour = "???";
        stats = new ArrayList<String>();
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

    /**
     * Gets the levels of all boons
     * @return the levels ArrayList
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the levels of all boons
     * @param levels ArrayList of all boon levels
     */
    public void setLevel(int level) {
        this.level = level;
    }

    public String boonDescription(int level){
        String stat = stats.get(level);
        return stat;
    }

    /**
     * Levels up the selected boon if the player already has that boon
     * @param myBoons array of player's boons
     * @param tempBoons array of generated boons for player to pick
     * @param selection boon which the player picked
     */
    public void levelUp(ArrayList<Boon> myBoons, ArrayList<Boon> tempBoons, int selection){
        for(Boon b : myBoons){
          if(tempBoons.get(selection).getBoonName().equals(b.getBoonName())){
            if(b.getLevel() < 3 && (b.getBoonName() == "Smite" || b.getBoonName() == "First Strike" || b.getBoonName() == "Vitality"
            || b.getBoonName() == "High Tide" || b.getBoonName() == "Second Wind" || b.getGodName() == "Chaos")){
              b.setLevel(b.getLevel() + 1);
              System.out.println("Your boon, " + b.getBoonName() + ", has been upgraded to level " + b.getLevel());
            }
          }
          else{
              if(b.getLevel() >= 3){
                System.out.println("This boon is max level.");
              }
              else{
                System.out.println("You can't level up this boon!");
              }
          }
        }
    }

    public void myBoons(String boons){
        System.out.println("Your boons are: ");
    }
}
