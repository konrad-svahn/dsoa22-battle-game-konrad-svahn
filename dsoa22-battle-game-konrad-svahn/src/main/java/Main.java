public class Main {

    public static void main(String[] args) {
          System.out.println("Game start");
          System.out.println("A black knight blocks the bridge before you");
          System.out.println("A battle has begun");

          GameCharacter player1 = new GameCharacter("player1",100,25);
          GameCharacter enemy1 = new GameCharacter("the black knight", 500,25);

          System.out.println(enemy1.getName()+" has "+enemy1.getHelth()+" health");
          System.out.println(player1.getName()+" has "+player1.getHelth()+" health");

          while (true) {
            enemy1.takeDamage(player1.getAttackPower());
            printDamage(player1,enemy1);
            
            if (enemy1.getHelth() <= 0){
                System.out.println(player1.getName()+" has won the battle");
                System.out.println("satisfied with your vicktory, you cross the bridge");
                break;
            } 
            
            player1.takeDamage(enemy1.getAttackPower());
            printDamage(enemy1,player1);

            if (player1.getHelth() <= 0){
                System.out.println(enemy1.getName()+" has won the battle");
                System.out.println("you died");
                break;
            }
        }

          
    }

    public static void printDamage(GameCharacter attacker, GameCharacter deffender){
        System.out.println(
            deffender.getName()+" recived "+attacker.getAttackPower()+" damage. "+deffender.getName()+" now has "+deffender.getHelth()+" Health"
        ); 
    }

}
