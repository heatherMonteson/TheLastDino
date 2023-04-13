package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//KeyEvent chart: https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html#VK_W

public class KeyInput extends KeyAdapter {

    public KeyInput(){

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(GamePiece piece : GamePieceHandler.gamePieces){
        //TODO: add the level graphics to move with the dino as it runs and

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                if(key==KeyEvent.VK_UP) //up==jump
                {
                    //Todo: events for dino jump
                }
                if(key==KeyEvent.VK_DOWN) //down==duck
                {
                    //Todo: events for Dino duck
                    piece.setXvel(0);
                }
            }

            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle)
            {
                //key events for dino
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(-4);
                }
                if(key==KeyEvent.VK_UP) //up==jump
                {
                    //Todo: events for dino jump
                }
                if(key==KeyEvent.VK_DOWN) //down==duck
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

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                if(key==KeyEvent.VK_UP) //up==jump
                {
                    //Todo: events for dino jump
                }
                if(key==KeyEvent.VK_DOWN) //down==duck
                {
                    //Todo: events for Dino duck
                    piece.setXvel(0);
                }
            }

            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle)
            {
                //key events for dino
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(0);
                }
                if(key==KeyEvent.VK_UP) //up==jump
                {
                    //Todo: events for dino jump
                }
                if(key==KeyEvent.VK_DOWN) //down==duck
                {
                    //Todo: events for Dino duck
                    piece.setXvel(0);
                }
            }
        }
    }
}
