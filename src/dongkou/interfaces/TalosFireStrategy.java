/**
 * Interface for fire strategies
 */
package dongkou.interfaces;

import battleship.interfaces.Fleet;
import battleship.interfaces.Position;

public interface TalosFireStrategy {
    
    public Position getFireCoordinates(Fleet enemyShips);
    public void hitFeedback(boolean hit, Fleet enemyShips);
    public void endMatch(int round, int points, int enemyPoints);
    public void endRound(int round, int points, int enemyPoints);
}
