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
            Weapon[] posibleEnemyWeapons = Npc.initialiseEnemyWeapons();
            ArrayList<GameCharacter> battleParticipants = new ArrayList<>();
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
            battleParticipants.add(player);
            System.out.println(player.getAchillesHeel());

            Npc enemy1 = new Npc("the black knight", 150, 4, 50, 35, Attacks.DO_NOTHING);
            Npc enemy2 = new Npc("rapier wielding bandit", 50, 8, 0, 35, Attacks.REGENERATE);
            Npc enemy3 = new Npc("torch wielding bandit", 50, 2, 0, 35, Attacks.FLAME_ATTACK);
            Npc enemy4 = new Npc("weakling", 1, 6, 0, 0, Attacks.CHARGE);
            Npc enemy5 = new Npc("pyromancer", 20, 1, 0, 35, Attacks.FLAME_CHARGE);

            enemy1.setWeapon(new Weapon("black mace", 50, 30, Attacks.ATTACK, Attacks.CHARGE));
            enemy2.setWeapon(new Weapon("rapier", 30, 0, Attacks.ATTACK, Attacks.RAPID_STRIKES));
            enemy3.setWeapon(new Weapon("torch", 10, 10, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES));
            enemy4.setWeapon(new Weapon("bomb", 0, 0, Attacks.DETONATE, Attacks.DETONATE));
            enemy5.setWeapon(new Weapon("Pyromancy kit", 20, 20, Attacks.FLAME_CHARGE, Attacks.THROW_GUNPOWDER));

            battleParticipants.add(enemy1);
            battleParticipants.add(enemy2);
            battleParticipants.add(enemy3);
            battleParticipants.add(enemy4);
            battleParticipants.add(enemy5);

            while (true) {
                CombatManager.runEncounter(scanner, battleParticipants);
                if (CombatManager.isGameOver()) {
                    System.out.println(Ansi.RED + "YOU HAVE DIED" + Ansi.RESET);
                    break;
                }
                System.out.println("Press"+Ansi.CYAN+" q "+Ansi.RESET+" to "+Ansi.CYAN+"quit" + Ansi.RESET + " or pres another buton to continue");
                if (CombatManager.endOfBattleChoise(player, scanner)) {break;}
            }
            System.out.println("The game has ended");

        } catch (Exception e) {
            System.out.println(e);
        }
    }  
}
