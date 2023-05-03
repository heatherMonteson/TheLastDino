package src;

public class TickClouds implements TickStrategy {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
        piece.xPos+=piece.xVel;
        piece.yPos+=piece.yVel;
    
    }
    
}