import java.util.ArrayList;
import java.util.Scanner;

public class CombatManager {
    
    public static ArrayList<GameCharacter> sortCharacters(ArrayList<GameCharacter> charackters){
        for(int i = 0; i < charackters.size(); i++){
            //System.out.println(charackters.get(0).getInitiative()+""+charackters.get(1).getInitiative()+""+charackters.get(2).getInitiative()+""+charackters.get(3).getInitiative());
            int bigest = 0;
            for(int j = 0 + i; j < charackters.size(); j++){
                if(j == 0 + i){bigest = j;}
                else if(charackters.get(j).getInitiative() > charackters.get(bigest).getInitiative()){bigest = j;}
            }
            GameCharacter temp = charackters.get(i);
            charackters.set(i,charackters.get(bigest)); 
            charackters.set(bigest,temp);
        }
        return charackters;
    }

    public static void runEncounter(ArrayList<GameCharacter> fighters){

        try (Scanner scanAction = new Scanner(System.in)) {
            
            UserInterface.printGameStart();
            sortCharacters(fighters);
            UserInterface.printBattleStart(fighters);
                
            while (true) {
                playerAction(scanAction);
                fighters.get(0).attack(fighters.get(1));
    
                if (fighters.get(1).isDead()){
                    UserInterface.printDeath(fighters.get(0), fighters.get(1));
                    break;
                } 
                        
                fighters.get(1).attack(fighters.get(0));
    
                if (fighters.get(0).isDead()){
                    UserInterface.printDeath(fighters.get(1), fighters.get(2));
                    break;
                } 
            }
        } catch (Exception e) {
            System.out.println(e);}
    }
     

    private static int playerAction(Scanner scanAction){
        while(true){
            String input = scanAction.nextLine();
            if(input.matches("-?\\d+")){
                System.out.println(";)");
                return Integer.valueOf(input);
        
                //if(scanAction.nextInt() == 0){System.out.println(";)"); 
                //} else {System.out.println(";[");}
            }
        }
    }
}
