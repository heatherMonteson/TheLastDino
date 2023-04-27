package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
    public static boolean space = false;

    public KeyInput(){
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
        for(GamePiece piece : GamePieceHandler.gamePieces){
            //boolean space = false;
            if(piece.type== Enums.GamePiece.Dino) //space==run
            {
                Dino dino = (Dino) piece;
                if(key==KeyEvent.VK_UP){
                    dino.jump();
                    
                }
                else if(key==KeyEvent.VK_DOWN){
                    dino.duck();

                }
                else if (key == KeyEvent.VK_SPACE){
                    //issue: when space bar and duck, the clouds and bush dont move when duck is released 
                    //when space bar is hit render dino.run
                    //when space bar is released render dino.stop
                    dino.isRunning();
                    space = true;
                }
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
                    //Todo: events for Dino duck
                    //piece.setXvel(runningVelocity);
                    piece.setXvel(0);

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
        for(GamePiece piece : GamePieceHandler.gamePieces){

            if(piece.type == Enums.GamePiece.Dino) //space==run
            {
                Dino dino = (Dino) piece;
                if(key==KeyEvent.VK_DOWN){
                    dino.stopDucking();

                }
                if(key == KeyEvent.VK_SPACE){// when space bar is released we want dino to stop running
                    dino.stand();
                    space = false;
                }
            }

            if(piece.type==Enums.GamePiece.Bush || piece.type==Enums.GamePiece.Icicle)
            {

                if(key==KeyEvent.VK_SPACE){ //when space is released, bushes stop
                    piece.setXvel(0);
                    
                }

                if(key==KeyEvent.VK_DOWN && space == true){ 
                    //dino is running and ducking (press space and down button)
                    piece.setXvel(runningVelocity);

                }else if(key==KeyEvent.VK_DOWN){ 
                    //need to set dino to run if dino is hitting space bar and duck
                    Dino.isStand = true;

                }
                
            
                
                
            }
            if( piece.type==Enums.GamePiece.Cloud || piece.type==Enums.GamePiece.SmokeCloud)
            {
                if(key==KeyEvent.VK_SPACE){ //when stop running set velocity to 0
                    piece.setXvel(0);
                }
                if(key==KeyEvent.VK_DOWN && space == true){ 
                    //dino is running and ducking (press space and down button)
                    piece.setXvel(runningVelocity);

                }
            }
        }
    }
}
