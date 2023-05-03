package src;
/*
 * NoCCollision: handles tick behavior for pieces that don't have collisions with the dino
 */
public class NoCollision implements Movement {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
        piece.xPos+=piece.xVel;
        piece.yPos+=piece.yVel;
    }
    
}