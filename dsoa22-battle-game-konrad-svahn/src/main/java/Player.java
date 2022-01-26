public class Player extends GameCharacter{

    public Player(String name, int helth, int initiative, int armour, int regeneration) {
        super(name, helth, initiative, armour, regeneration);
        this.isPlayer = true;
        setArmour(0);
        setInitiative(initiative);
        setAttackVariance(5);
    } 
}
