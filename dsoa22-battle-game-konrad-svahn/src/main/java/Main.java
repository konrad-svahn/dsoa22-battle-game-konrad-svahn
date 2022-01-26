import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            
            String name;
            System.out.println("Game start");
            System.out.println("Enter your name");
            name = scanner.nextLine();
            
            GameCharacter player = new Player(name, 100, 5, 0, 30);
            GameCharacter enemy1 = new Npc("the black knight", 150, 4, 50, 30);
            GameCharacter enemy2 = new Npc("rapier wielding bandit", 50, 8, 0,30);
            GameCharacter enemy3 = new Npc("torch wielding bandit", 50, 2, 0, 30);
            GameCharacter enemy4 = new Npc("weakling", 1, 1, 0, 0);
            enemy1.setWeapon(new Weapon("black mace", 50, Attacks.ATTACK, Attacks.CHARGE));
            enemy2.setWeapon(new Weapon("rapier", 30, Attacks.ATTACK, Attacks.RAPID_STRIKES));
            enemy3.setWeapon(new Weapon("torch", 10, Attacks.FLAME_ATTACK, Attacks.RAPID_FLAME_STRIKES));
    
            ArrayList<GameCharacter> battleParticipants = new ArrayList<>();
            battleParticipants.add(player);
            //battleParticipants.add(enemy1);
            //battleParticipants.add(enemy2);
            battleParticipants.add(enemy3);
            battleParticipants.add(enemy4);

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
