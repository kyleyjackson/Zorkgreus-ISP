package Zorkgreus.Monsters.Skeletons;

import Zorkgreus.Monsters.Monsters;

public class rangedSkeleton extends Monsters{
    //subclass for rangedSkeleton, found in the 3rd room on floor 1
    public rangedSkeleton(){
        super(3, 9, 10, 10);
    }
    public void displayMonsterMessage(){
        System.out.println("A frail, weak skeleton with a bow. ");
    }
}
