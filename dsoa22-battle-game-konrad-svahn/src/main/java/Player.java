public class Player extends GameCharacter{

    public Player(String name, int helth, int initiative) {
        super(name, helth, initiative);
        this.isPlayer = true;
        setInitiative(initiative);
        setAttackVariance(5);
    } 
}
