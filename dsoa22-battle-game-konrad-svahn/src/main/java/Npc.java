public class Npc extends GameCharacter{

    public Npc(String name, int helth, int initiative ,int armour ,int regeneration, Attacks achillesHeel) {
        super(name, helth, initiative, armour, regeneration);
        this.isPlayer = false;
        this.setAchillesHeel(achillesHeel);
        setInitiative(initiative);
    } 

    static GameCharacter spawnEnemy () {
        return new Npc("the black knight", 150, 4, 50, 35, Attacks.DO_NOTHING);
    }

    public static Weapon[] initialiseEnemyWeapons () {
        Weapon[] posibleEnemyWeapons = {
            new Weapon("sword ", 0, 0, Attacks.ATTACK, Attacks.DO_NOTHING),
            new Weapon("axe", 0, 0, Attacks.CHARGE, Attacks.DO_NOTHING),
            new Weapon("dagger", 0, 0, Attacks.RAPID_STRIKES, Attacks.DO_NOTHING),
            new Weapon("flail", 0, 0, Attacks.WILD_ABANDON, Attacks.DO_NOTHING),
            new Weapon("rapier", 0, 0, Attacks.ATTACK, Attacks.RAPID_STRIKES),
            new Weapon("mace", 0, 0, Attacks.ATTACK, Attacks.CHARGE),
            new Weapon("club", 0, 0, Attacks.ATTACK, Attacks.WILD_ABANDON),
            new Weapon("great sword", 0, 0, Attacks.RAPID_STRIKES, Attacks.WILD_ABANDON),
            new Weapon("war hammer", 0, 0, Attacks.CHARGE, Attacks.WILD_ABANDON),
            new Weapon("spear", 0, 0, Attacks.RAPID_STRIKES, Attacks.CHARGE),
            new Weapon("tricksters tools", 0, 0, Attacks.RAPID_STRIKES, Attacks.THROW_GUNPOWDER),
            new Weapon("torch", 0, 0, Attacks.FLAME_ATTACK, Attacks.DO_NOTHING),
            new Weapon("fire grenades", 0, 0, Attacks.FLAME_CHARGE, Attacks.DO_NOTHING),
            new Weapon("magical burning sword", 0, 0, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES),
            new Weapon("the claw of an ancient dragon", 0, 0, Attacks.LEECH, Attacks.RAPID_FLAME_STRIKES),
            new Weapon("cursed leech skin", 0, 0, Attacks.LEECH, Attacks.DO_NOTHING),
            new Weapon("wand of absorbtion", 0, 0, Attacks.LEECH, Attacks.REGENERATE),
            new Weapon("healing wand", 0, 0, Attacks.REGENERATE, Attacks.FLAME_ATTACK),
            new Weapon("alchemy kit", 0, 0, Attacks.REGENERATE, Attacks.THROW_GUNPOWDER),
            new Weapon("pyromancy kit", 0, 0, Attacks.FLAME_CHARGE, Attacks.THROW_GUNPOWDER),
            new Weapon("gunpowder barrel", 0, 0, Attacks.THROW_GUNPOWDER, Attacks.DETONATE),  
        };
        return posibleEnemyWeapons;
    }
}
