package dongkou.Tobias;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

public class Talos {

    public static PlayerFactory<BattleshipsPlayer> getPlayerFactory() {
        return new Talos_PlayerFactory();
    }
}
