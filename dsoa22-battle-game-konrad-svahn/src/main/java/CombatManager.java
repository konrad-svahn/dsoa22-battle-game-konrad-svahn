import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CombatManager {

    static boolean addAndRemoveWheaponFromInventory = true;

    //CombatManager contains methods needed for the games combat loop
    private static boolean isGameOver = false;

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void runEncounter(Scanner scannAction ,ArrayList<GameCharacter> fighters) {

        int actionP;
        int playerNum;
        int enemyToAttack = 0;
        int turn = 0;
        Player playerP;

        // sets all fighters health to be eqal to their max health 
        initialiseHelth(fighters);
        UserInterface.printGameStart();
        sortCharacters(fighters);
        UserInterface.printBattleStart(fighters);
                
        // the main combat loop, one loop is one round of combat
        mainCombatLoop: while (true) {
            turn++;
            UserInterface.printTurnStart(turn);

            playerNum = whoIsPlayer(fighters);
            playerP = (Player) fighters.get(playerNum);

            //nullifies the last turns block by resetting armour to 0 
            fighters.get(playerNum).setArmour(0);
            if (playerP.getChargeLevel() <= 0) {
                while (true) {
                    //promts a player input and reads that input
                    UserInterface.printActionPromt(fighters.get(playerNum));
                    actionP = playerAction(scannAction, 5, 0); 
    
                    //performs the player actions that must ocur before the first attack of the turn
                    if (actionP == 1) {//run awway
                        UserInterface.printRunAway();
                        break mainCombatLoop;
                    } else if (actionP == 5) {//acces inventory  
                        inventoryManager(playerP, scannAction);
                    } else {break;}
                }
           
           
                //asks what enimy to attack if the player decides to use an attack action
                if (actionP == 3 || actionP == 4) {

                    Attacks attackType = getAttackType(actionP, fighters.get(playerNum));
                    if (attackType != Attacks.DO_NOTHING && attackType != Attacks.REGENERATE ) {
                    
                        UserInterface.printEnemySelecktionPromt(fighters,playerNum);
                        enemyToAttack = fighters.size();

                        //makes it inposible for the player to attack self and helps to avoid an out of bounds error 
                        while (enemyToAttack == fighters.size()) {
                        enemyToAttack = playerAction(scannAction, fighters.size(), -1);
                        if (enemyToAttack == fighters.size()) {UserInterface.printWarning(fighters.size() - 1);}
                        }
                        
                        if (enemyToAttack <= playerNum) {
                            enemyToAttack = enemyToAttack - 1;
                        }
                    }
                } 
            } else {
                actionP = 3;
            }

            // starts loping through fighter and performs ecah charackters action for the turn 
            if(actionLoop(actionP, fighters, playerP, playerNum, enemyToAttack, scannAction)){break;}   
        }
    } 

    // returns true if player is dead or if all enemies are dead
    private static boolean actionLoop(int actionP, ArrayList<GameCharacter> fighters, Player playerP, int playerNum, int enemyToAttack, Scanner scanAction){
       
        int action;
        Attacks attackType;
        Random ran = new Random();

        // loops through fighter and performs the charackters actions
        for (int i = 0; i < fighters.size(); i++) {
                
            // if the curent fighter is the player the next action is set to the players action, otherwise randomly seleckts an atack for the enemy to perform
            if (fighters.get(i).isPlayer) { action = actionP;} 
            else { action =  ran.nextInt(2) + 3;}

            // if statements that check what the next action is and performs it 
            if (action == 2) {
                //block
                fighters.get(playerNum).setArmour(60);
                UserInterface.printBlock(fighters.get(playerNum));
            } else if (action == 3 || action == 4) {
                // performs attack1 or attack 2
                
                // sets attackType to be the wheapons first attack if action is the player chose to use the first attack, or the second if the player chose the second  
                attackType = getAttackType(action, fighters.get(i));

                // if the curent fighter is the player they attack the enemy the player seleckted 
                if(fighters.get(i).isPlayer){
                    fighters.get(i).attack(fighters.get(enemyToAttack), fighters, attackType); 
                    for (int j = 0; j < fighters.size(); j++) {
                        if (fighters.get(j).isDead()) {
                            UserInterface.printDeath(fighters.get(j));
                            addToInventory(fighters, j, playerP, scanAction);
                        } 
                    }
                // else the curent fighter attacks the player 
                }else if (fighters.get(i).getHelth() > 0) {
                    fighters.get(i).attack(fighters.get(playerNum), fighters, attackType);
                    if (fighters.get(playerNum).isDead()) {
                        UserInterface.printDeath(fighters.get(playerNum));
                        isGameOver = true;
                        return true;
                    }
                }
            }    
        }
        if (removeDead(fighters)) {
            return true;
        }
        for (int i = 0; i < fighters.size(); i++) {
            // checks if the fighter is on fire and deals fire damage to them if they are
            if (fighters.get(i).getTurnsOnFireLeft() > 0) {
                UserInterface.printFireDamage(fighters.get(i), fighters.get(i).TakeFireDamage());
                if (fighters.get(i).isPlayer && fighters.get(i).isDead()) {
                    isGameOver = true;
                    return true;
                } else if (fighters.get(i).isDead()) {
                    UserInterface.printDeath(fighters.get(i));
                    addToInventory(fighters, enemyToAttack, playerP, scanAction);
                }
            }
        }
        if (removeDead(fighters)) {
            return true;
        }
        // returns false if the batle did not end during the round of combat
        return false;
    }

    public static void inventoryManager(Player player, Scanner scanAction) {

        int seleckted;
        boolean deleteItems;

        // if addAndRemoveWheaponFromInventory is inabled asks the player if they want too equip or delete whepons 
        if (addAndRemoveWheaponFromInventory) {
            UserInterface.equipOrDelete();
            seleckted = playerAction(scanAction, 2, 0);
            if (seleckted == 2) {
                deleteItems = true;
            } else {
                deleteItems = false;
            }
        } else {
            deleteItems = false; 
        }
        UserInterface.printInventory(player);   
        // loop that keaps you inside the inventory unless you press q to exit
        while (true) {
            UserInterface.printInventoryActionPromt(deleteItems);
            seleckted = inventoryChoice(scanAction,player.getInventorySize());
            if (seleckted > 0) {
                if (deleteItems == true) {
                    player.removeFromInventory(seleckted - 1);
                } else {
                    player.setWeapon(player.getFromInventory(seleckted - 1));
                    UserInterface.printEquipMessage(player.getWeapon());
                    break;
                }
            // breaks the loop if you presed q
            } else if (seleckted == 0) {break;}
        } 
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
    public static int playerAction(Scanner scanAction, int length, int promtMod) {  
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

    public static boolean endOfBattleChoise(Player player,Scanner scanAction) {
        String input = scanAction.nextLine();
    
        if (input.equals("q")) {
            UserInterface.printSaveChoisePromt();
            while (true) {
                UserInterface.printSaveChoise();
                input = scanAction.nextLine();
                if (input.equals("q")) {
                    UserInterface.printSaveMessage(false);
                    break;
                } else if (input.equals("s")) {
                    SaveAndLoad.saveObject("saveFile.save", player);
                    break;   
                } 
            }
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

    private static int whoIsPlayer(ArrayList<GameCharacter> fighters){
        int playerNum = 0;
        //Figures out the players place in the attack order and saves it as an int
        for (int i = 0; i < fighters.size(); i++) {      
            if (fighters.get(i).isPlayer) {
                playerNum = i;
            } 
        }
        return playerNum;
    }

    private static boolean removeDead(ArrayList<GameCharacter> fighters) {   
        for (int i = 0; i < fighters.size(); i++){
            if (fighters.get(i).isDead()){
                fighters.remove(fighters.get(i));
            }
        }
        if (fighters.size() < 2) {
            return true;
        } 
        return false;
    }

    private static Attacks getAttackType(int action, GameCharacter actor) {
        Attacks attackType;
        if (action == 3) {
            attackType = actor.getWeapon().getAttack1();
        } else {
            attackType = actor.getWeapon().getAttack2();
        }
        return attackType;
    }

    //if adding and removing weapons is enabled, asks the player if they want to pick up the dead enemys weapon
    private static void addToInventory (ArrayList<GameCharacter> fighters, int enemyToAttack, Player playerP, Scanner scanAction) {
        if (addAndRemoveWheaponFromInventory && fighters.get(enemyToAttack).getWeapon().getName() != "their own body") {
            UserInterface.printPickUppEnimyWeaponPromt(fighters.get(enemyToAttack).getWeapon());
            int addOrNot = playerAction(scanAction, 2, 0);
            if (addOrNot == 1){
                fighters.get(enemyToAttack).getWeapon().setEquiped(false);
                playerP.addToInventory(fighters.get(enemyToAttack).getWeapon());
            }
            UserInterface.printAddOrNot(addOrNot);
        }
    }
}
