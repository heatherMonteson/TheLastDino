package src;

public abstract class GamePieceFactory {
    public void makeGamePiece(Enums.GamePiece type, int numberPieces){
        GamePieceHandler handler = GamePieceHandler.getHandler();
        for(int i=1; i<=numberPieces; i++){
            GamePiece piece = createPiece(type);
            if(piece!=null)
                handler.addObject(piece);
            else
                throw new IllegalArgumentException("Error creating game pieces of type" + type);
        }
    }
    protected abstract GamePiece createPiece(Enums.GamePiece type);
}

class CreateGamePiece extends GamePieceFactory{

    protected GamePiece createPiece(Enums.GamePiece type) {
        GamePiece piece= null;
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
            piece = new Bush();
        if(type==Enums.GamePiece.Leaf)
            piece =  new Leaf();
        return piece;
    }
}