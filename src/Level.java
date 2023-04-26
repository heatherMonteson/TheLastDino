package src;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/*
 * Level and Level1, Level2, Level3, EndLevel: handles calling for the correct game pieces for the level to be made and sets/renders
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


class EndLevel extends Level{

    public EndLevel(){
        level=Enums.Level.End;
        points=0;
        image=Toolkit.getDefaultToolkit().getImage("Images/Game Over.png");
    }

    @Override
    public void activate() {
        GamePieceHandler handler = GamePieceHandler.getHandler();
        handler.removeAllButDino();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D graphics = (Graphics2D)g;
        Player player = Player.getPlayer();
        int height=GameController.height;
        int size=25;
        Color color= Color.white;
        Top3Parser parser = new Top3Parser();

        graphics.drawImage(image, -40,0,GameController.width+100, GameController.height, null);

        // category
        Utility.textRender(graphics,  color,"Player",250, height/2-100, size);
        Utility.textRender(graphics,  color,"Score",385, height/2-100, size);
        Utility.textRender(graphics,  color,"Lives",525, height/2-100, size);
        Utility.textRender(graphics,  color,"Level",625, height/2-100, size);

        //Current player
        Utility.textRender(graphics,  color,"Your Play:    "+ player.getName(),75, height/2-50, size);
        Utility.textRender(graphics,  color,String.valueOf(player.getScore()),400, height/2-50, size);
        Utility.textRender(graphics,  color,String.valueOf(player.getLives()),550, height/2-50, size);
        Utility.textRender(graphics,  color,String.valueOf( player.GetLevel()),650, height/2-50, size);

        if(parser.isAbleToParse()){
            //top player
            Utility.textRender(graphics,  color,"#1 Player:    "+ parser.getPlayer1Name(),75, height/2, size);
            Utility.textRender(graphics,  color,parser.getPlayer1Score(),400, height/2, size);
            Utility.textRender(graphics,  color,parser.getPlayer1Lives(),550, height/2, size);
            Utility.textRender(graphics,  color,parser.getPlayer1Level(),650, height/2, size);

            //second place player
            Utility.textRender(graphics,  color,"#2 Player:    "+ parser.getPlayer2Name(),75, height/2+50, size);
            Utility.textRender(graphics,  color,parser.getPlayer2Score(),400, height/2+50, size);
            Utility.textRender(graphics,  color,parser.getPlayer2Lives(),550, height/2+50, size);
            Utility.textRender(graphics,  color,parser.getPlayer2Level(),650, height/2+50, size);

            //third place player
            Utility.textRender(graphics,  color,"#3 Player:    "+ parser.getPlayer3Name(),75, height/2+100, size);
            Utility.textRender(graphics,  color,parser.getPlayer3Score(),400, height/2+100, size);
            Utility.textRender(graphics,  color, parser.getPlayer3Lives(),550, height/2+100, size);
            Utility.textRender(graphics,  color,parser.getPlayer3Level(),650, height/2+100, size);
        }

    }

}
