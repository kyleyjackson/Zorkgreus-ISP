package Zorkgreus.NPC;

import java.util.Scanner;
import Zorkgreus.Player;


public class Patroclus extends NPC {
    private String pastRoom = "F3 NPC Room";

    public Patroclus(Player player) {
        displayIntroMessage();
        System.out.println("");
        System.out.println("");
        
        displayChoices(player);
    }

    public void displayIntroMessage() {
        System.out.println("Sitting down on a rock in the middle of the room, this man looks dejected, his eyes looking miserable and discouraged, his spirit lost. ");
        System.out.println("Donning a long cyan cape, a golden headband with blue laurels and a sharpened spear, it's clear that he's a fallen warrior. ");
        System.out.println("Why have you come here stranger? Are you searching for love? Glory? Fame? The outside world offers none of that. ");
        System.out.println("Here. Why don't you take this? A gift from me to you. I wish you luck in your journey. ");
    }

    public void displayChoices(Player player) {
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean validInput = false;
        boolean displayExtraLifeMessage = false;
        System.out.println("Patroculus has offered you 3 items: ");
        System.out.println("Kiss of Styx Premium - Replenishes your extra life ");
        System.out.println("Hyrdalite Gold - Restore 10% of your max HP when you enter a room ");
        System.out.println("Cyclops Jerky Select - Your attack deals 30% extra damage ");
        while (!validInput) {
            if(displayExtraLifeMessage)
                System.out.print("Invalid Inpupt - [H]ydralite Gold, or [C]yclops Jerky Select: ");
            else if(player.getExtraLife()){
            System.out.println("Because you have an extra life already, you cannot select the Kiss of Styx Premium. ");
            System.out.print("Which one would you like to select, [H]ydralite Gold, or [C]yclops Jerky Select: ");
            }else if (displayFirstMessage)
                System.out.print("Which one would you like to select, [K]iss of Sty Premium, [H]ydralite Gold, or [C]yclops Jerky Select: ");
            else
                System.out.print("Invalid Input - [K]iss of Sty Premium, [H]ydralite Gold, or [C]yclops Jerky Select: ");
            try {
                String msg = in.nextLine().toUpperCase();
                if(player.getExtraLife()&&((msg.equals("K")|| msg.equals("KISS OF STYX PREMIUM"))))
                    displayExtraLifeMessage = true;
                else if (msg.equals("K")
                        || msg.equals("KISS OF STYX PREMIUM")
                        || msg.equals("H")
                        || msg.equals("HYRDALITE GOLD")
                        || msg.equals("C")
                        || msg.equals("CYCLOPS JERKY SELECT")) {
                       if(msg.equals("K")||msg.equals("KISS OF STYX PREMIUM")){
                            player.setExtraLife(true);
                       }else if(msg.equals("H")||msg.equals("HYDRALITE GOLD")){
                            player.setHydraliteGold(true);
                            //code rest of feature later
                       }else{
                            player.addPlayerAttack((int)(player.getPlayerAtk()*0.3));
                       }
                      validInput = true;
                }else{
                    if(player.getExtraLife()){
                        displayExtraLifeMessage = true;
                    }
                    displayFirstMessage = false;
                }
            } catch (Exception ex) {
                if(player.getExtraLife()){
                    displayExtraLifeMessage = true;
                }
                displayFirstMessage = false;
            }
        }
    }

    public String getPastRoom(){
        return pastRoom;
    }

    public void setPastRoom(String newRoom){
        pastRoom = newRoom;
    }

}
