import java.util.ArrayList;

public class Player extends GameCharacter {

    private ArrayList<Weapon> inventory = new ArrayList<>();

    public Player(String name, int helth, int initiative, int armour, int regeneration) {
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
}
