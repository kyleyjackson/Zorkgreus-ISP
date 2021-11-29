package src.Zorkgreus.Monsters.Skeletons;

import src.Zorkgreus.Monsters.Monsters;

public class derangedSkeleton extends Monsters {
    // subclass for derangedSkeleton, fouund in the 8th room of floor 1
    public derangedSkeleton() {
        super(15, 9, 20, 15);
    }

    public void displayMonsterMessage() {
        System.out.println("A skeleton slowly lumbers towards you, tugging on a bone in his body.");
        System.out.println("As the bone pops out with a snap, he looks at it approvingly as his lips curl into a snarl.");
    }
}
