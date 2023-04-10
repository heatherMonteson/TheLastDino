package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    public KeyInput(){
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(GamePiece piece : GamePieceHandler.gamePieces){

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                //TODO: add the level graphics to move with the dino as it runs
                //key events for dino
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(5);
                }
            }
            if(key==KeyEvent.VK_UP) //up==jump
            {
                //Todo: events for dino jump
            }
            if(key==KeyEvent.VK_DOWN) //down==duck
            {
                //Todo: events for Dino duck
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(GamePiece piece : GamePieceHandler.gamePieces){

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                //TODO: add the level graphics to move with the dino as it runs
                //key events for dino
                if(key==KeyEvent.VK_SPACE)
                {
                    piece.setXvel(0);
                }
            }
            if(key==KeyEvent.VK_UP) //up==jump
            {
                //Todo: events for dino jump
            }
            if(key==KeyEvent.VK_DOWN) //down==duck
            {
                //Todo: events for Dino duck
            }
        }
    }
}
