import java.util.ArrayList;

public class UserInterface {

    public static void printBattleStart(ArrayList<GameCharacter>  charackters){
        System.out.println("A battle has begun");
        for(int i = 0; i < charackters.size(); i++){
            int j = i+1;
            System.out.println(
                charackters.get(i).getName()+" has "+charackters.get(i).getHelth()+" helth and is number "+j+" in the atack order"
            );
        }
    } 

    public static void printDamage(GameCharacter attacker, GameCharacter deffender, int damage){
        System.out.println(
            attacker.getName()+" hits "+deffender.getName()+" for "+damage+" damage. "+deffender.getName()+" now has "+deffender.getHelth()+" Health"
        ); 
    }

    public static void printRegen(){
        System.out.println("troll");
    }
}
