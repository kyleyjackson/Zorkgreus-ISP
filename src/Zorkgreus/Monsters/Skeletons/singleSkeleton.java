package Zorkgreus.Monsters.Skeletons;

import Zorkgreus.Monsters.Monsters;

public class singleSkeleton extends Monsters {
    // subclass for singleSkeleton, found in the 1st room of floor 1
    public singleSkeleton() {
        super(0, 0, 10, 15, "");
    }

    public void displayMonsterMessage() {
        System.out.println("A frail, weak skeleton with a small chipped dagger. ");
    }
}
