import java.io.Serializable;
import java.util.Random;

public abstract class GameCharacter implements Serializable{
    
    private String name; 
    private int helth;
    private int maxHelth;
    private int initiative;
    private int turnsOnFireLeft;
    private int chargeLevel;
    private int armour;
    private int regeneration;
    public boolean isPlayer;
    private Weapon weapon;
    private Weapon defaultWeapon;
    private Attacks achillesHeel;
    Random ran = new Random();
    

    public GameCharacter(String name, int maxHelth ,int initiativeint, int armour, int regeneration) {

        this.name = name;
        this.maxHelth = maxHelth;
        this.helth = maxHelth;
        this.defaultWeapon = new Weapon("their own body", 5, 10, Attacks.ATTACK, Attacks.DO_NOTHING);
        this.weapon = this.defaultWeapon;
        this.armour = armour;
        this.regeneration = regeneration;
    }

    public void attack(GameCharacter defender, Attacks attackType) {
        int tempDam;
        if (this.chargeLevel == 2) {
            this.chargeLevel = 0;
            defender.turnsOnFireLeft = 2;
            UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 3d), 0, true, Attacks.FLAME_CHARGE);

        } else if (this.chargeLevel == 1) {
            this.chargeLevel = 0;
            UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 3d), 0, false, Attacks.CHARGE);

        } else {
            if (attackType == Attacks.ATTACK) {
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 1d), 0, false, Attacks.ATTACK);

            } else if (attackType == Attacks.FLAME_ATTACK) {
                defender.turnsOnFireLeft = 3;
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 1d), 0, true, Attacks.FLAME_ATTACK);

            } else if (attackType == Attacks.CHARGE) {
                this.chargeLevel = 1;
                UserInterface.printCharge(this);

            } else if (attackType == Attacks.FLAME_CHARGE) {
                this.chargeLevel = 2;
                UserInterface.printCharge(this);

            } else if (attackType == Attacks.RAPID_STRIKES) {
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 0.5), 0, false, Attacks.RAPID_STRIKES);//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneds more work

            } else if (attackType == Attacks.RAPID_FLAME_STRIKES) {
                defender.turnsOnFireLeft = 2;
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 0.5), 0, true, Attacks.RAPID_FLAME_STRIKES);//nnnnnnnnnnnnnnnnnnnnnnnnneds more work

            } else if (attackType == Attacks.LEECH) {
                tempDam = defender.takeAttackDamage(this, 1d);
                UserInterface.printDamage(GameCharacter.this, defender, tempDam, heal(leechCalk(tempDam)), false, Attacks.LEECH);// needs flavour text
            } else if (attackType == Attacks.REGENERATE) {
                UserInterface.printRegen(heal(this.getRegeneration()), this);
                
            } else if (attackType == Attacks.WILD_ABANDON) {
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 1d), 0, false, Attacks.WILD_ABANDON);//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneds more work

            } else if (attackType == Attacks.DO_NOTHING) {
                UserInterface.printDoesNothing(this);

            } else if (attackType == Attacks.THROW_GUNPOWDER) {
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 1d), 0, false, Attacks.THROW_GUNPOWDER);//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneds more work
     
            } else if (attackType == Attacks.DETONATE) {
                UserInterface.printDamage(GameCharacter.this, defender, defender.takeAttackDamage(this, 1d), 0, false, Attacks.DETONATE);//nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneds more work
            }
        }
        
    }

    private int takeAttackDamage(GameCharacter attacker, Double modifier) {

        double damage;

        if (attacker.getWeapon().getAttackVariance() == 0) {
            damage = attacker.getWeapon().getDamage(); 
        } else {
            damage = attacker.getWeapon().getDamage() + ran.nextInt(attacker.getWeapon().getAttackVariance()*2) - attacker.getWeapon().getAttackVariance();
        }
        damage *= modifier ;
        int damageInt = (int) damage;
        damageInt -= armour;
        return takeDamage(damageInt);
    }

    public int TakeFireDamage() {
        if (turnsOnFireLeft > 0) {
            int damage = (10 + ran.nextInt(10) - 5);
            turnsOnFireLeft -= 1;
            return takeDamage(damage);
        }
        return 0;
    }

    private int takeDamage(int damage){
        if (damage < 0) {damage = 0;}
            this.helth = this.helth - damage;
            if (this.helth < 0) {this.setHelth(0);}
            return damage;
    }

    public int heal(int amount){
        if (amount < 0) { amount = 0;}
        this.helth += amount;
        if (this.helth > this.maxHelth) { this.helth = this.maxHelth;}
        return amount;
    }

    private int leechCalk (int damage) {
        double leech = this.regeneration/100d; 
        leech *= damage;
        int leechInt = (int) leech;
        return leechInt;
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

    public void resetHelth() {
        setHelth(getMaxHelth());
    }

    // only getters and setters dwell below this point
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
        this.weapon.setEquiped(false);
        this.weapon = weapon;
        this.weapon.setEquiped(true);
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

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    public Weapon getDefaultWeapon() {
        return defaultWeapon;
    }

    public Attacks getAchillesHeel() {
        return achillesHeel;
    }

    public void setAchillesHeel(Attacks achillesHeel) {
        this.achillesHeel = achillesHeel;
    }
}
