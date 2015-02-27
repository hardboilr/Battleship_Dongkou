package dongkou.Tobias;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

public class Talos_PlayerFactory implements PlayerFactory<BattleshipsPlayer>
{
    private static int nextID = 1;
    private final int id;

    public Talos_PlayerFactory()
    {
        id = nextID++;
    }
    
    
    @Override
    public BattleshipsPlayer getNewInstance()
    {
        return new Talos_Player();
    }

    @Override
    public String getID()
    {
        return "R8+" + id;
    }

    @Override
    public String getName()
    {
        return "HAL R8000, version Talos " + id;
    }
    
}

