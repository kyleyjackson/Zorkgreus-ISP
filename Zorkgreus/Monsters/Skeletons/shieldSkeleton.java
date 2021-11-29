package Zorkgreus.Monsters.Skeletons;

import Zorkgreus.Monsters.Monsters;

public class shieldSkeleton extends Monsters{
    //subclass for shieldSkeleton, found in the 7th room of floor 1
    public shieldSkeleton(){
        super(7, 5, 20, 30);
    }
    public void displayMonsterMessage(){
        System.out.println("A skeleton with a towering shield that has scratch and burn makrs all over it. ");
        System.out.println("He slams it into a ground, creating a heavy impact");
    }
}
