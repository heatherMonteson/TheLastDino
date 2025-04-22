package src;

import java.util.Random;
/*
 *GamePieceFactory and CreateGamePiece: handles the creation of all moving game pieces (except the dino which is a singleton)
 *
 * OO pattern: Factory
 */
public abstract class GamePieceFactory {

    protected static int position;

    /*
     *makeGamePiece: creates n game pieces based on the requested numberPieces and their type
     * and adds them to the game piece handler as they are made
     *
     * @param Enums.GamePiece, int
     * @return nothing
     */
    public void makeGamePiece(Enums.GamePiece type, int numberPieces){
        GamePieceHandler handler = GamePieceHandler.getHandler();
        GamePiece piece;
        position=0;

        for(int i=1; i<=numberPieces; i++){
            piece = createPiece(type);
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

    private Random rand = new Random();

    /*
     * buffer: adds a buffer between game pieces so that they do not overlap piece of the same type and there is
     * sudo random spacing between them
     *
     * @param int, int
     * @return int
     */
    private int buffer (int bound, int add){
        int offset = rand.nextInt(bound) + add; //change this
        position += offset;
        return position;
    }

    /*
     * createPiece: creates game pieces based on type calling the buffer method to add space between items
     *
     * @param Enums.GamePiece
     * @return GamePiece
     */
    protected GamePiece createPiece(Enums.GamePiece type) {
        GamePiece piece= null;

        if(type==Enums.GamePiece.Icicle){
            piece = new Icicle(buffer(800, 400), 375);
        }
        else if(type==Enums.GamePiece.SmokeCloud){
            piece = new SmokeCloud(buffer(500, 300), 90);
        }
        else if(type==Enums.GamePiece.Snowball){
            piece = new Snowball(buffer(900, 400), Utility.findValue(280,300) );
        }
        else if(type==Enums.GamePiece.Cloud){
            piece = new Cloud(buffer(500, 300), 90);
        }
        else if(type==Enums.GamePiece.Fireball){
            piece = new Fireball(buffer(900, 400), Utility.findValue(280,300));
        }
        else if(type==Enums.GamePiece.Bush){
            piece = new Bush(buffer(700, 400), 375);
        }
        else if(type==Enums.GamePiece.Leaf){
            piece = new Leaf(buffer(800, 400), Utility.findValue(200, 300));
        }
        return piece;
    }

}