package dongkou.strategy;

import battleship.interfaces.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlacementStrategy {

    private List<ArrayList> strategies;
    private Random rnd = new Random();

    public PlacementStrategy() {
        strategies = new ArrayList();
        ArrayList<ShipPlacement> strategy1 = new ArrayList();
        strategy1.add(new ShipPlacement(new Position(0, 4), true));
        strategy1.add(new ShipPlacement(new Position(2, 0), false));
        strategy1.add(new ShipPlacement(new Position(9, 0), true));
        strategy1.add(new ShipPlacement(new Position(9, 6), true));
        strategy1.add(new ShipPlacement(new Position(3, 9), false));
        strategies.add(strategy1);

        ArrayList<ShipPlacement> strategy2 = new ArrayList();
        strategy2.add(new ShipPlacement(new Position(8, 0), true));
        strategy2.add(new ShipPlacement(new Position(3, 5), true));
        strategy2.add(new ShipPlacement(new Position(1, 7), true));
        strategy2.add(new ShipPlacement(new Position(7, 4), true));
        strategy2.add(new ShipPlacement(new Position(5, 3), true));
        strategies.add(strategy2);

        ArrayList<ShipPlacement> strategy3 = new ArrayList();
        strategy3.add(new ShipPlacement(new Position(0, 0), true));
        strategy3.add(new ShipPlacement(new Position(2, 2), true));
        strategy3.add(new ShipPlacement(new Position(4, 3), true));
        strategy3.add(new ShipPlacement(new Position(6, 5), true));
        strategy3.add(new ShipPlacement(new Position(9, 5), true));
        strategies.add(strategy3);

        ArrayList<ShipPlacement> strategy4 = new ArrayList();
        strategy4.add(new ShipPlacement(new Position(5, 8), true));
        strategy4.add(new ShipPlacement(new Position(6, 7), true));
        strategy4.add(new ShipPlacement(new Position(7, 7), true));
        strategy4.add(new ShipPlacement(new Position(8, 6), true));
        strategy4.add(new ShipPlacement(new Position(9, 5), true));
        strategies.add(strategy4);

        ArrayList<ShipPlacement> strategy5 = new ArrayList();
        strategy5.add(new ShipPlacement(new Position(0, 2), true));
        strategy5.add(new ShipPlacement(new Position(0, 4), true));
        strategy5.add(new ShipPlacement(new Position(0, 7), true));
        strategy5.add(new ShipPlacement(new Position(1, 9), false));
        strategy5.add(new ShipPlacement(new Position(5, 9), false));
        strategies.add(strategy5);

        ArrayList<ShipPlacement> strategy6 = new ArrayList();
        strategy6.add(new ShipPlacement(new Position(2, 7), true));
        strategy6.add(new ShipPlacement(new Position(1, 0), true));
        strategy6.add(new ShipPlacement(new Position(1, 3), true));
        strategy6.add(new ShipPlacement(new Position(0, 6), false));
        strategy6.add(new ShipPlacement(new Position(0, 9), false));
        strategies.add(strategy6);

        ArrayList<ShipPlacement> strategy7 = new ArrayList();
        strategy7.add(new ShipPlacement(new Position(2, 2), true));
        strategy7.add(new ShipPlacement(new Position(2, 4), true));
        strategy7.add(new ShipPlacement(new Position(7, 0), true));
        strategy7.add(new ShipPlacement(new Position(7, 3), true));
        strategy7.add(new ShipPlacement(new Position(3, 9), false));
        strategies.add(strategy7);

        ArrayList<ShipPlacement> strategy8 = new ArrayList();
        strategy8.add(new ShipPlacement(new Position(0, 3), false));
        strategy8.add(new ShipPlacement(new Position(2, 2), true));
        strategy8.add(new ShipPlacement(new Position(9, 6), true));
        strategy8.add(new ShipPlacement(new Position(5, 7), false));
        strategy8.add(new ShipPlacement(new Position(3, 1), false));
        strategies.add(strategy8);

        ArrayList<ShipPlacement> strategy9 = new ArrayList();
        strategy9.add(new ShipPlacement(new Position(3, 1), false));
        strategy9.add(new ShipPlacement(new Position(5, 1), false));
        strategy9.add(new ShipPlacement(new Position(3, 2), true));
        strategy9.add(new ShipPlacement(new Position(7, 2), true));
        strategy9.add(new ShipPlacement(new Position(2, 5), false));
        strategies.add(strategy9);

        ArrayList<ShipPlacement> strategy10 = new ArrayList();
        strategy10.add(new ShipPlacement(new Position(8, 0), false));
        strategy10.add(new ShipPlacement(new Position(0, 7), false));
        strategy10.add(new ShipPlacement(new Position(0, 8), false));
        strategy10.add(new ShipPlacement(new Position(0, 9), false));
        strategy10.add(new ShipPlacement(new Position(5, 1), false));
        strategies.add(strategy10);

        ArrayList<ShipPlacement> strategy11 = new ArrayList();
        strategy11.add(new ShipPlacement(new Position(1, 0), true));
        strategy11.add(new ShipPlacement(new Position(3, 0), true));
        strategy11.add(new ShipPlacement(new Position(5, 0), true));
        strategy11.add(new ShipPlacement(new Position(7, 0), true));
        strategy11.add(new ShipPlacement(new Position(3, 4), false));
        strategies.add(strategy11);

        ArrayList<ShipPlacement> strategy12 = new ArrayList();
        strategy12.add(new ShipPlacement(new Position(8, 7), true));
        strategy12.add(new ShipPlacement(new Position(6, 6), true));
        strategy12.add(new ShipPlacement(new Position(4, 6), true));
        strategy12.add(new ShipPlacement(new Position(2, 5), true));
        strategy12.add(new ShipPlacement(new Position(4, 4), false));
        strategies.add(strategy12);

        ArrayList<ShipPlacement> strategy13 = new ArrayList();
        strategy13.add(new ShipPlacement(new Position(1, 1), true));
        strategy13.add(new ShipPlacement(new Position(5, 1), false));
        strategy13.add(new ShipPlacement(new Position(7, 5), false));
        strategy13.add(new ShipPlacement(new Position(3, 3), true));
        strategy13.add(new ShipPlacement(new Position(2, 8), false));
        strategies.add(strategy13);

        ArrayList<ShipPlacement> strategy14 = new ArrayList();
        strategy14.add(new ShipPlacement(new Position(1, 1), false));
        strategy14.add(new ShipPlacement(new Position(2, 3), false));
        strategy14.add(new ShipPlacement(new Position(0, 4), false));
        strategy14.add(new ShipPlacement(new Position(2, 6), false));
        strategy14.add(new ShipPlacement(new Position(4, 8), false));
        strategies.add(strategy14);

        ArrayList<ShipPlacement> strategy15 = new ArrayList();
        strategy15.add(new ShipPlacement(new Position(5, 2), true));
        strategy15.add(new ShipPlacement(new Position(0, 1), false));
        strategy15.add(new ShipPlacement(new Position(8, 0), true));
        strategy15.add(new ShipPlacement(new Position(8, 5), true));
        strategy15.add(new ShipPlacement(new Position(1, 7), false));
        strategies.add(strategy15);

        ArrayList<ShipPlacement> strategy16 = new ArrayList();
        strategy16.add(new ShipPlacement(new Position(8, 0), true));
        strategy16.add(new ShipPlacement(new Position(1, 3), true));
        strategy16.add(new ShipPlacement(new Position(6, 3), false));
        strategy16.add(new ShipPlacement(new Position(5, 8), false));
        strategy16.add(new ShipPlacement(new Position(0, 5), true));
        strategies.add(strategy16);

        ArrayList<ShipPlacement> strategy17 = new ArrayList();
        strategy17.add(new ShipPlacement(new Position(2, 0), false));
        strategy17.add(new ShipPlacement(new Position(4, 0), false));
        strategy17.add(new ShipPlacement(new Position(7, 0), false));
        strategy17.add(new ShipPlacement(new Position(9, 1), true));
        strategy17.add(new ShipPlacement(new Position(9, 5), true));
        strategies.add(strategy17);

        ArrayList<ShipPlacement> strategy18 = new ArrayList();
        strategy18.add(new ShipPlacement(new Position(9, 8), true));
        strategy18.add(new ShipPlacement(new Position(8, 7), true));
        strategy18.add(new ShipPlacement(new Position(0, 0), true));
        strategy18.add(new ShipPlacement(new Position(1, 0), true));
        strategy18.add(new ShipPlacement(new Position(3, 4), false));
        strategies.add(strategy18);

        ArrayList<ShipPlacement> strategy19 = new ArrayList();
        strategy19.add(new ShipPlacement(new Position(1, 8), false));
        strategy19.add(new ShipPlacement(new Position(3, 1), false));
        strategy19.add(new ShipPlacement(new Position(8, 6), true));
        strategy19.add(new ShipPlacement(new Position(0, 4), false));
        strategy19.add(new ShipPlacement(new Position(5, 3), true));
        strategies.add(strategy19);

        ArrayList<ShipPlacement> strategy20 = new ArrayList();
        strategy20.add(new ShipPlacement(new Position(1, 0), false));
        strategy20.add(new ShipPlacement(new Position(0, 0), true));
        strategy20.add(new ShipPlacement(new Position(1, 2), false));
        strategy20.add(new ShipPlacement(new Position(3, 1), false));
        strategy20.add(new ShipPlacement(new Position(7, 4), true));
        strategies.add(strategy20);

        ArrayList<ShipPlacement> strategy21 = new ArrayList();
        strategy21.add(new ShipPlacement(new Position(2, 9), false));
        strategy21.add(new ShipPlacement(new Position(0, 0), false));
        strategy21.add(new ShipPlacement(new Position(7, 0), false));
        strategy21.add(new ShipPlacement(new Position(3, 0), false));
        strategy21.add(new ShipPlacement(new Position(4, 9), false));
        strategies.add(strategy21);

        ArrayList<ShipPlacement> strategy22 = new ArrayList();
        strategy22.add(new ShipPlacement(new Position(6, 3), false));
        strategy22.add(new ShipPlacement(new Position(1, 3), false));
        strategy22.add(new ShipPlacement(new Position(1, 7), false));
        strategy22.add(new ShipPlacement(new Position(8, 5), true));
        strategy22.add(new ShipPlacement(new Position(5, 1), true));
        strategies.add(strategy22);

        ArrayList<ShipPlacement> strategy23 = new ArrayList();
        strategy23.add(new ShipPlacement(new Position(1, 6), false));
        strategy23.add(new ShipPlacement(new Position(1, 8), false));
        strategy23.add(new ShipPlacement(new Position(4, 8), false));
        strategy23.add(new ShipPlacement(new Position(2, 2), false));
        strategy23.add(new ShipPlacement(new Position(3, 6), true));
        strategies.add(strategy23);

        ArrayList<ShipPlacement> strategy24 = new ArrayList();
        strategy24.add(new ShipPlacement(new Position(9,0), true));
        strategy24.add(new ShipPlacement(new Position(8,1), true));
        strategy24.add(new ShipPlacement(new Position(7,3), true));
        strategy24.add(new ShipPlacement(new Position(6,4), true));
        strategy24.add(new ShipPlacement(new Position(5,5), true));
        strategies.add(strategy24);
        
        ArrayList<ShipPlacement> strategy25 = new ArrayList();
        strategy25.add(new ShipPlacement(new Position(1,1), true));
        strategy25.add(new ShipPlacement(new Position(1,5), true));
        strategy25.add(new ShipPlacement(new Position(6,1), false));
        strategy25.add(new ShipPlacement(new Position(5,8), false));
        strategy25.add(new ShipPlacement(new Position(4,3), true));
        strategies.add(strategy25);
    }

    public ArrayList getStrategy() {
        return strategies.get(rnd.nextInt(strategies.size()));
    }

}
