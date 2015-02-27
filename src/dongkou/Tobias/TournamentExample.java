package dongkou.Tobias;

import R8.R8_PlayerFactory;
import battleship.examples.SystematicShotPlayerFactory;
import battleship.interfaces.BattleshipsPlayer;
import battleship.implementations.Battleships;
import java.util.ArrayList;
import tournament.Tournament;
import tournament.game.GameFactory;
import tournament.player.PlayerFactory;

public class TournamentExample
{
    public static void main(String[] args)
    {
        //Create player list
        ArrayList<PlayerFactory<BattleshipsPlayer>> playerFactories = new ArrayList<>();
        for(int i = 0; i < 10; ++i)
        {
            playerFactories.add(new R8_PlayerFactory());
            playerFactories.add(new Talos_PlayerFactory());
        }
        //Create game factory
        GameFactory<BattleshipsPlayer> gameFactory = Battleships.getGameFactory();
        Tournament.run(gameFactory, playerFactories, 8); //Running with 8 threads... 
    }
}
