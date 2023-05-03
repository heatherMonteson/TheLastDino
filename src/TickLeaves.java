package src;

import java.awt.*;

public class TickLeaves implements TickStrategy {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
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