package src;

import java.awt.*;
import java.util.LinkedList;

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

    public static void addObject(GamePiece piece){
        gamePieces.add(piece);
    }

    //resets at each level to hold new game pieces
    public void removeAllButDino(){
        gamePieces = new LinkedList<GamePiece>();
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

}
