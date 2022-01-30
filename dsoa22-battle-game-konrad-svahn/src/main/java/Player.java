import java.util.ArrayList;
import java.util.Scanner;

public class Player extends GameCharacter {

    private ArrayList<Weapon> inventory = new ArrayList<>();

    public Player(String name, int helth, int initiative, int armour, int regeneration, Attacks achillesHeel) {
        super(name, helth, initiative, armour, regeneration);
        this.isPlayer = true;
        setArmour(0);
        setInitiative(initiative);
    } 

    
    public ArrayList<Weapon> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Weapon> inventory) {
        this.inventory = inventory;
    } 

    public Weapon getFromInventory(int wheaponToGet) {
        return inventory.get(wheaponToGet);
    }

    public void addToInventory(Weapon toAdd) {
        inventory.add(toAdd);
    }

    public void removeFromInventory(int wheaponToRemove) {
        if (getFromInventory(wheaponToRemove).isEquiped()) {
            this.setWeapon(this.getDefaultWeapon());
        }
        UserInterface.printItemRemoval(getFromInventory(wheaponToRemove));
        inventory.remove(wheaponToRemove);
    }

    public int getInventorySize(){
        return inventory.size();
    }

    public static Player create(Scanner scanner) {
        UserInterface.enterYourName();
        String name = scanner.nextLine();

        // will be changed to: return new Player(name, 1000, 5, 0, 35, Attacks.DO_NOTHING);
        Player player = new Player(name, 1000, 5, 0, 35, Attacks.DO_NOTHING);

        player.addToInventory(new Weapon("testWeapon1", 3, 0, Attacks.ATTACK, Attacks.FLAME_ATTACK));
        player.addToInventory(new Weapon("testWeapon2", 3, 0, Attacks.CHARGE, Attacks.FLAME_CHARGE));
        player.addToInventory(new Weapon("testWeapon3", 3, 0, Attacks.RAPID_STRIKES, Attacks.RAPID_FLAME_STRIKES));
        player.addToInventory(new Weapon("testWeapon4", 3, 0, Attacks.LEECH, Attacks.REGENERATE));
        player.addToInventory(new Weapon("testWeapon5", 3, 0, Attacks.WILD_ABANDON, Attacks.DO_NOTHING));
        player.addToInventory(new Weapon("testWeapon6", 3, 0, Attacks.THROW_GUNPOWDER, Attacks.DETONATE));

        return player;
    }
}
