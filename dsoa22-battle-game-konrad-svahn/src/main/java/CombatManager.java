import java.util.ArrayList;
import java.util.Scanner;

public class CombatManager {

    //CombatManager contains methods needed for the games combat loop
    private static boolean isGameOver = false;

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static ArrayList<GameCharacter> sortCharacters(ArrayList<GameCharacter> charackters) {
        for (int i = 0; i < charackters.size(); i++) {
            //System.out.println(charackters.get(0).getInitiative()+""+charackters.get(1).getInitiative()+""+charackters.get(2).getInitiative()+""+charackters.get(3).getInitiative());
            int bigest = 0;
            for (int j = 0 + i; j < charackters.size(); j++) {
                if (j == 0 + i) {bigest = j;
                } else if (charackters.get(j).getInitiative() > charackters.get(bigest).getInitiative()){bigest = j;}
            }
            GameCharacter temp = charackters.get(i);
            charackters.set(i,charackters.get(bigest)); 
            charackters.set(bigest,temp);
        }
        return charackters;
    }

    public static void runEncounter(Scanner scannAction ,ArrayList<GameCharacter> fighters) {

            UserInterface.printGameStart();
            sortCharacters(fighters);
            UserInterface.printBattleStart(fighters);
                
            while (true) {
                playerAction(scannAction);
                fighters.get(0).attack(fighters.get(1));
    
                if (fighters.get(1).isDead()) {
                    UserInterface.printDeath(fighters.get(0), fighters.get(1));
                    break;
                } 
                        
                fighters.get(1).attack(fighters.get(0));
    
                if (fighters.get(0).isDead()) {
                    UserInterface.printDeath(fighters.get(1), fighters.get(0));
                    isGameOver = true;
                    break;
                } 
            }
    } 
     
    private static int playerAction(Scanner scanAction ) {   
        while (true){scanAction.tokens();
            if (scanAction.hasNext()){String input = scanAction.nextLine();
                if (input.matches("-?\\d+")) {
                    return Integer.valueOf(input);
                }
            }
        }
    }

    public static boolean endOfBattleChoise(Scanner scanAction) {
        String input = scanAction.nextLine();
    
        if (input.equals("q")) {
            return true;
        } else {
            return false;
        } 
    }
}
