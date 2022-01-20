public class UserInterface {

    public static void printBattleStart(GameCharacter[] charackters){
        System.out.println("A battle has begun");
        for(int i = 0; i < charackters.length; i++){
            int j = i+1;
            System.out.println(
                charackters[i].getName()+" has "+charackters[i].getHelth()+" helth and is number "+j+" in the atack order"
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
