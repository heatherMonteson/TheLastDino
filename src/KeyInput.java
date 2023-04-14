package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;

//KeyEvent chart: https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html#VK_W

public class KeyInput extends KeyAdapter {
    private int runningVelocity = -4;
    private final double pxlPerM=10.0;
    private final double gravity = -9.8;

    public KeyInput(){

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(GamePiece piece : GamePieceHandler.gamePieces){
        //TODO: add the level graphics to move with the dino as it runs and ducks

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                Dino dino = (Dino) piece;
                dino.jump();
            }

            //pieces move to the left to make it look like the dino is running forward
            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle || piece.type==Enums.GamePiece.Cloud ||piece.type==Enums.GamePiece.SmokeCloud)
            {
                if(key==KeyEvent.VK_SPACE) //space==run, start moving
                {
                    piece.setXvel(runningVelocity);
                }
                if(key==KeyEvent.VK_DOWN) //down==duck, stop moving
                {
                    //Todo: events for Dino duck
                    piece.setXvel(0);

                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(GamePiece piece : GamePieceHandler.gamePieces){
            //TODO: add the level graphics to move with the dino as it runs and

            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle)
            {
                //key events for dino
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(0);
                }
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(0);
                }

            }
        }
    }
}
