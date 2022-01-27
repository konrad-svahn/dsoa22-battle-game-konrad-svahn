import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CombatManager {

    //CombatManager contains methods needed for the games combat loop
    private static boolean isGameOver = false;

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void runEncounter(Scanner scannAction ,ArrayList<GameCharacter> fighters) {

        int actionP;
        int player;
        int enemyToAttack = 0;
        int seleckted;
        boolean deleteItems;
        boolean addAndRemoveWheaponFromInventory = true;

        // sets all fighters health to be eqal to their max health 
        initialiseHelth(fighters);
        UserInterface.printGameStart();
        sortCharacters(fighters);
        UserInterface.printBattleStart(fighters);
                
        // the main combat loop, one loop is one round of combat
        mainCombatLoop: while (true) {

            player = whoIsPlayer(fighters);
            Player playerP = (Player) fighters.get(player);

            //nullifies the last turns block by resetting armour to 0 
            fighters.get(player).setArmour(0);

            while (true) {
                //promts a player input and reads that input
                UserInterface.printActionPromt(fighters.get(player));
                actionP = playerAction(scannAction, 5, 0); 

                //performs the player actions that must ocur before the first attack of the turn
                if (actionP == 1) {//run awway
                    break mainCombatLoop;
                } else if (actionP == 5) {//acces inventory

                    // if addAndRemoveWheaponFromInventory is inabled asks the player if they want too equip or delete whepons 
                    if (addAndRemoveWheaponFromInventory) {
                        UserInterface.equipOrDelete();
                        seleckted = playerAction(scannAction, 2, 0);
                        if (seleckted == 2) {
                            deleteItems = true;
                        } else {
                            deleteItems = false;
                        }
                    } else {
                        deleteItems = false; 
                    }
                   

                    UserInterface.printInventory(fighters.get(player));   
                    // loop that keaps you inside the inventory unless you press q to exit
                    while (true) {
                        seleckted = inventoryChoice(scannAction,playerP.inventory.size());

                        if (seleckted > 0) {
                            System.out.println(";)");
                        // breaks the loop if you presed q
                        } else if (seleckted == 0) {break;}
                    }                  
                } else {break;}
            }
            
            //asks what enimy to attack if the player decides to use an attack action
            if (actionP == 3 || actionP == 4) {
                 
                UserInterface.printEnemySelecktionPromt(fighters,player);
                enemyToAttack = fighters.size();

                //makes it inposible for the player to attack self and helps to avoid an out of bounds error 
                while (enemyToAttack == fighters.size()) {
                enemyToAttack = playerAction(scannAction, fighters.size(), -1);
                if (enemyToAttack == fighters.size()) {UserInterface.printWarning(fighters.size() - 1);}
                }
                
                if (enemyToAttack <= player) {
                    enemyToAttack = enemyToAttack - 1;
                }
            }

            // starts loping through fighter and performs ecah charackters action for the turn 
            if(actionLoop(actionP, fighters, player, enemyToAttack)){break;}   
        }
    } 

    // returns true if player is dead or if all enemies are dead
    private static boolean actionLoop(int actionP, ArrayList<GameCharacter> fighters, int player, int enemyToAttack){
       
        int action;
        int size;
        Attacks attackType;
        Random ran = new Random();

        // loops through fighter and performs the charackters actions
        for (int i = 0; i < fighters.size(); i++) {

            //size is the amount of fighters att the begining of each characktes action we will compare this with the actual fighters.size() to se if anyone died
            size = fighters.size();
                
            // if the curent fighter is the player the next action is set to the players action, otherwise randomly seleckts an atack for the enemy to perform
            if (fighters.get(i).isPlayer) { action = actionP;} 
            else { action =  ran.nextInt(2) + 3;}

            // if statements that check what the next action is and performs it 
            if (action == 2) {
                //block
                fighters.get(player).setArmour(40);
                UserInterface.printBlock(fighters.get(player));
            } else if (action == 3 || action == 4) {
                // performs attack1 or attack 2
                
                // sets attackType to be the wheapons first attack if action is the player chose to use the first attack, or the second if the player chose the second  
                if (action == 3) {
                    attackType = fighters.get(i).getWeapon().getAttack1();
                } else {
                    attackType = fighters.get(i).getWeapon().getAttack2();
                }
        
                // if the curent fighter is the player they attack the enemy the player seleckted 
                if(fighters.get(i).isPlayer){
                    fighters.get(i).attack(fighters.get(enemyToAttack), attackType);

                    // this if statement fixes a bug but i have no idea why it works
                    if (fighters.get(enemyToAttack).isDead() && fighters.get(enemyToAttack).getInitiative() < fighters.get(player).getInitiative()) {
                        size = size - 1;
                    }
                    if (isKillingBow(fighters.get(enemyToAttack), fighters)) {
                        return true;
                    } 
                    //reajusts the curent fighter if the total amount of fighters has decreased and the killed fighter had a higer initiative than the player
                    if (fighters.size() == size - 1 ) {i = i - 1;}
                    //reajusts which fighter the player is
                    player = whoIsPlayer(fighters);
                    System.out.println(fighters.size());
                // else the curent fighter attacks the player 
                }else{
                    fighters.get(i).attack(fighters.get(player), attackType);
                    if (isKillingBow(fighters.get(player), fighters)) {
                        return true;
                    }
                }
            }  
           
        }

        for (int i = 0; i < fighters.size(); i++) {

            // checks if the fighter is on fire and deals fire damage to them if they are
            if (fighters.get(i).getTurnsOnFireLeft() > 0) {
                UserInterface.printFireDamage(fighters.get(i), fighters.get(i).TakeFireDamage());
                if (isKillingBow(fighters.get(i), fighters)) {return true;}
            }
        }
        // returns false if the batle did not end during the round of combat
        return false;
    }

    //sorts by higest initiative
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
     
    // player action recives a player input and makes sure it is an int between 1 and length.
    // promtMod modifies the error message, 
    // so if you for example take the returned value of this method -2 after using it you can ajust what the metod prints by entering promtMod -2 
    private static int playerAction(Scanner scanAction, int length, int promtMod) {  
        boolean warning = false; 
        while (true){
            if (warning){UserInterface.printWarning(length + promtMod);}
            warning = true;
            if (scanAction.hasNext()){String input = scanAction.nextLine();
                if (testInput(input, length) != 0) {
                    return testInput(input, length);
                }
            }
        }
    }

    private static int inventoryChoice(Scanner scanAction, int length) {
        boolean warning = false;
        while (true) {
            if (warning){UserInterface.printWarning(length);}
            warning = true;
            String input = scanAction.nextLine();
            if (input.equals("q")) {
                return 0;
            } else {
                if (testInput(input, length) != 0) {
                    return testInput(input, length);
                }
            } 
        }
    }

    private static int testInput(String input, int length){
        if (input.matches("-?\\d+")) {
            int intput = Integer.valueOf(input);
            if (intput <= length && intput > 0) {
                return intput;
            }
        } 
        return 0;
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

    private static boolean isKillingBow(GameCharacter defender, ArrayList<GameCharacter> fighters) {
        if (defender.isDead()) {
            UserInterface.printDeath(defender);

            if (defender.isPlayer) {
                isGameOver = true;
                return true;
            } else {
                if (fighters.size() <= 2) {
                    return true;
                }
                fighters.remove(defender);
            }  
        }
        return false;
    }   

    private static int whoIsPlayer(ArrayList<GameCharacter> fighters){
        int player = 0;
        //Figures out the players place in the attack order and saves it as an int
        for (int i = 0; i < fighters.size(); i++) {      
            if (fighters.get(i).isPlayer) {
                player = i;
            } 
        }
        return player;
    }
}
