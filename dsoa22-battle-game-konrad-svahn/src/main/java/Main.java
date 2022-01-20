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

            GameCharacter[] battle1 = {enemy1,player};
            sortCharacters(battle1);
            UserInterface.printBattleStart(battle1);
            
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

    public static GameCharacter[] sortCharacters(GameCharacter[] charackters){
        for(int i = 0; i < charackters.length; i++){
            int bigest = 0;
            for(int j = 0 + i; j < charackters.length; j++){
                if(j == 0 + i){bigest = j;}
                else if(charackters[j].getInitiative() > charackters[j-1].getInitiative()){bigest = j;}
            }
            //System.out.println(charackters[0].getInitiative()+""+charackters[1].getInitiative()+""+charackters[2].getInitiative()+""+charackters[3].getInitiative());
            GameCharacter temp = charackters[i];
            charackters[i] = charackters[bigest];
            charackters[bigest] = temp;
        }
        return charackters;
    }
}
