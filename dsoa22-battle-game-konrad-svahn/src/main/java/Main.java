import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String name;
        
            
        System.out.println("Enter your name");

        name = scanner.nextLine();
        GameCharacter player1 = new GameCharacter(name,100,25,5,true);

        System.out.println("Game start");
        System.out.println("A black knight blocks the bridge before you");
        

            
        GameCharacter enemy1 = new GameCharacter("the black knight", 500,25,4,false);

        GameCharacter[] battle1 = {enemy1,player1};
        sortCharacters(battle1);
        printBattleStart(battle1);
        

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

    public static GameCharacter[] sortCharacters(GameCharacter[] charackters){
        for(int i = 0; i < charackters.length; i++){
            int bigest = 0;
            for(int j = 0 + i; j < charackters.length; j++){
                if(j == 0 + i){bigest = j;}
                else if(charackters[j].getInitiative() > charackters[j-1].getInitiative()){bigest = j;}
            }
            //System.out.println(charackters[0].getInitiative()+""+charackters[1].getInitiative()+""+charackters[2].getInitiative()+""+charackters[3].getInitiative());
            GameCharacter temp = charackters[i];
            charackters[i] = charackters[bigest];
            charackters[bigest] = temp;
        }
        return charackters;
    }

    public static void printDamage(GameCharacter attacker, GameCharacter deffender){
        System.out.println(
            attacker.getName()+" hits "+deffender.getName()+" for "+attacker.getAttackPower()+" damage. "+deffender.getName()+" now has "+deffender.getHelth()+" Health"
        ); 
    }

    public static void printRegen(){
        System.out.println("troll");
    }

    public static void printBattleStart(GameCharacter[] charackters){
        System.out.println("A battle has begun");
        for(int i = 0; i < charackters.length; i++){
            int j = i+1;
            System.out.println(
                charackters[i].getName()+" has "+charackters[i].getHelth()+" helth and is number "+j+" in the atack order"
            );
        }
    }

}
