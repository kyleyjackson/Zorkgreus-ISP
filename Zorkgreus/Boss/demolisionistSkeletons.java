package Zorkgreus.Boss;

import java.util.Scanner;

public class demolisionistSkeletons extends Boss{
    //subclass for demolisionistSkeletons (Richie and Harrison), found in the 5th room of floor 1
    private int hp;
    private boolean isAlive;
    Scanner in = new Scanner(System.in);

    public demolisionistSkeletons(){
        super(5, 12, 15, 40);
        hp = 15;
        isAlive = true;
    }
    public void isAlive(){
        if(hp<=0)
        isAlive = false;
}

    public void specialAttack(){
        if(isAlive==false){
            System.out.println("A skeleton demolisionist has died, its dropped a bomb, if you are fast enough you can dodge the bomb, please input a number to see [1-10]: ");
            try{
            String num = in.nextLine().toUpperCase();
            int bombNum = (int)(Math.random()*10) + 1;

            }catch(Exception ex){
                System.out.println("Invalid Input - Number Between [1-10]");
            }
        }
}
}
