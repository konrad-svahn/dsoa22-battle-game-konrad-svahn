import java.util.Random;

public abstract class GameCharacter {
    
    private String name; 
    private int helth;
    private int maxHelth;
    private int initiative;
    private int attackVariance;
    private int turnsOnFireLeft;
    private int armour;
    private int regeneration;
    public boolean isPlayer;
    private Weapon weapon;
    Random ran = new Random();
    

    public GameCharacter(String name, int maxHelth ,int initiativeint, int armour, int regeneration) {

        this.name = name;
        this.maxHelth = maxHelth;
        this.helth = maxHelth;
        this.weapon = new Weapon("sword", 25, Attacks.ATTACK, Attacks.CHARGE);;
        this.armour = armour;
        this.regeneration = regeneration;
    }

    public void attack(GameCharacter defender, Attacks attackType) {
        System.out.println(attackType);

        if (attackType == Attacks.ATTACK) {

        } else if (attackType == Attacks.FLAME_ATTACK) {
            defender.turnsOnFireLeft = 2;
        } else if (attackType == Attacks.CHARGE) {

        }else if (attackType == Attacks.FLAME_CHARGE) {
            defender.turnsOnFireLeft = 1;
        }else if (attackType == Attacks.RAPID_STRIKES) {

        }else if (attackType == Attacks.RAPID_FLAME_STRIKES) {
            defender.turnsOnFireLeft = 2;
        }else if (attackType == Attacks.LEECH) {

        }else if (attackType == Attacks.REGENERATE) {

        }else if (attackType == Attacks.THROW_GUNPOWDER) {

        }else if (attackType == Attacks.DETONATE) {

        }
        
        UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this));
    }

    public void resetHelth() {
        setHelth(getMaxHelth());
    }

    private int takeDamage(int damage){
        if (damage < 0) {damage = 0;}
            this.helth = this.helth - damage;
            if (this.helth < 0) {this.setHelth(0);}
            return damage;
    }

    private int takeAttackDamage(GameCharacter attacker) {

        int damage = attacker.getWeapon().getDamage() + ran.nextInt(this.attackVariance*2) - this.attackVariance;
        damage -=armour;
        return takeDamage(damage);
    }

    public int TakeFireDamage() {
        if (turnsOnFireLeft > 0) {
            int damage = (10 + ran.nextInt(10) - 5) - armour;
            turnsOnFireLeft -= 1;
            return takeDamage(damage);
        }
        return 0;
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

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }
}
