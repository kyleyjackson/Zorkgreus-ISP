package Zorkgreus.Boss;

import java.util.Scanner;

import Zorkgreus.Player;

public class Tarantula extends Boss {
    // subclass for Tarantula, found in the 14th room of floor 2 (boss)

    public Tarantula() {
        super(15, 5, 7, 75, 10);
    }

    public void displayBossMessage() {
        System.out.println(
            "With a snarl, the tarantula whips his head at you after feeding on a dead spider, leaving a trail of venom, eroding away the floor. ");
        System.out.println("Every step he takes is full of power, as his red eyes immediately recognize you as prey. ");
        System.out.println("G0t p@5t th3 0T#3r mUt@7I0n3? Im9f3sS1v3. ");
    }

    //rage called when he falls below 33% hp, increased atk and priority
    public void bossRage() {
        super.addBossAtk(20);
        super.addBossPriority(2);
        displayBossRage();
    }

    //displays the detail of the rage
    public void displayBossRage() {
        System.out.println("The tarantula has fallen into a state of rage. ");
        System.out.println(
                "The colours of his eyes begin to fade into darkness as he lets out a thunderous screech and pounces on you. ");
        System.out.println("1GnOr@n7 f0ol! ");
    }

    //special attack for the boss, deals DOT for one attack
    public void specialBossAttack(int startHP) {
        if (super.getHP() <= (startHP * 0.7)) {

            // when the person gets attacked the first time the boss is under 70% hp, apply
            // poison which ticks 10 hp away from the person for 2 turns
        }
        displayBossSpecialAttack();
    }

    // displays the detail of the special attack
    public void displayBossSpecialAttack() {
        System.out.println("The tarantulas fangs are now laced with deadly venom. ");
    }

    //final attack when the tarantula dies
    public void finalBossAttack(){
        Scanner in = new Scanner(System.in);
    }
}
