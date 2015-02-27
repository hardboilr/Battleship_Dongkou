package dongkou.Tobias;

import R8.R8_Player;
import battleship.examples.SystematicShotPlayer;
import battleship.interfaces.BattleshipsPlayer;
import battleship.implementations.Battleships;
import tournament.game.GameInstance;
import tournament.game.GameResult;

public class SingleMatchExample
{
    public static void main(String[] args)
    {
        BattleshipsPlayer player1 = new Talos_Player();
        BattleshipsPlayer player2 = new R8_Player();
//        BattleshipsPlayer player2 = new SystematicShotPlayer();
        GameInstance<BattleshipsPlayer> game = Battleships.getSingleGameInstance();
        GameResult res = game.run(player1, player2);
        System.out.println("Result: ");
        System.out.println("HAL R8000, version Talos  (Rounds won): " + res.minorPointsA);
        System.out.println("Computer  (Rounds won): " + res.minorPointsB);
        
    }
    
    
}
