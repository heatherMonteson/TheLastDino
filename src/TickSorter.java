package src;

public class TickSorter {
    private TickStrategy strategy;
    
    public void setStrategy(TickStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void tick(GamePiece piece) {
        strategy.tick(piece);
    }
}