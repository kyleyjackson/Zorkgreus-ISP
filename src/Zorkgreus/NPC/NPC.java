package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Player;

public class NPC {

    // all methods below exist just for polymorphism - allowing subclasses to override
    public void displayIntroMessage(){
    }

    public void displayChoices(Player player){
    }

    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> generatedBoons, ArrayList<Boon> playerBoons){
        return playerBoons;
    }

}
