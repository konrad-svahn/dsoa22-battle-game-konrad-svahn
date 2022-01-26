import java.util.ArrayList;

public class UserInterface {

    // The class UserInterface is a class that writes out events in the terminal
    public static void printBattleStart (ArrayList<GameCharacter>  charackters) {
        System.out.println("A battle has begun");
        for (int i = 0; i < charackters.size(); i++){
            int j = i+1;
            System.out.println(
                charackters.get(i).getName()+" has "+charackters.get(i).getHelth()+" helth and is number "+j+" in the atack order"
            );
        }
    } 

    public static void printDamage (GameCharacter attacker, GameCharacter deffender, int damage) {
        System.out.println(
            attacker.getName()+" hits "+deffender.getName()+" for "+damage+" damage. "+deffender.getName()+" now has "+deffender.getHelth()+" Health"
        ); 
    }

    public static void printFireDamage (GameCharacter character, int damage) {
        System.out.println(
            character.getName() + " is burning and takes " + damage +" damage "+character.getName()+" now has "+character.getHelth()+" Health"
        );
    }

    public static void printWarning (int max) {
        System.out.println("You must seleckt a number between 1 and "+max+" to continue");
    }

    public static void printRegen () {
        System.out.println("troll");
    }

    public static void printDeath (GameCharacter victim) {
        System.out.println(victim.getName() + " has died");
    }

    public static void printActionPromt (GameCharacter player) {
       
        System.out.println("press 1 to run away");
        System.out.println("press 2 to block");
        System.out.println("press 3 to use " + player.getWeapon().getAttack1());
        System.out.println("press 4 to use " + player.getWeapon().getAttack2());
        System.out.println("press 5 to use your inventory");
    }

    public static void printBlock(GameCharacter character) {
        System.out.println(character.getName() + " raises their sheild to block, reducing all inkomming damage by " + character.getArmour() +" for the rest of the turn");
    }
    
    public static void printEnemySelecktionPromt(ArrayList<GameCharacter> fighters, int player) {

        System.out.println("what enemy do you attack?");
        for (int i = 0; i < fighters.size() - 1; i++){
            int j = i + 1;
            if (i < player) {
                System.out.println("press " + j + " to attack " + fighters.get(i).getName());
            } else {System.out.println("press " + j + " to attack " + fighters.get(i + 1).getName());}
        }
    }

    public static void printGameStart() {
        System.out.println("Two bandits block the bridge before you");
    }
}
