package src;

public class TickGamePieces implements TickStrategy {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
        piece.xPos += piece.xVel;
        piece.yPos += piece.yVel;
        if(!piece.collision1 && (piece.getBounds().intersects(Dino.getDino().getBounds()))){
            piece.collision1=true;
            Broker.getBroker().event(Enums.Event.LostLife);
        }
    
    }
    
}

