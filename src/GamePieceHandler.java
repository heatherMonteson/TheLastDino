package src;

import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePieceHandler {
    static LinkedList<GamePiece> gamePieces = new LinkedList<GamePiece>();
    static private GamePieceHandler handler=new GamePieceHandler();

    private GamePieceHandler(){
        //auto add dino
        GamePiece dino = Dino.getDino();
        addObject(dino);
    }

    public static GamePieceHandler getHandler() {
        return handler;
    }

    public int getNumPieces(){
        return gamePieces.size(); 
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

    public void removeGamePiece(GamePiece gamePiece){
        gamePieces.remove(gamePiece);
    }

    public void removeAllButDino(){
        for(GamePiece piece : gamePieces){
            if(piece.type!= Enums.GamePiece.Dino)
                removeGamePiece(piece);
        }
    }

}
