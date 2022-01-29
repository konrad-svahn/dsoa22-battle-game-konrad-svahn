import java.util.ArrayList;

public class UserInterface {

    // The class UserInterface is a class that writes out events in the terminal

    // Unsure if this is a good place for this method 
    // this method retuns the descrption for the diferent atack types
    public static String attackDescription (Attacks attackType, GameCharacter player){
        String description = ""; 
        if (attackType == Attacks.ATTACK) {
            description = 
            " (The attack move deals the damage of your weapons attack power("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET + ") which varies by (" +
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET+"))";
        
        } else if (attackType == Attacks.FLAME_ATTACK) {
            description = 
            "  (The flame attack move "+Ansi.RED+"lights the target on fire"+Ansi.RESET+" and deals the damage of your weapons attack power("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET + ") which varies by (" + 
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET+"))";
        
        } else if (attackType == Attacks.CHARGE) {
            description = 
            " (Using carge will cause you to perform an attack dealing "+Ansi.YELLOW+"3x"+Ansi.RESET+" your wheapon damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET + ") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET + "), on your next turn)";
        
        } else if (attackType == Attacks.FLAME_CHARGE) {
            description = 
            " (Using flame carge will cause you to perform an attack dealing "+Ansi.YELLOW+"3x"+Ansi.RESET+" your weapon damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET + ") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET+"), on your next turn and will "+Ansi.RED+"set the target on fire"+Ansi.RESET+")";
        
        } else if (attackType == Attacks.RAPID_STRIKES) {
            description = 
            " (Using rapid strikes will preform "+Ansi.YELLOW+"3 attacks"+Ansi.RESET+" in a row each of which will deal "+Ansi.YELLOW+"half"+Ansi.RESET+" your weapons damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET +") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET +"))";
        
        } else if (attackType == Attacks.RAPID_FLAME_STRIKES) {
            description = 
            " (Using rapid flame strikes will preform "+Ansi.YELLOW+"3 attacks "+Ansi.RESET+"in a row each of which will deal "+Ansi.YELLOW+"half "+Ansi.RESET+"your weapons damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET +") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET +") and will "+Ansi.RED+"set the target on fire"+Ansi.RESET+")";
        
        } else if (attackType == Attacks.LEECH) {
            description = 
            " (Leech deals the damage of your weapons attack power("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET +") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET + ") and regenerates "+ Ansi.YELLOW + player.getRegeneration() + "%"+ Ansi.RESET +" of the damage delt)";
        
        }  else if (attackType == Attacks.REGENERATE) {
            description = 
            " (Regenerate recovers "+Ansi.YELLOW + player.getRegeneration() + Ansi.RESET + " helth)";
        } else if (attackType == Attacks.WILD_ABANDON) {
            description = 
            " (Wild abandon will deal "+Ansi.YELLOW+"3x"+Ansi.RESET+" your weapons attack power("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET+") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET + "), to you and the target. If the attack would have killed you, you will survive with"+
            Ansi.YELLOW+" 1 "+Ansi.RESET+"health)";
        
        } else if (attackType == Attacks.DO_NOTHING) {
            description = " (Using do nothing does nothing)";
        } else if (attackType == Attacks.THROW_GUNPOWDER) {
            description = 
            " (Throw gunpowder deals"+Ansi.YELLOW+" 1 "+Ansi.RESET+"damage normaly but if the target is on fire it deals "+Ansi.YELLOW+"3x"+Ansi.RESET+" weapon damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET+") which varies by "+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET +")";
        
        } else if (attackType == Attacks.DETONATE) {
            description = 
            " ( Detonate deals "+ Ansi.YELLOW + "500" + Ansi.RESET + " damage to all enemies but destroys the wheapon you perform it with)";
        }
        return description;
    }

    // the folowing methods print information related to the inventory
    public static void printInventory (GameCharacter player){
        Player playerP = (Player) player;
        String equiped; 
        int i = 1;
        for (Weapon wheapon : playerP.getInventory()) {
            if (wheapon.isEquiped()) {
                equiped = Ansi.YELLOW + " EQUIPED" + Ansi.RESET;
            } else {
                equiped = "";
            }
            System.out.println(i+": "+wheapon.getName() + equiped);
            System.out.println("damage: " + Ansi.YELLOW + wheapon.getDamage() + Ansi.RESET + ",   attack variance " + Ansi.YELLOW + wheapon.getAttackVariance() + 
            Ansi.RESET + ",   attack 1: " + Ansi.CYAN + wheapon.getAttack1() + Ansi.RESET + ",   attack 2: " + Ansi.CYAN + wheapon.getAttack2() + Ansi.RESET
            );
            System.out.println();
            i++;
        } 
    }

    public static void printAddOrNot (int trowAway) {
        if (trowAway == 1) {
            System.out.println("you "+Ansi.YELLOW+"added" + Ansi.RESET + " the wepon to your inventory");
            System.out.println();
        } else {
            System.out.println("you " + Ansi.RED + "discarded" + Ansi.RESET + " the weapon");
            System.out.println();
        }
    }

    public static void equipOrDelete () {
        System.out.println("press "+ Ansi.YELLOW +"1"+Ansi.RESET+" if you want to "+ Ansi.YELLOW +"equip"+Ansi.RESET+" weapons or "+
         Ansi.RED +"2"+Ansi.RESET+" if you want to "+ Ansi.RED +"delete"+Ansi.RESET+" weapons from your inventory"
         );
    }

    public static void printEquipMessage (Weapon weapon) {
        System.out.println("you equiped "+weapon.getName());
    }

    public static void printItemRemoval (Weapon weapon) {
        System.out.println("you threw away "+weapon.getName());
    }

    // the following methods print simple actions related to combat
    public static void printDamage (GameCharacter attacker, GameCharacter deffender, int damage) {
        System.out.println(
            Ansi.PURPLE + attacker.getName() + Ansi.RESET + " uses "+ attacker.getWeapon().getName() + " to attack " + Ansi.PURPLE + deffender.getName() + Ansi.RESET + 
            " dealing " + Ansi.YELLOW + damage + Ansi.RESET +" damage. " 
        ); 
        System.out.println(
            Ansi.PURPLE + deffender.getName() + Ansi.RESET + " now has " + Ansi.GREEN + deffender.getHelth() + Ansi.RESET + " Health remaining"
        );
        System.out.println();
    }

    public static void printFireDamage (GameCharacter character, int damage) {
        System.out.println(
            Ansi.PURPLE + character.getName() + Ansi.RESET + " is burning and takes " + Ansi.RED + damage + Ansi.RESET +" damage "
        );
        System.out.println(
            Ansi.PURPLE + character.getName() + Ansi.RESET + " now has " + Ansi.GREEN + character.getHelth() + Ansi.RESET + " Health  remaining"
        );
        System.out.println();
    }

    public static void printBlock (GameCharacter character) {
        System.out.println( 
        Ansi.PURPLE + character.getName() + Ansi.RESET + " raises their sheild to block, reducing all inkomming damage by " + 
        Ansi.BLUE + character.getArmour() + Ansi.RESET +" for the rest of the turn"
        );
        System.out.println();
    }

    public static void printRegen () {
        System.out.println("troll");
    }

    public static void printDeath (GameCharacter victim) {
        System.out.println(Ansi.PURPLE + victim.getName() + Ansi.RESET + " has died");
        System.out.println();
    }

    // the following methods print mesages that promt player input 
    public static void printActionPromt (GameCharacter player) {
        System.out.println("press 1 to run away");
        System.out.println("press 2 to block");
        System.out.println("press 3 to use " + Ansi.CYAN +player.getWeapon().getAttack1()+Ansi.RESET+attackDescription(player.getWeapon().getAttack1(), player));
        System.out.println("press 4 to use " + Ansi.CYAN +player.getWeapon().getAttack2()+Ansi.RESET+attackDescription(player.getWeapon().getAttack2(), player));
        System.out.println("press 5 to open your inventory");
    }

    public static void enterYourName () {
        // not being able to print from method is usualy a good idea but the fackt that i had to make this its own method is stupid
        System.out.println("Enter your name");
    }

    public static void printEnemySelecktionPromt (ArrayList<GameCharacter> fighters, int player) {

        System.out.println("what enemy do you attack?");
        for (int i = 0; i < fighters.size() - 1; i++){
            int j = i + 1;
            if (i < player) {
                System.out.println("press " + j + " to attack " + Ansi.PURPLE + fighters.get(i).getName() + Ansi.RESET);
            } else {System.out.println("press " + j + " to attack " + Ansi.PURPLE + fighters.get(i + 1).getName()+ Ansi.RESET);}
        }
    }

    public static void printWarning (int max) {
        System.out.println("You must select a number between 1 and "+max+" to continue");
    }

    public static void printInventoryActionPromt (boolean deleteItems) {
        if (deleteItems == true) {
            System.out.println("write the numer of the wheapon you want to "+ Ansi.RED +"delete "+ Ansi.RESET +"or press"+Ansi.CYAN+" q "+Ansi.RESET+"to exit");
        } else {
            System.out.println("write the numer of the wheapon to "+ Ansi.YELLOW +"equip "+ Ansi.RESET +" or press"+Ansi.CYAN+" q "+Ansi.RESET+"to exit the inventory");
        }
    }

    public static void printPickUppEnimyWeaponPromt (Weapon weapon) {
        System.out.println(
            "Do you want to add " + weapon.getName() + " (damage(" + Ansi.YELLOW + weapon.getDamage() + Ansi.RESET + "), variation("+
            Ansi.YELLOW + weapon.getAttackVariance() + Ansi.RESET + ")), " + Ansi.CYAN + weapon.getAttack1() + Ansi.RESET + ", "+
            Ansi.CYAN + weapon.getAttack2() + Ansi.RESET + " to your inventory?"
        );
        System.out.println(
            "press " + Ansi.YELLOW + "1 " + Ansi.RESET + "to " + Ansi.YELLOW + "add " + Ansi.RESET + "press "+ 
            Ansi.RED + "2 " + Ansi.RESET + "to " + Ansi.RED + "discard " + Ansi.RESET
        );
    }

    // methodes dedicated to printing mesages about saving and loading
    public static void printSaveChoise () {  
        System.out.println(
            "Press " + Ansi.YELLOW + "s" + Ansi.RESET + " to " + Ansi.YELLOW + "save" + Ansi.RESET + 
            " or "+Ansi.CYAN+"q" + Ansi.RESET + " to "+Ansi.CYAN+"quit" + Ansi.RESET + " without saveing"
        );
    }

    public static void printSaveChoisePromt () {
        System.out.println(
            "Do you want to " + Ansi.YELLOW + "save" + Ansi.RESET + 
            " your game? Doing so will "+ Ansi.RED +"write over your previous save" + Ansi.RESET + " if there is one."
            );
    }

    public static void printSaveMessage (Boolean hasSaved) {

        if (hasSaved) {
            System.out.println("your game was " + Ansi.YELLOW + "saved" + Ansi.RESET);
        } else {
            System.out.println("you exited the game");
        }
    }

    public static void printLoadPromt () {
        System.out.println("Do you want to start with a " + Ansi.BLUE + "new charackter" + Ansi.RESET + " or continue with your " + Ansi.YELLOW + "previous" + Ansi.RESET + " one?");
        System.out.println(
            "To Create a " + Ansi.BLUE + "new charakter" + Ansi.RESET + " pres " + Ansi.BLUE + "1" + Ansi.RESET +
            " to load the " + Ansi.YELLOW + "previous" + Ansi.RESET + " one pres " + Ansi.YELLOW + "2" + Ansi.RESET
        );
    }

    // the methods bellow print miscellaneous messages
    public static void printGameStart () {
        System.out.println("some foes block the bridge before you");
    }

    public static void printBattleStart (ArrayList<GameCharacter>  charackters) {
        System.out.println("A battle has begun");
        System.out.println();
        for (int i = 0; i < charackters.size(); i++){
            int j = i+1;
            System.out.println(
                Ansi.PURPLE + charackters.get(i).getName() + Ansi.RESET + " has " + Ansi.GREEN + charackters.get(i).getHelth() + Ansi.RESET + 
                " helth, " + Ansi.BLUE + charackters.get(i).getArmour() + Ansi.RESET + " armour, is number " + Ansi.PURPLE + j + Ansi.RESET +
                " in the atack order and uses " + charackters.get(i).getWeapon().getName()+
                " (damage(" + Ansi.YELLOW + charackters.get(i).getWeapon().getDamage() + Ansi.RESET + "), variance(" + 
                Ansi.YELLOW + charackters.get(i).getWeapon().getAttackVariance() + Ansi.RESET + ")) to attack"
            );
            System.out.println();
        }
    } 
}
