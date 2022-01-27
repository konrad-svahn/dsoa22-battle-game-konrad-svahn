import java.util.ArrayList;

public class UserInterface {

    // The class UserInterface is a class that writes out events in the terminal

    // Unsure if this is a good place for this method 
    // this method retuns the descrption for the diferent atack types
    public static String attackDescription(Attacks attackType, GameCharacter player){
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
        
        } else if (attackType == Attacks.WILD_ABANDON) {
            description = 
            " (Wild abandon will deal "+Ansi.YELLOW+"3x"+Ansi.RESET+" your weapons attack power("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET+") which varies by ("+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET + "), to you and the target. If the attack would have killed you, you will survive with"+
            Ansi.YELLOW+" 1 "+Ansi.RESET+"health)";
        
        } else if (attackType == Attacks.THROW_GUNPOWDER) {
            description = " (Throw gunpowder deals"+Ansi.YELLOW+" 1 "+Ansi.RESET+"damage normaly but if the target is on fire it deals "+Ansi.YELLOW+"3x"+Ansi.RESET+" weapon damage("+
            Ansi.YELLOW + player.getWeapon().getDamage() + Ansi.RESET+") which varies by "+
            Ansi.YELLOW + player.getWeapon().getAttackVariance() + Ansi.RESET +")";
        
        } else if (attackType == Attacks.REGENERATE) {
            description = 
            " (Regenerate recovers "+Ansi.YELLOW + player.getRegeneration() + Ansi.RESET + " helth)";
        
        } else if (attackType == Attacks.DETONATE) {
            description = 
            " ( Detonate deals "+ Ansi.YELLOW + "500" + Ansi.RESET + " damage to all enemies but destroys the wheapon you perform it with)";
        }
        return description;
    }

    // the folowing methods print information related to the inventory
    public static void printInventory(GameCharacter player){
        Player playerP = (Player) player;
        String equiped; 
        int i = 1;
        for (Weapon wheapon : playerP.getInventory()) {
            if (wheapon.isEquiped()) {
                equiped = Ansi.RED + " EQUIPED" + Ansi.RESET;
            } else {
                equiped = "";
            }
            System.out.println(i+": "+wheapon.getName() + equiped);
            System.out.println("damage: "+wheapon.getDamage()+",   attack variance "+wheapon.getAttackVariance()+",   attack 1: "+wheapon.getAttack1()+",   attack 2: "+wheapon.getAttack2());
            System.out.println();
            i++;
        } 
    }

    public static void equipOrDelete() {
        System.out.println("press 1 if you want to equip weapons or 2 if you want to delete weapons from your inventory");
    }

    public static void printEquipMessage(Weapon weapon) {
        System.out.println("you equiped "+weapon.getName());
    }

    public static void printItemRemoval(Weapon weapon) {
        System.out.println("you threw away "+weapon.getName());
    }

    // the following methods print simple actions related to combat
    public static void printDamage (GameCharacter attacker, GameCharacter deffender, int damage) {
        System.out.println(
            attacker.getName()+" hits "+deffender.getName()+" with "+ attacker.getWeapon().getName() +" for "+damage+" damage. "+deffender.getName()+" now has "+deffender.getHelth()+" Health"
        ); 
    }

    public static void printFireDamage (GameCharacter character, int damage) {
        System.out.println(
            character.getName() + " is burning and takes " + damage +" damage "+character.getName()+" now has "+character.getHelth()+" Health"
        );
    }

    public static void printBlock(GameCharacter character) {
        System.out.println(character.getName() + " raises their sheild to block, reducing all inkomming damage by " + character.getArmour() +" for the rest of the turn");
    }

    public static void printRegen () {
        System.out.println("troll");
    }

    public static void printDeath (GameCharacter victim) {
        System.out.println(victim.getName() + " has died");
    }

    // the following methods print mesages that promt player input 
    public static void printActionPromt (GameCharacter player) {
        System.out.println("press 1 to run away");
        System.out.println("press 2 to block");
        System.out.println("press 3 to use " + player.getWeapon().getAttack1()+attackDescription(player.getWeapon().getAttack1(), player));
        System.out.println("press 4 to use " + player.getWeapon().getAttack2()+attackDescription(player.getWeapon().getAttack2(), player));
        System.out.println("press 5 to open your inventory");
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

    public static void printWarning (int max) {
        System.out.println("You must select a number between 1 and "+max+" to continue");
    }

    public static void printInventoryActionPromt(boolean deleteItems) {
        if (deleteItems == true) {
            System.out.println("write the numer of the wheapon you want to delete or press q to exit");
        } else {
            System.out.println("write the numer of the wheapon to equip or press q to exit the inventory");
        }
    }

    // the methods bellow print miscellaneous messages
    public static void printGameStart() {
        System.out.println("Two bandits block the bridge before you");
    }

    public static void printBattleStart (ArrayList<GameCharacter>  charackters) {
        System.out.println("A battle has begun");
        for (int i = 0; i < charackters.size(); i++){
            int j = i+1;
            System.out.println(
                charackters.get(i).getName()+" has "+charackters.get(i).getHelth()+" helth and is number "+j+" in the atack order"
            );
        }
    } 

}
