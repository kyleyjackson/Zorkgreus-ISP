package Zorkgreus;

import java.util.ArrayList;

public class Boon {
    private String god;
    private String boonName;
    private String colour;
    private String flavour;
    private String stats;
    private int level;

    public Boon(){
        god = "???";
        boonName = "???";
        colour = "???";
        flavour = "???";
        stats = "???";
        level = 0;
    }

    public Boon(String god, String boonName, String colour, String flavour, String stats, int level) {
        this.god = god;
        this.boonName = boonName;
        this.colour = colour;
        this.flavour = flavour;
        this.stats = stats;
        this.level = level;
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

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
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

    /**
     * Levels up the selected boon if the player already has that boon.
     * @param myBoons ArrayList of player's boons
     * @param tempBoons ArrayList of generated boons for player to pick
     * @param selection boon picked by the player
     */
    public void levelUp(ArrayList<Boon> myBoons, ArrayList<Boon> tempBoons, int selection){
        for(Boon b : myBoons){
          if(tempBoons.get(selection).getBoonName().equals(b.getBoonName())){
            if(b.getLevel() < 3 && !(b.getBoonName() == "Smite" || b.getBoonName() == "First Strike" || b.getBoonName() == "Vitality"
            || b.getBoonName() == "High Tide" || b.getBoonName() == "Second Wind")){ //dont use canLevelAtIndex because player selects from tempBoons, not myBoons
              b.setLevel(b.getLevel() + 1);
              System.out.println("Your boon, " + b.getBoonName() + ", has been upgraded to level " + b.getLevel());
              System.out.println(b.getStats());
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

    /**
     * Levels up a boon from the player's current boons.
     * @param myBoons ArrayList of player's boons
     * @param index index of the boon being levelled
     * 
     * @return T/F, true if can level boon, false if cannot
     */
    public boolean levelUpPom(ArrayList<Boon> myBoons, int index){
        boolean validNum = false;
        int count = 0;
        while(!validNum || count < 100){
            Boon b = myBoons.get(index);
            if(!canLevelAtIndex(myBoons, index)){
                while(!canLevelAtIndex(myBoons, index) || count < 100){
                    index = (int)(Math.random() * myBoons.size());
                    count++;
                }
            }
            b = myBoons.get(index);
            b.setLevel(b.getLevel() + 1);
            System.out.println("Your boon, " + b.getBoonName() + ", has been upgraded to level " + b.getLevel());
            System.out.println(b.getStats());
            validNum = true;
            return true;
        }
        return false;
    }

    /**
     * Checks if a specific boon can be levelled in player's boons.
     * @param myBoons ArrayList of player's boons
     * @param index index of the boon being levelled
     * @return T/F
     */
    public boolean canLevelAtIndex(ArrayList<Boon> myBoons, int index){
        Boon b = myBoons.get(index);
        if((b.getLevel() < 3 && !(b.getBoonName() == "Smite" || b.getBoonName() == "First Strike" || b.getBoonName() == "Vitality"
        || b.getBoonName() == "High Tide" || b.getBoonName() == "Second Wind")) || b.getLevel() >= 3)
            return false;
        else
            return true;
    }

}

