package src;

import java.awt.*;
import java.util.LinkedList;

public class GamePieceHandler {
    static LinkedList<GamePiece> gamePieces = new LinkedList<GamePiece>();
    static private GamePieceHandler handler=new GamePieceHandler();

    private GamePieceHandler(){
        //auto add dino, always needs to be in the handler
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

    public void removeAllButDino(){
        gamePieces = new LinkedList<GamePiece>();
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

}
