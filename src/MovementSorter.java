package src;

/*
 *MovementSorter: handles the tick behavior
 */

public class MovementSorter {
    private Movement strategy;
    
    public void setStrategy(Movement strategy) {
        this.strategy = strategy;
    }
    
    public void tick(GamePiece piece) {
        strategy.tick(piece);
    }
}