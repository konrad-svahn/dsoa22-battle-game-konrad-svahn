public class Npc extends GameCharacter{

    public Npc(String name, int helth, int initiative) {
        super(name, helth, initiative);
        this.isPlayer = false;
        setInitiative(initiative);
        setAttackVariance(10);
    } 
}
