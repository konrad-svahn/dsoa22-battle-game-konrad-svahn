import java.util.ArrayList;
import java.util.Random;
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

        int actionP;
        int player = 0;
        int enemyToAttack = 0;

        UserInterface.printGameStart();
        sortCharacters(fighters);
        initialiseHelth(fighters);
        UserInterface.printBattleStart(fighters);

        for (int i = 0; i < fighters.size(); i++) {      
            if (fighters.get(i).isPlayer) {
                player = i;
            } 
        }
                
        while (true) {
            UserInterface.printActionPromt(1);
            actionP = playerAction(scannAction, 5); 
            if (actionP == 1) {//run
                break;
            } else if (actionP == 5) {//inventory

            }
            
            if (actionP == 3 || actionP == 4) {
                UserInterface.printActionPromt(2);

                enemyToAttack = playerAction(scannAction, fighters.size()) - 1;

                if (enemyToAttack == player && player != 0) {
                    enemyToAttack = 0;
                } else if (enemyToAttack == player && player == 0) {
                    enemyToAttack = 1;  
                }
            }

            if(actionLoop(actionP, fighters, player, enemyToAttack)){break;}
             
        }
    } 
     
    private static int playerAction(Scanner scanAction, int length) {   
        while (true){
            
            if (scanAction.hasNext()){String input = scanAction.nextLine();
                if (input.matches("-?\\d+")) {
                    int intput = Integer.valueOf(input);
                    if (intput <= length && intput > 0) {
                        return intput;
                    }
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

    private static void initialiseHelth(ArrayList<GameCharacter> fighters){
        for (int i = 0; i < fighters.size(); i++) {
            fighters.get(i).resetHelth();
        }
    }

    private static boolean isKillingBow(GameCharacter attacker, GameCharacter defender) {
        if (defender.isDead()) {
            UserInterface.printDeath(attacker, defender);
            if (defender.isPlayer) {isGameOver = true;}
            return true;
        } else {
            return false;
        }
    }

    private static boolean actionLoop(int actionP, ArrayList<GameCharacter> fighters, int player, int enemyToAttack){

        int action;
        Random ran = new Random();

        for (int i = 0; i < fighters.size(); i++) {
                
            if (fighters.get(i).isPlayer) {
                action = actionP;
            } else {
                action =  ran.nextInt(2) + 3;
            }

            System.out.println("action is: "+action);

            if (action == 2) {//block

            } else if (action == 3) {//1
                return attackLoop(fighters, i, player, enemyToAttack); 
            } else if (action == 4) {//2
                return attackLoop(fighters, i, player, enemyToAttack);
            }   
        }
        return false;
    }

    private static boolean attackLoop(ArrayList<GameCharacter> fighters, int current, int player, int enemyToAttack){
        
        if(fighters.get(current).isPlayer){
            fighters.get(current).attack(fighters.get(enemyToAttack));
            return isKillingBow(fighters.get(current), fighters.get(enemyToAttack));
                
            
        }else{
            fighters.get(current).attack(fighters.get(player));
            return isKillingBow(fighters.get(current), fighters.get(player));
        }
    }
}
