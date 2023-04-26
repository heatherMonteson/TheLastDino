package src;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/*
 *GamePiece: all moving game pieces
 *
 *
 */

public abstract class GamePiece {
    protected int xPos, yPos;
    protected  int xVel, yVel;
    protected Enums.GamePiece type;
    protected Rectangle bounds;

    public GamePiece(int xPos, int yPos, Enums.GamePiece type)
    {
        this.xPos= xPos;
        this.yPos=yPos;
        this.type = type;
        xVel=0;
        yVel=0;
    }
    //show game piece
    public abstract void render(Graphics graphics);

    public void tick(){
        //tick changes xPos
        xPos+=xVel;
        yPos+=yVel;
    }

    public Enums.GamePiece getType(){
        return type;
    }
    public Rectangle getBounds(){return bounds;}
    //piece location/velocity getters (just setting now to play with input)

    public void setXvel(int x){
        this.xVel=x;
    }
}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Bush extends GamePiece{
    public Image bush = Toolkit.getDefaultToolkit().getImage("Images/bush1.png");

    public Bush(int xPos, int yPos){ 
        super(xPos, yPos, Enums.GamePiece.Bush);
        this.xPos= xPos; 
        this.yPos=yPos;
        
    }
    public void render(Graphics graphics){
        graphics.drawImage(bush, xPos,yPos,110, 70, null); 
       
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Icicle extends GamePiece{
    public Image icicle = Toolkit.getDefaultToolkit().getImage("Images/icicle2.png");

    public Icicle(int xPos, int yPos){
        super(xPos, yPos, Enums.GamePiece.Icicle);
        this.xPos= xPos; 
        this.yPos=yPos;
    }

    public void render(Graphics graphics){
        graphics.drawImage(icicle, xPos,yPos,110, 70, null); 
       
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Snowball extends GamePiece{
    public Image snowball = Toolkit.getDefaultToolkit().getImage("Images/snowBall1.png");

    public Snowball(int xPos, int yPos){
        super(xPos, yPos, Enums.GamePiece.Snowball);
        this.xPos= xPos; 
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes 
    }
    public void render(Graphics graphics){
        graphics.drawImage(snowball, xPos, yPos,150, 75, null);

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Fireball extends GamePiece{
    public Image fireball = Toolkit.getDefaultToolkit().getImage("Images/fireball1.png");

    public Fireball(int xPos, int yPos){
        super(xPos, yPos, Enums.GamePiece.Fireball);
        this.xPos= xPos; 
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes 
    }
    public void render(Graphics graphics){
        graphics.drawImage(fireball, xPos,yPos,150, 100, null); //leaf is currently stagnant
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Leaf extends GamePiece{
    public Image leaf = Toolkit.getDefaultToolkit().getImage("Images/leaf.png");

    public Leaf(int xPos, int yPos){
        super(xPos, yPos, Enums.GamePiece.Leaf);
        this.xPos= xPos; 
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes 
    }
    public void render(Graphics graphics){
        graphics.drawImage(leaf, xPos,yPos,80, 50, null); //leaf is currently stagnant

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Cloud extends GamePiece{
    public Image cloud1 = Toolkit.getDefaultToolkit().getImage("Images/cloud1.png");
    public Image cloud2 = Toolkit.getDefaultToolkit().getImage("Images/cloud2.png");
    //TODO: randomize positions
    public Cloud(int xPos, int yPos) {
        super(xPos, yPos, Enums.GamePiece.Cloud);
        this.xPos= xPos; 
        this.yPos=yPos;
        // super(GameController.width-50, GameController.height/2, Enums.GamePiece.Cloud);
    }

    public void render(Graphics graphics) {
        int xtemp = xPos + 120;
        int ytemp = yPos - 75;
        graphics.drawImage(cloud1, xPos,yPos,150, 70, null);
        graphics.drawImage(cloud2, xtemp ,ytemp ,150, 70, null);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class SmokeCloud extends GamePiece{
    public Image SmokeCloud1 = Toolkit.getDefaultToolkit().getImage("Images/SmokeCloud.png");
    public Image SmokeCloud2 = Toolkit.getDefaultToolkit().getImage("Images/smokeCloud1.png");

    //TODO: randomize positions
    public SmokeCloud(int xPos, int yPos) {
        super(xPos, yPos, Enums.GamePiece.SmokeCloud);
        this.xPos= xPos; 
        this.yPos=yPos;
    }

    public void render(Graphics graphics) {
        int xtemp = xPos + 120;
        int ytemp = yPos - 70;
        graphics.drawImage(SmokeCloud1, xPos,yPos,150, 70, null);
        graphics.drawImage(SmokeCloud2, xtemp ,ytemp ,150, 70, null);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

class Dino extends GamePiece {

    public boolean isJumping;
    private static final Dino singleDino = new Dino();
    public Image gif = Toolkit.getDefaultToolkit().getImage("Images/runner.gif");

    private Dino() {
        super(50, 285, Enums.GamePiece.Dino);
        isJumping = false;
    }

    public static Dino getDino() {
        return singleDino;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(gif, xPos, yPos, 150, 150, null); //correct dino coordinates to get him on the ground
    }

    public void tick(){

        if(isJumping){ //if dino is jumping, we wait a moment and then readjust his position back down
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    yPos =285;
                    isJumping = false;
                }
            }, 350); // delay of 1 second
        }
        xPos+=xVel;
        yPos+=yVel;

        //check collisions with the dino
//        for(GamePiece piece: GamePieceHandler.gamePieces){
//            //collision with leaf
//            if(piece.getType()== Enums.GamePiece.Leaf && piece.getBounds() == this.bounds)
//                Broker.getBroker().event(Enums.Event.AteLeaves);
//                //collision with obstacle
//            else if(piece.getType()!= Enums.GamePiece.Cloud && piece.getType()!= Enums.GamePiece.SmokeCloud && piece.getBounds() == this.bounds)
//                Broker.getBroker().event(Enums.Event.LostLife);
//        }
    }

    //to bring dino back down from jump
    public void resetDinoPosition(){

        if(isJumping){//hes jumping and needs to come back down
            yPos -= 20; //it takes this and subtracts from yPos every second
        }

        isJumping = false; //when he is back on the ground
    }

    public void jump(){
        isJumping = true;
        yPos = 160;

    }



    public void duck() {
    }

    public void stand() {
    }
}