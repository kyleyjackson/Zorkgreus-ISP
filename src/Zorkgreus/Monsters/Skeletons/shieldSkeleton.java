package src.Zorkgreus.Monsters.Skeletons;

import src.Zorkgreus.Monsters.Monsters;

public class shieldSkeleton extends Monsters {
    // subclass for shieldSkeleton, found in the 7th room of floor 1
    public shieldSkeleton() {
        super(7, 7, 20, 30);
    }

    public void displayMonsterMessage() {
        System.out.println("A skeleton with a towering shield that has scratch and burn makrs all over it. ");
    }
}
