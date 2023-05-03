package src;

/*
 * CollisionsLifeLost: basic tick behavior for pieces with collisions where player looses life
 *
 */
public class CollisionLifeLost implements Movement {
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

