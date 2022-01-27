import java.util.ArrayList;

public class UserInterface {

    // The class UserInterface is a class that writes out events in the terminal
    public static void printBattleStart (ArrayList<GameCharacter>  charackters) {
        System.out.println("A battle has begun");
        for (int i = 0; i < charackters.size(); i++){
            int j = i+1;
            System.out.println(
                charackters.get(i).getName()+" has "+charackters.get(i).getHelth()+" helth and is number "+j+" in the atack order"
            );
        }
    } 

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

    public static void printWarning (int max) {
        System.out.println("You must select a number between 1 and "+max+" to continue");
    }

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

    public static void printGameStart() {
        System.out.println("Two bandits block the bridge before you");
    }

    public static void equipOrDelete() {
        System.out.println("press 1 if you want to equip weapons or 2 if you want to delete weapons from your inventory");
    }

    public static void printEquipMessage(Weapon weapon) {
        System.out.println("you equiped "+weapon.getName());
    }

    public static void printInventory(GameCharacter player){
        Player playerP = (Player) player;
        String equiped; 
        int i = 1;
        for (Weapon wheapon : playerP.getInventory()) {
            if (wheapon.isEquiped()) {
                equiped = " EQUIPED";
            } else {
                equiped = "";
            }
            System.out.println(i+": "+wheapon.getName()+equiped);
            System.out.println("damage: "+wheapon.getDamage()+",   attack variance "+wheapon.getAttackVariance()+",   attack 1: "+wheapon.getAttack1()+",   attack 2: "+wheapon.getAttack2());
            System.out.println();
            i++;
        } 
        System.out.println("write the numer of the wheapon to seleckt or press q to exit the inventory");
    }

    // Unsure if this is a good place for this method
    public static String attackDescription(Attacks attackType, GameCharacter player){
        String description = ""; 
        if (attackType == Attacks.ATTACK) {
            description = 
            " (The attack move deals the damage of your weapons attack power("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+")";
        } else if (attackType == Attacks.FLAME_ATTACK) {
            description = 
            "  (The flame attack move lights the target on fire and deals the damage of your weapons attack power("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+")";
        } else if (attackType == Attacks.CHARGE) {
            description = 
            " (Using carge will cause you to perform an attack dealing 3x your wheapon damage("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+" on your next turn)";
        } else if (attackType == Attacks.FLAME_CHARGE) {
            description = 
            " (Using flame carge will cause you to perform an attack dealing 3x your wheapon damage("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+" on your next turn and will set the target on fire)";
        } else if (attackType == Attacks.RAPID_STRIKES) {
            description = 
            " (Using rapid strikes will preform 3 attacks in a row each of which will deal half your weapons damage("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+")";
        } else if (attackType == Attacks.RAPID_FLAME_STRIKES) {
            description = 
            " (Using rapid flame strikes will preform 3 attacks in a row each of which will deal half your weapons damage("+
            player.getWeapon().getDamage()+") which varies by "+player.getWeapon().getAttackVariance()+" and will set the target on fire)";
        } else if (attackType == Attacks.LEECH) {
            description = 
            " (Leech deals the damage of your weapons attack power("+player.getWeapon().getDamage()+") which varies "+
            player.getWeapon().getAttackVariance()+" and regenerates "+player.getRegeneration()+"% of the damage delt)";
        } else if (attackType == Attacks.REGENERATE) {
            description = " (Regenerate recovers "+player.getRegeneration()+" helth)";
        } else if (attackType == Attacks.WILD_ABANDON) {
            description = " (Wild abandon will deal 3x your weapons attack power("+player.getWeapon().getDamage()+") which varies "+
            player.getWeapon().getAttackVariance()+", to you and the target. If the attack would have killed you you will survive with 1 health)";
        } else if (attackType == Attacks.THROW_GUNPOWDER) {
            description = " (Throw gunpowder deals 1 damage normaly but if the target is on fire it deals 3x weapon damage("+
            player.getWeapon().getDamage()+") which varies "+player.getWeapon().getAttackVariance()+")";
        } else if (attackType == Attacks.DETONATE) {
            description = " ( Detonate deals 500 damage to all enemies but destroys the wheapon you perform it with)";
        }
        return description;
    }
}
