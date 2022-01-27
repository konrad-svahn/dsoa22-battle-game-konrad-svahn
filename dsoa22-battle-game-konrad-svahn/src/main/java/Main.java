import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        if (System.getProperty("os.name").startsWith("Windows")) {System.out.println("windows");}
        
        try (Scanner scanner = new Scanner(System.in)) {
            
            String name;
            System.out.println("Game start");
            System.out.println("Enter your name");
            name = scanner.nextLine();
            
            Player player = new Player(name, 1000, 5, 0, 35);
            Npc enemy1 = new Npc("the black knight", 150, 4, 50, 35);
            Npc enemy2 = new Npc("rapier wielding bandit", 50, 8, 0, 35);
            Npc enemy3 = new Npc("torch wielding bandit", 50, 2, 0, 35);
            Npc enemy4 = new Npc("weakling", 1, 6, 0, 0);
            //GameCharacter enemy5 = new Npc("pyromancer", 1, 1, 0, 0);
            //player.setWeapon(new Weapon("wand", 25, 5, Attacks.FLAME_ATTACK, Attacks.REGENERATE));
            player.addToInventory(new Weapon("wand", 25, 5, Attacks.WILD_ABANDON, Attacks.REGENERATE));
            player.addToInventory(new Weapon("rand1", 25, 5, Attacks.CHARGE, Attacks.FLAME_CHARGE));
            player.addToInventory(new Weapon("rand2", 25, 5, Attacks.ATTACK, Attacks.FLAME_ATTACK));
            player.addToInventory(new Weapon("rand3", 25, 5, Attacks.RAPID_STRIKES, Attacks.RAPID_FLAME_STRIKES));
            player.addToInventory(new Weapon("rand4", 25, 5, Attacks.LEECH, Attacks.REGENERATE));
            player.addToInventory(new Weapon("rand5", 25, 5, Attacks.THROW_GUNPOWDER, Attacks.DETONATE));

            enemy1.setWeapon(new Weapon("black mace", 50, 30, Attacks.ATTACK, Attacks.CHARGE));
            enemy2.setWeapon(new Weapon("rapier", 30, 0, Attacks.ATTACK, Attacks.RAPID_STRIKES));
            enemy3.setWeapon(new Weapon("torch", 10, 10, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES));
    
            ArrayList<GameCharacter> battleParticipants = new ArrayList<>();
            battleParticipants.add(player);
            //battleParticipants.add(enemy1);
            battleParticipants.add(enemy2);
            battleParticipants.add(enemy3);
            battleParticipants.add(enemy4);
            //battleParticipants.add(enemy5);

            while (true) {
                CombatManager.runEncounter(scanner, battleParticipants);
                if (CombatManager.isGameOver()) {break;}
                System.out.println("Press q to quit pres another buton to continue");
                if (CombatManager.endOfBattleChoise(scanner)) {break;}
            }
            System.out.println("The game has ended");

        } catch (Exception e) {
            System.out.println(e);
        }
    }  
}
