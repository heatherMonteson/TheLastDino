package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;


/*
 *KeyInput: handles and processes all user key input sent as they play the game
 *
 * Citation:
 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html#VK_W
 * used for documentation
 *
 * Framework: KeyAdapter
 *
 */
public class KeyInput extends KeyAdapter {
    private int runningVelocity = -5;
    private boolean spacePressed;

    public KeyInput(){
        spacePressed=false;
    }

    /*
     * keyPressed: required implementation of abstract method from KeyAdapter, determines if a key is pressed through set event listeners
     *
     * @param KeyEvent
     * @return nothing
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_SPACE)
            spacePressed=true;

        for(GamePiece piece : GamePieceHandler.gamePieces){

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                Dino dino = (Dino) piece;
                if(key==KeyEvent.VK_UP){
                    //dino.jumpAndReset();
                    dino.jump();
                    //dino.resetDinoPosition();
                }
                else if(key==KeyEvent.VK_DOWN)
                    dino.duck();
                else if(key==KeyEvent.VK_SPACE)
                    dino.running();
            }

            //pieces move to the left to make it look like the dino is running forward
            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle || piece.type==Enums.GamePiece.Cloud ||piece.type==Enums.GamePiece.SmokeCloud)
            {
                if(key==KeyEvent.VK_SPACE) //space==run, start moving
                {
                    piece.setXvel(runningVelocity);
                }
                else if(key==KeyEvent.VK_DOWN) //down==duck, stop moving
                {
                    piece.setXvel(0);
                }
            }
            if(piece.type==Enums.GamePiece.Leaf ||piece.type==Enums.GamePiece.Snowball ||piece.type==Enums.GamePiece.Fireball){

                if(key==KeyEvent.VK_SPACE) //space==run, start moving
                {
                    piece.setXvel(-8);
                }
            }
        }
    }

    /*
     * keyReleased: required implementation of abstract method from KeyAdapter, determines if a key is released through set event listeners
     *
     * @param KeyEvent
     * @return nothing
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_SPACE)
            spacePressed=false;

        for(GamePiece piece : GamePieceHandler.gamePieces){

            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                Dino dino = (Dino) piece;
                if(key==KeyEvent.VK_DOWN)
                    dino.stand();
                else if(key== KeyEvent.VK_SPACE)
                    dino.stopping();
            }

            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle || piece.type==Enums.GamePiece.Cloud ||piece.type==Enums.GamePiece.SmokeCloud)
            {
                if(key==KeyEvent.VK_SPACE){
                    piece.setXvel(0);
                }
                if(key==KeyEvent.VK_DOWN && spacePressed){
                    piece.setXvel(runningVelocity);
                }
                else if(key==KeyEvent.VK_DOWN){
                    piece.setXvel(0);
                }
            }
            if(piece.type==Enums.GamePiece.Leaf ||piece.type==Enums.GamePiece.Snowball ||piece.type==Enums.GamePiece.Fireball){

                if(key==KeyEvent.VK_SPACE) //space==run, start moving
                {
                    piece.setXvel(-5);
                }
            }
        }
    }
}
