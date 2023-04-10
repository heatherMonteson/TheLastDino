package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePieceHandler {
    static LinkedList<GamePiece> gamePieces = new LinkedList<GamePiece>();

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
        //TODO: update to add objects to list using a factory with input being an enum type
        gamePieces.add(piece);
    }

    public void removeObject(GamePiece gamePiece){
        gamePieces.remove(gamePiece);
    }
}
