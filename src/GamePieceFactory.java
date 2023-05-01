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
                //System.out.println(piece.xPos);
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
    private static int tempBush = 0; // a static variable so that it is shared across all CreateGamePiece objects.
    private static int tempCloud = 0;
    public static int tempLeaf = 0;
    private static int tempFireBall = 0;
    private static int tempSmokeCloud = 0;
    private static int tempIcicle = 0;
    private static int tempSnowball = 0;
    protected GamePiece createPiece(Enums.GamePiece type) {
        GamePiece piece= null;
        int offset = 0;
        //int temp2 = 400; //resets to 400 every time, we don't really need to reset this
        Random rand = new Random();
        if(type==Enums.GamePiece.Icicle){
            offset = rand.nextInt(800) + 400; //gives us range of values from [400,800]
            tempIcicle += offset; //increasing our temp to make sure bushes are always forward
            piece = new Icicle(tempIcicle,365);
        }
        else if(type==Enums.GamePiece.SmokeCloud){
            offset = rand.nextInt(500) + 300; //change this
            tempSmokeCloud += offset;
            piece = new SmokeCloud(tempSmokeCloud, 90);
        }
        else if(type==Enums.GamePiece.Snowball){
            offset = rand.nextInt(900) + 400; //change this
            tempSnowball += offset;
            piece = new Snowball(tempSnowball, 250);

        }
        else if(type==Enums.GamePiece.Cloud){
            offset = rand.nextInt(500) + 300; //gives us range of values from [300,500]
            tempCloud += offset; //increasing our temp to make sure bushes are always forward
            piece = new Cloud(tempCloud, 90);
        }
        else if(type==Enums.GamePiece.Fireball){
            offset = rand.nextInt(900) + 400; //change this
            tempFireBall += offset;
            piece = new Fireball(tempFireBall, 250);
        }
        else if(type==Enums.GamePiece.Bush){
            offset = rand.nextInt(800) + 400; //gives us range of values from [400,800]
            tempBush += offset; //increasing our temp to make sure bushes are always forward
            piece = new Bush(tempBush,375);
        }
        else if(type==Enums.GamePiece.Leaf){
            //currently only one leaf is rendering
            offset = rand.nextInt(900) + 400; //change this
            tempLeaf += offset;
            piece = new Leaf(tempLeaf, 200);
        }
        return piece;
    }

}