package src;
import java.util.Random;
/*
 *GamePieceFactory and CreateGamePiece: handles the creation of all moving game pieces (except the dino which is a singleton)
 *
 * OO pattern: Factory
 */
public abstract class GamePieceFactory {
    public void makeGamePiece(Enums.GamePiece type, int numberPieces){
        GamePieceHandler handler = GamePieceHandler.getHandler();
        GamePiece piece;
        for(int i=1; i<=numberPieces; i++){
            piece = createPiece(type);
            //debugging code
            if(type == Enums.GamePiece.Bush){
                System.out.println(piece.xPos);
            }
            if(piece!=null){
                handler.addObject(piece);
            }
            else
                throw new IllegalArgumentException("Error creating game pieces of type" + type);
        }
    }
    protected abstract GamePiece createPiece(Enums.GamePiece type);
}

class CreateGamePiece extends GamePieceFactory{
    //static int temp2; //this is so first bush is no closer than 400
    //reset temp at some point , could reset in makeGamePiece
    private static int temp2 = 0; // a static variable so that it is shared across all CreateGamePiece objects.
    protected GamePiece createPiece(Enums.GamePiece type) {
        GamePiece piece= null;
        int offset = 0;
        //int temp2 = 400; //resets to 400 every time, we don't really need to reset this
        Random rand = new Random();
        if(type==Enums.GamePiece.Icicle){
            piece=new Icicle();
        }
        else if(type==Enums.GamePiece.SmokeCloud){
            piece = new SmokeCloud();
        }
        else if(type==Enums.GamePiece.Snowball){
            piece= new Snowball();
        }
        else if(type==Enums.GamePiece.Cloud){
            piece = new Cloud();
        }
        else if(type==Enums.GamePiece.Fireball){
            piece = new Fireball();
        }
        else if(type==Enums.GamePiece.Bush){
            System.out.println("what temp is initialized to" + temp2);{
            offset = rand.nextInt(4501) + 800; //bigger this is, the more spread out bush is
            temp2 += offset; //basically just adding offset to 400 every time 
            System.out.println("temp2 incremented: " + temp2);
            piece = new Bush(temp2,365); //why is there bush before dino, have something to do with adding xpos to xvel
        }
        else if(type==Enums.GamePiece.Leaf){
            piece =  new Leaf();
        }
        return piece;
    }
}