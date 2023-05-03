package src;

import java.awt.*;

/*
 * CollisionsPoints: handles tick behavior for pieces with points attached
 */
public class CollisionPoints implements Movement {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
        if(piece.getType()==Enums.GamePiece.Leaf){
            Leaf leaves = (Leaf)piece;
            leaves.xPos += leaves.xVel;
            leaves.yPos += leaves.yVel;
            if(!leaves.collision1 && (leaves.getBounds().intersects(Dino.getDino().getBounds()))){
                leaves.collision1=true;
                Broker.getBroker().event(Enums.Event.AteLeaves);
                leaves.leaf = Toolkit.getDefaultToolkit().getImage("");
            }
        }
    }
    
}