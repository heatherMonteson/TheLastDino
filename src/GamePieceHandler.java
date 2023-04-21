package src;

import java.awt.*;
import java.util.LinkedList;

//Holds all game pieces that have movement: Dino, clouds, fire/snowball, leaves, bush, icicle
//handles calling the rendering and ticking methods to update all game pieces
//Always need dino in handler

//OO pattern: Eager singleton
public class GamePieceHandler {
    static LinkedList<GamePiece> gamePieces = new LinkedList<GamePiece>();
    static private final GamePieceHandler handler=new GamePieceHandler();

    private GamePieceHandler(){
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

    public static GamePieceHandler getHandler() {
        return handler;
    }

    public void tick(){
        for(GamePiece piece: gamePieces){
             piece.tick();
        }
    }

    public void render(Graphics graphics){
        for(GamePiece piece: gamePieces){
            piece.render(graphics);
        }
    }

    public void addObject(GamePiece piece){
        gamePieces.add(piece);
    }

    //resets at each level to hold new game pieces
    public void removeAllButDino(){
        gamePieces = new LinkedList<GamePiece>();
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

}
