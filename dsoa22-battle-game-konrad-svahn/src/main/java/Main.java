import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            
            String name;
            System.out.println("Game start");
            System.out.println("Enter your name");
            name = scanner.nextLine();
            
            GameCharacter player = new Player(name,100,5);
            GameCharacter enemy1 = new Npc("the black knight", 50, 4);
            GameCharacter enemy2 = new Npc("rapier wielding bandit", 75, 8);
            GameCharacter enemy3 = new Npc("torch wielding bandit", 150, 2);
            enemy2.setWeapon(new Weapon("rapier", 30));
            enemy3.setWeapon(new Weapon("torch", 10));
    
            ArrayList<GameCharacter> battleParticipants = new ArrayList<>();
            battleParticipants.add(enemy1);
            battleParticipants.add(player);
            //battleParticipants.add(enemy2);
            //battleParticipants.add(enemy3);

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
