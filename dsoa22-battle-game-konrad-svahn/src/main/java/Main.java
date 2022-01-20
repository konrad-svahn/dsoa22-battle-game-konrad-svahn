import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            
            String name;

            
            
            System.out.println("Enter your name");

            name = scanner.nextLine();
            GameCharacter player = new Player(name,100,5);

            System.out.println("Game start");
            System.out.println("A black knight blocks the bridge before you");
                
            GameCharacter enemy1 = new Npc("the black knight", 500,4);
            GameCharacter enemy2 = new Npc("the black knight", 500,1);
            GameCharacter enemy3 = new Npc("the black knight", 500,8);

            ArrayList<GameCharacter> battleParticipants = new ArrayList<>();
            battleParticipants.add(enemy1);
            battleParticipants.add(player);
            battleParticipants.add(enemy2);
            battleParticipants.add(enemy3);
            sortCharacters(battleParticipants);
            UserInterface.printBattleStart(battleParticipants);
            
            while (true) {
                player.attack(enemy1);

                if (enemy1.getHelth() <= 0){
                    System.out.println(player.getName()+" has won the battle");
                    System.out.println("satisfied with your vicktory, you cross the bridge");
                    break;
                } 
                    
                enemy1.attack(player);

                if (player.getHelth() <= 0){
                    System.out.println(enemy1.getName()+" has won the battle");
                    System.out.println("you died");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }   
    }

    public static ArrayList<GameCharacter> sortCharacters(ArrayList<GameCharacter> charackters){
        for(int i = 0; i < charackters.size(); i++){
            System.out.println(charackters.get(0).getInitiative()+""+charackters.get(1).getInitiative()+""+charackters.get(2).getInitiative()+""+charackters.get(3).getInitiative());
            int bigest = 0;
            for(int j = 0 + i; j < charackters.size(); j++){
                if(j == 0 + i){bigest = j;}
                else if(charackters.get(j).getInitiative() > charackters.get(bigest).getInitiative()){bigest = j;}
            }
            GameCharacter temp = charackters.get(i);
            charackters.set(i,charackters.get(bigest)); 
            charackters.set(bigest,temp);
        }
        return charackters;
    }
}
