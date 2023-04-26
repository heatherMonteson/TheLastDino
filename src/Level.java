package src;

import java.awt.*;
/*
 * Level and Level1, Level2, Level3: handles calling for the correct game pieces for the level to be made and sets/renders
 * the level background graphics. Also holds point value at a set level
 *
 */
public abstract class Level {
    protected Enums.Level level;
    protected int points; //point value at every level

    Image image;//general image display through level play
    Image levelStartImg; //opening level image

    GamePieceFactory factoryConnection = new CreateGamePiece();

    public Enums.Level getLevel(){return level;}
    public int getPoints(){return points;}

    /*
     * activate: handle calling for appropriate game piece creation for that level
     *
     * @param noting
     * @return nothing
     */
    abstract public void activate();

    /*
     * render: renders the background image for the level
     *
     * @param Graphics
     * @return nothing
     */
    public void render(Graphics graphics) {
        graphics.drawImage(image, -40,0,GameController.width+100, GameController.height, null);
    }
    /*
     * render: renders image for the start of the level
     *
     * @param Graphics
     * @return nothing
     */
    public void startRender(Graphics graphics) {
        graphics.drawImage(levelStartImg, -40,0,GameController.width+100, GameController.height, null);
    }
}

class Level1 extends Level{

    public Level1(){
        level=Enums.Level.L1;
        points=50;
        image=Toolkit.getDefaultToolkit().getImage("Images/level1.png");
        levelStartImg=Toolkit.getDefaultToolkit().getImage("Images/start1.png");
    }
    @Override
    public void activate() {
        factoryConnection.makeGamePiece(Enums.GamePiece.Cloud, 20);
        factoryConnection.makeGamePiece(Enums.GamePiece.Bush, 15);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 5);
    }

}

class Level2 extends Level{
    public Level2(){
        level=Enums.Level.L2;
        points=75;
        image=Toolkit.getDefaultToolkit().getImage("Images/level2.png");
        levelStartImg=Toolkit.getDefaultToolkit().getImage("Images/start2.png");

    }

    @Override
    public void activate() {
        //clear old pieces
        GamePieceHandler handler = GamePieceHandler.getHandler();
        handler.removeAllButDino();

        factoryConnection.makeGamePiece(Enums.GamePiece.SmokeCloud, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Fireball, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 10); 
    }

}

class Level3 extends Level{
    public Level3(){
        level=Enums.Level.L3;
        points=100;
        image=Toolkit.getDefaultToolkit().getImage("Images/level3.png");
        levelStartImg=Toolkit.getDefaultToolkit().getImage("Images/start3.png");

    }

    @Override
    public void activate() {
        //clear old pieces
        GamePieceHandler handler = GamePieceHandler.getHandler();
        handler.removeAllButDino();

        factoryConnection.makeGamePiece(Enums.GamePiece.Icicle, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Snowball, 10);
        factoryConnection.makeGamePiece(Enums.GamePiece.Leaf, 10);
    }

}
