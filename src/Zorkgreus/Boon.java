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

    /**
     * Gets the levels of all boons
     * @return the levels ArrayList
     */
    public ArrayList<Integer> getLevel() {
        return levels;
    }

    /**
     * Sets the levels of all boons
     * @param levels ArrayList of all boon levels
     */
    public void setLevel(ArrayList<Integer> levels) {
        this.levels = levels;
    }

    /**
     * Gets the level of an individual boon
     * @param index in the levels ArrayList
     * @return index in the levels ArrayList
     */
    public int getBoonLevel(int index){
        return levels.get(index);
    }

    /**
     * Sets the level of an individual boon
     * @param level value to set level to
     * @return param value
     */
    public int setBoonLevel(int level){
        return 69420;
    }

    /**
     * Levels up the selected boon if the player already has that boon
     * @param myBoons array of player's boons
     * @param tempBoons array of generated boons for player to pick
     * @param selection boon which the player picked
     */
    public void levelUp(ArrayList<Boon> myBoons, ArrayList<Boon> tempBoons, int selection){
        Boon b = new Boon();
        for(int i = 0; i < myBoons.size(); i++){
          if(tempBoons.get(selection).getBoonName().equals(b.getBoonName())){
            if(b.getBoonLevel(i) < 3 && b.levels.size() > 1){
              b.setBoonLevel(b.getBoonLevel(i) + 1);
            }
          }
          else{
              if(b.getBoonLevel(i) >= 3){
                System.out.println("This boon can't be levelled up any further!");
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
