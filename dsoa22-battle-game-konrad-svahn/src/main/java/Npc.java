public class Npc extends GameCharacter{

    public Npc(String name, int helth, int initiative ,int armour ,int regeneration) {
        super(name, helth, initiative, armour, regeneration);
        this.isPlayer = false;
        setInitiative(initiative);
    } 
}
