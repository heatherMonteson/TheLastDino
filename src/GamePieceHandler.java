package src;

import java.awt.*;
import java.util.LinkedList;
/*
 * GamePieceHandler: Handles all the objects in the game that will move as a result from user key input
 * activates all their render and tick methods to update the game piece location
 *
 * Citation: https://www.youtube.com/watch?v=e8g9eNnFpHQ
 * used for the idea of the handler
 */
public class GamePieceHandler {
    static LinkedList<GamePiece> gamePieces = new LinkedList<GamePiece>();
    static private final GamePieceHandler handler=new GamePieceHandler();

    //always need to have the dino, always auto add
    private GamePieceHandler(){
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

    public static GamePieceHandler getHandler() {
        return handler;
    }

    /*
     * tick: calls to game piece tick to update game piece locations
     *
     * @param nothing
     * @return nothing
     */
    public void tick(){
        for(GamePiece piece: gamePieces){
            piece.tick();
        }
    }

    /*
     * render: calls to game piece render to update game piece graphics
     *
     * @param nothing
     * @return nothing
     */
    public void render(Graphics graphics){
        for(GamePiece piece: gamePieces){
            piece.render(graphics);
           
        }
    }

    public void addObject(GamePiece piece){
        gamePieces.add(piece);
    }

    /*
     * removeAllButDino: Always has the dino piece in the list
     *
     * @param nothing
     * @return nothing
     */
    public void removeAllButDino(){
        gamePieces = new LinkedList<GamePiece>();
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }
}
