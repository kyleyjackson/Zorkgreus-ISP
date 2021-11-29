package src.Zorkgreus.Monsters.Skeletons;

import src.Zorkgreus.Monsters.Monsters;

public class strongerSkeleton extends Monsters {
    // subclass for strongerSkeleton, found in the 2nd room of floor 1
    public strongerSkeleton() {
        super(5, 5, 10, 15);
    }

    public void displayMonsterMessage() {
        System.out.println("A slighty stronger skeleton with a sword. ");
    }
}
