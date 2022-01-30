import java.util.ArrayList;
import java.util.Random;

public class Npc extends GameCharacter{

    public Npc(String name, int helth, int initiative ,int armour ,int regeneration, Attacks achillesHeel) {
        super(name, helth, initiative, armour, regeneration);
        this.isPlayer = false;
        this.setAchillesHeel(achillesHeel);
        this.setInstantDeathMesage(Ansi.PURPLE + this.getName()+ Ansi.RESET + " was instantly killed by your attack");
        setInitiative(initiative);
    } 

    static GameCharacter spawnEnemy () {
        return new Npc("the black knight", 150, 4, 50, 35, Attacks.DO_NOTHING);
    }
    
    static Npc spawNpc (ArrayList<GameCharacter> battleParticipants) {
        Random ran = new Random();
        Npc returned;
        Npc[] customNpc = initialiseCustomNpcs();
        Attacks achillesHeel = Attacks.DO_NOTHING;
        boolean canAddCustomNpc = true;
        int ranInt = ran.nextInt(20);

        for (int i = 0; i < battleParticipants.size() ; i++) {
            for (int j = 0; j < customNpc.length ; j++) {
                if (battleParticipants.get(i).getName() == customNpc[j].getName()) {
                    canAddCustomNpc = false;
                }
            }
        }
        if (canAddCustomNpc == true && ranInt == 5) {
            returned = initialiseCustomNpcs()[ran.nextInt(customNpc.length)];
        } else {
            if (ran.nextInt(2) == 0) {
                achillesHeel = Attacks.ATTACK;
            }
            returned = new Npc(randomizedEnemyNames()[ran.nextInt(randomizedEnemyNames().length)], ran.nextInt(111) + 40 , ran.nextInt(11), ran.nextInt(11), ran.nextInt(111), achillesHeel);
            returned.setWeapon(initialiseEnemyWeapons()[ran.nextInt(initialiseEnemyWeapons().length)]);
            returned.getWeapon().setDamage(ran.nextInt(41)+5);
            returned.getWeapon().setAttackVariance(ran.nextInt(31));
        }
        return returned;
    }

    private static String[] randomizedEnemyNames () {
        String[] names = {
            "skeleton",
            "bandit",
            "orc",
            "goblin",
            "troll",
            "trickster",
            "barbarian",
            "war mage",
            "ghoul",
            "doppelganger",
            "lizard-man",
            "madman",
            "zombie"
        };
        return names;
    }

    private static Npc[] initialiseCustomNpcs() {
        Npc[] customNpc = {
            new Npc("the black knight", 1000, 4, 50, 35, Attacks.DO_NOTHING),
            new Npc("the pyromancer", 250, 3, 10, 25, Attacks.DO_NOTHING),
            new Npc("the demon", 666, 6, 6, 66, Attacks.DO_NOTHING),
            new Npc("the giant leech", 200, 1, 0, 200, Attacks.DO_NOTHING),
            new Npc("the great red dragon", 10000, 1, 150, 5, Attacks.DO_NOTHING),
            new Npc("the masked stranger", 230, 9, 10, 15, Attacks.DO_NOTHING),
        };
        customNpc[0].setWeapon(new Weapon("war hammer", 50, 30, Attacks.CHARGE, Attacks.WILD_ABANDON));
        customNpc[1].setWeapon(new Weapon("Pyromancy kit", 20, 20, Attacks.FLAME_CHARGE, Attacks.THROW_GUNPOWDER));
        customNpc[2].setWeapon(new Weapon("magical burning sword", 30, 5, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES));
        customNpc[3].setWeapon(new Weapon("cursed leech skin", 20, 7, Attacks.LEECH, Attacks.DO_NOTHING));
        customNpc[4].setWeapon(new Weapon("the claw of an ancient dragon", 100, 0, Attacks.LEECH, Attacks.RAPID_FLAME_STRIKES));
        customNpc[5].setWeapon(new Weapon("rapier", 30, 0, Attacks.ATTACK, Attacks.RAPID_STRIKES));
        return customNpc;
    }

    private static Weapon[] initialiseEnemyWeapons () {
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
