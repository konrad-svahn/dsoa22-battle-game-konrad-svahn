import java.util.Random;

public abstract class GameCharacter {
    
    private String name; 
    private int helth;
    private int initiative;
    private int attackVariance;
    public boolean isPlayer;
    private Weapon weapon;
    Random ran = new Random();
    
    public GameCharacter(String name, int helth ,int initiative) {
        this.name = name;
        this.helth = helth;
        this.weapon = new Weapon("sword", 25);;
    }

    public void attack(GameCharacter defender){
        int damage = this.weapon.damage + ran.nextInt(this.attackVariance*2) - this.attackVariance;
        if(damage < 0){damage = 0;}
        defender.takeDamage(damage);
        if(defender.getHelth() < 0){defender.setHelth(0);}
        UserInterface.printDamage(GameCharacter.this,defender,damage);

    }

    private void takeDamage(int damage){
        this.helth = this.helth - damage;
    }

    public boolean isDead(){
        if (this.helth <= 0){
        return true;

        }else{
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
        if (this.isPlayer == true){initiative = 5;}
        if (initiative < 1 || initiative > 9){initiative = 1;}
        if (initiative == 5 && this.isPlayer == false){initiative = 4;}
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

}
