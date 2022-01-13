public class GameCharacter {
    
    private String name; 
    private int helth;
    private int attackPower;
    private int initiative;
    private boolean isPlayer;

    public GameCharacter(String name, int helth , int attackPower ,int initiative ,boolean isPlayer) {
        this.name = name;
        this.helth = helth;
        this.attackPower = attackPower;

        if (isPlayer == true){initiative = 5;}
        if (initiative < 1 || initiative > 9){initiative = 1;}
        if (initiative == 5 && isPlayer == false){initiative = 4;}
        this.initiative = initiative;
        this.isPlayer = isPlayer;
    }

    public void takeDamage(int damage){
        this.helth = this.helth - damage;
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

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
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

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

}
