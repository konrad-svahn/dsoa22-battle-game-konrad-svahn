import java.util.ArrayList;
import java.util.Random;
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
            Random ran = new Random();
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
            
            System.out.println(player.getAchillesHeel());

            
            
            Npc enemy1 = new Npc("weakling", 5, 6, 0, 0, Attacks.CHARGE);
            enemy1.setWeapon(new Weapon("torch", 7, 3, Attacks.FLAME_ATTACK, Attacks.DO_NOTHING));
            battleParticipants.add(enemy1);
            
           
            while (true) {
                battleParticipants.add(player);
                for (int i = 0; i <= ran.nextInt(3); i++) {
                    battleParticipants.add(Npc.spawNpc(battleParticipants));
                } 
                CombatManager.runEncounter(scanner, battleParticipants);
                if (CombatManager.isGameOver()) {
                    System.out.println(Ansi.RED + "YOU HAVE DIED" + Ansi.RESET);
                    break;
                }
                System.out.println("Press"+Ansi.CYAN+" q "+Ansi.RESET+" to "+Ansi.CYAN+"quit" + Ansi.RESET + " or pres another buton to continue");
                if (CombatManager.endOfBattleChoise(player, scanner)) {break;}
                battleParticipants.clear();;
            }
            System.out.println("The game has ended");

        } catch (Exception e) {
            System.out.println(e);
        }
    }  
}
