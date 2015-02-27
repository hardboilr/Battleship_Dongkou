package dongkou.Tobias;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import dongkou.interfaces.TalosFireStrategy;
import battleship.interfaces.Ship;
import dongkou.strategy.ProbDensity;
import dongkou.strategy.PlacementStrategy;
import dongkou.strategy.ShipPlacement;
import java.util.ArrayList;
import java.util.Random;

public class Talos_Player implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private TalosFireStrategy strategy;
    private PlacementStrategy placementstrategy;
    private ArrayList<ShipPlacement> placementPositions;

    int shotsFired = 0;

    private ArrayList<Position> positionsWithShips;

    public Talos_Player() {
        positionsWithShips = new ArrayList();
        placementstrategy = new PlacementStrategy();
    }

    @Override
    public void placeShips(Fleet fleet, Board board) {
        int nextX = 0;
        int nextY = 0;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        strategy = new ProbDensity(sizeX, sizeY);
        placementPositions = placementstrategy.getStrategy();
        for (int i = 0; i < fleet.getNumberOfShips(); i++) {
            Ship s = fleet.getShip(i);
            board.placeShip(placementPositions.get(i).getPosition(), s, placementPositions.get(i).isVertical());
        }
    }

    @Override
    public void incoming(Position pos) {
        //Do nothing
    }

    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        return strategy.getFireCoordinates(enemyShips);
    }

    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        strategy.hitFeedback(hit, enemyShips);
    }

    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    @Override
    public void startRound(int round) {
    }

    @Override
    public void endRound(int round, int points, int enemyPoints) {
        strategy.endRound(round, points, enemyPoints);
    }

    @Override
    public void endMatch(int won, int lost, int draw) {
        strategy.endMatch(won, lost, draw);
    }

    public int getSizeX() {
        return sizeX;
    }
}
