package src.Zorkgreus.Monsters.Skeletons;

import src.Zorkgreus.Monsters.Monsters;

public class suspiciousSkeleton extends Monsters {
    // subclass for suspiciousSkeleton, found in the 6th room of floor 1
    public suspiciousSkeleton() {
        super(7, 10, 15, 15);
    }

    public void displayMonsterMessage() {
        System.out.println(
                "A suspicious skeleton, wielding a 3 metre long rotten baseball bat that seems to light for him comes into the light. ");
    }
}
