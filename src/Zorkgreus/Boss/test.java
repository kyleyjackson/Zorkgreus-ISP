package Zorkgreus.Boss;

public class test {
    public static void main(String[] args) {
        Boss temp = new QueenSpider();
        while(temp.getHP()>=1){
           temp.specialBossAttack(); 
           temp.adBossHP(-4);
        }

    }
}
