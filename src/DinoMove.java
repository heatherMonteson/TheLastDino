package src;
import java.util.Timer;
import java.util.TimerTask;

/*
 * DinoMove: handles tick behavior used by the dino
 */

public class DinoMove implements Movement {
    public void tick(GamePiece piece) {
        // update x and y based on velocity
        //this if is responsible for bringing dino back to ground after jumping
        //we use timer for jumping bc we dont want jump method to be controlled and consistent
        if(piece.getType()==Enums.GamePiece.Dino){
            Dino dino = (Dino)piece; //is this typecasting this correctly?
            if (dino.isJumping) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dino.yPos = 335;
                        dino.isJumping = false;
                    }
                }, 700); //this num is the ms delay
            }
            dino.xPos += dino.xVel;
            dino.yPos += dino.yVel;
        }

    }
    
}