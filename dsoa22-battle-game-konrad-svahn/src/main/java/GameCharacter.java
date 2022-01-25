import java.util.Random;

public abstract class GameCharacter {
    
    private String name; 
    private int helth;
    private int maxHelth;
    private int initiative;
    private int attackVariance;
    public boolean isPlayer;
    private Weapon weapon;
    private int turnsOnFireLeft;
   
    Random ran = new Random();
    
    public GameCharacter(String name, int maxHelth ,int initiative) {
        this.name = name;
        this.maxHelth = maxHelth;
        this.helth = maxHelth;
        this.weapon = new Weapon("sword", 25, Attacks.ATTACK, Attacks.CHARGE);;
    }

    public void attack(GameCharacter defender) {
        int damage = this.weapon.getDamage() + ran.nextInt(this.attackVariance*2) - this.attackVariance;
        if (damage < 0) {damage = 0;}
        defender.takeDamage(damage);
        if (defender.getHelth() < 0) {defender.setHelth(0);}
        UserInterface.printDamage(GameCharacter.this, defender, damage);

    }

    public void resetHelth() {
        setHelth(getMaxHelth());
    }

    private void takeDamage(int damage) {
        this.helth = this.helth - damage;
    }

    public boolean isDead() {
        if (this.helth <= 0) {
            return true;
        } else {
            return false;}
    }
    
    public boolean isPlayer() {
        return this.isPlayer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHelth() {
        return helth;
    }

    public void setHelth(int helth) {
        this.helth = helth;
    } 

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        if (this.isPlayer == true) {initiative = 5;}
        if (initiative < 1 || initiative > 9) {initiative = 1;}
        if (initiative == 5 && this.isPlayer == false) {initiative = 4;}
        this.initiative = initiative;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getAttackVariance() {
        return attackVariance;
    }

    public void setAttackVariance(int attackVariance) {
        this.attackVariance = attackVariance;
    }

    public int getMaxHelth() {
        return maxHelth;
    }

    public void setMaxHelth(int maxHelth) {
        this.maxHelth = maxHelth;
    }

    public int getTurnsOnFireLeft() {
        return turnsOnFireLeft;
    }

    public void setTurnsOnFireLeft(int turnsOnFireLeft) {
        this.turnsOnFireLeft = turnsOnFireLeft;
    }
}
