import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        // this is here for when Im going to try to make the ansi codes work in windows
        // which will probably be the last work i ever do on this game because it seems kind of hard
        if (System.getProperty("os.name").startsWith("Windows")) {
            System.out.println("Windows");
        }
        
        try (Scanner scanner = new Scanner(System.in)) {
            
            
            int inputP;
            Player player;
            System.out.println("Game start");

            UserInterface.printLoadPromt();
            inputP = CombatManager.playerAction(scanner, 2, 0);

            if (inputP == 2) {
                if((player = (Player) SaveAndLoad.loadObject("saveFile.save")) == null) {
                    player = Player.create(scanner);
                }
            } else {
                player = Player.create(scanner);
            }

            Npc enemy1 = new Npc("the black knight", 150, 4, 50, 35);
            Npc enemy2 = new Npc("rapier wielding bandit", 50, 8, 0, 35);
            Npc enemy3 = new Npc("torch wielding bandit", 50, 2, 0, 35);
            Npc enemy4 = new Npc("weakling", 1, 6, 0, 0);
            Npc enemy5 = new Npc("pyromancer", 20, 1, 0, 35);
            /*
            player.addToInventory(new Weapon("burning sword", 25, 5, Attacks.WILD_ABANDON, Attacks.FLAME_ATTACK));
            player.addToInventory(new Weapon("testWeapon1", 3, 0, Attacks.ATTACK, Attacks.FLAME_ATTACK));
            player.addToInventory(new Weapon("testWeapon2", 3, 0, Attacks.CHARGE, Attacks.FLAME_CHARGE));
            player.addToInventory(new Weapon("testWeapon3", 3, 0, Attacks.RAPID_STRIKES, Attacks.RAPID_FLAME_STRIKES));
            player.addToInventory(new Weapon("testWeapon4", 3, 0, Attacks.LEECH, Attacks.REGENERATE));
            player.addToInventory(new Weapon("testWeapon5", 3, 0, Attacks.WILD_ABANDON, Attacks.DO_NOTHING));
            player.addToInventory(new Weapon("testWeapon6", 3, 0, Attacks.THROW_GUNPOWDER, Attacks.DETONATE));*/

            enemy1.setWeapon(new Weapon("black mace", 50, 30, Attacks.ATTACK, Attacks.CHARGE));
            enemy2.setWeapon(new Weapon("rapier", 30, 0, Attacks.ATTACK, Attacks.RAPID_STRIKES));
            enemy3.setWeapon(new Weapon("torch", 10, 10, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES));
            enemy5.setWeapon(new Weapon("Pyromancy kit", 20, 20, Attacks.FLAME_CHARGE, Attacks.THROW_GUNPOWDER));

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
                System.out.println("Press"+Ansi.CYAN+" q "+Ansi.RESET+" to "+Ansi.CYAN+"quit" + Ansi.RESET + " or pres another buton to continue");
                if (CombatManager.endOfBattleChoise(player, scanner)) {break;}
            }
            System.out.println("The game has ended");

        } catch (Exception e) {
            System.out.println(e);
        }
    }  
}
