package dongkou.strategy;

import battleship.interfaces.Position;

public class ShipPlacement {
    
    private Position position;
    private boolean vertical;
    
    public ShipPlacement(Position input1, boolean input2) {
        this.position = input1;
        this.vertical = input2;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isVertical() {
        return vertical;
    }
    
    
    
    
}
