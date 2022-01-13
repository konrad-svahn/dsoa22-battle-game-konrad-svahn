public class GameCharacter {
    
    private String name; 
    private int helth;
    private int attackPower;

    public GameCharacter(String name, int helth , int attackPower) {
        this.name = name;
        this.helth = helth;
        this.attackPower = attackPower;
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

    public void takeDamage(int damage){
        this.helth = this.helth - damage;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

}
