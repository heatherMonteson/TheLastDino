package src;
import java.util.Random;

public abstract class GamePieceFactory {
    public void makeGamePiece(Enums.GamePiece type, int numberPieces){
        GamePieceHandler handler = GamePieceHandler.getHandler();
        for(int i=1; i<=numberPieces; i++){
            GamePiece piece = createPiece(type);
            //debugging code
            if(type == Enums.GamePiece.Bush){
                System.out.println(piece.xPos);
            }
            if(piece!=null)
                handler.addObject(piece);
            else
                throw new IllegalArgumentException("Error creating game pieces of type" + type);
        }
    }
    protected abstract GamePiece createPiece(Enums.GamePiece type);
}

class CreateGamePiece extends GamePieceFactory{
    //static int temp2; //this is so first bush is no closer than 400
    //reset temp at some point , could reset in makeGamePiece
    protected GamePiece createPiece(Enums.GamePiece type) {
        GamePiece piece= null;
        int offset = 0;
        int temp2 = 400;
        Random rand = new Random();
        if(type==Enums.GamePiece.Icicle)
            piece=new Icicle();
        if(type==Enums.GamePiece.SmokeCloud)
            piece = new SmokeCloud();
        if(type==Enums.GamePiece.Snowball)
            piece= new Snowball();
        if(type==Enums.GamePiece.Cloud)
            piece = new Cloud();
        if(type==Enums.GamePiece.Fireball)
            piece = new Fireball();
        if(type==Enums.GamePiece.Bush)
            offset = rand.nextInt(4501) + 800; //bigger this is, the more spread out bush is
            temp2 += offset;
            piece = new Bush(temp2,365); //why is there bush before dino, have something to do with adding xpos to xvel
            
        if(type==Enums.GamePiece.Leaf)
            piece =  new Leaf();
        return piece;
    }
}