package src;

public abstract class Level {
    protected Enums.Level level;
    protected int points;

    public Enums.Level getLevel(){return level;}
    public int getPoints(){return points;}

    //must clear game piece handler list at level 2 and 3 to toss all remaining pieces
    //send required game pieces to the factory to be added to the game piece handler
    abstract public void activate();

    //display the background image for the level
    abstract public void render();

}

class Level1 extends Level{
    public Level1(){
        level=Enums.Level.L1;
        points=50;
    }
    @Override
    public void activate() {
        GamePieceFactory factoryConnection = new CreateGamePiece();
        factoryConnection.makeGamePiece(Enums.GamePiece.Cloud, 50);
        factoryConnection.makeGamePiece(Enums.GamePiece.Bush, 15);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 15);
    }

    @Override
    public void render() {

    }
}

class Level2 extends Level{
    public Level2(){
        level=Enums.Level.L2;
        points=75;
    }

    @Override
    public void activate() {
        //clear old pieces
        GamePieceHandler handler = GamePieceHandler.getHandler();
        handler.removeAllButDino();

        //create level specific pieces
        GamePieceFactory factoryConnection = new CreateGamePiece();
        factoryConnection.makeGamePiece(Enums.GamePiece.SmokeCloud, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Fireball, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 10);
    }

    @Override
    public void render() {

    }
}

class Level3 extends Level{
    public Level3(){
        level=Enums.Level.L3;
        points=100;
    }

    @Override
    public void activate() {
        //clear old pieces
        GamePieceHandler handler = GamePieceHandler.getHandler();
        handler.removeAllButDino();

        //create level specific pieces
        GamePieceFactory factoryConnection = new CreateGamePiece();
        factoryConnection.makeGamePiece(Enums.GamePiece.Icicle, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Snowball, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 10);
    }

    @Override
    public void render() {

    }
}
