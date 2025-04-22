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
    public boolean collision1;
    

    //public static Rectangle bushRect;

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
        xPos+=xVel;
        yPos+=yVel;
    }

    public Rectangle getBounds(){
        return bounds;
    }

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
        this.collision1 = false;
        this.bounds=new Rectangle(xPos, yPos, 80, 50);
    }
    public void render(Graphics graphics){
        graphics.drawImage(bush, xPos,yPos,100, 60, null);
//        graphics.draw3DRect(xPos+10,yPos,80, 50, false);

    }

    public void tick(){
        xPos+=xVel;
        yPos+=yVel;
        if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
            collision1=true;
            Broker.getBroker().event(Enums.Event.LostLife);
        }
        bounds.setLocation(xPos+10, yPos);
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
        this.bounds=new Rectangle(xPos, yPos, 80, 50);
    }

    public void render(Graphics graphics){
        graphics.drawImage(icicle, xPos,yPos,90, 60, null);
    }

    public void tick(){
        xPos+=xVel;
        yPos+=yVel;
        if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
            collision1=true;
            Broker.getBroker().event(Enums.Event.LostLife);
        }
        bounds.setLocation(xPos, yPos);
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
        this.bounds=new Rectangle(xPos + 10, yPos, 100, 70);
    }
    public void render(Graphics graphics){
        graphics.drawImage(snowball, xPos, yPos,150, 75, null);
//         graphics.setColor(Color.blue);
//         graphics.fillRect(xPos + 10 , yPos ,100, 70);

    }

    public void tick(){
        //tick changes xPos
        xPos+=xVel;
        yPos+=yVel;
        if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
            collision1=true;
            Broker.getBroker().event(Enums.Event.LostLife);
        }
        bounds.setLocation(xPos + 10, yPos);
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
        this.bounds=new Rectangle(xPos, yPos, 100, 70);
    }
    public void render(Graphics graphics){
        graphics.drawImage(fireball, xPos,yPos,130, 80, null); //leaf is currently stagnant
        // graphics.setColor(Color.orange);
        // graphics.fillRect(xPos , yPos ,100, 70);
    }

    public void tick(){
        //tick changes xPos
        xPos+=xVel;
        yPos+=yVel;
        if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
            collision1=true;
            Broker.getBroker().event(Enums.Event.LostLife);
        }
        bounds.setLocation(xPos, yPos);
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
        this.bounds=new Rectangle(xPos, yPos, 70, 40);
    }
    public void render(Graphics graphics){
        graphics.drawImage(leaf, xPos,yPos,80, 50, null);
    }

       public void tick(){
        //tick changes xPos
        xPos+=xVel;
        yPos+=yVel;
        if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
            collision1=true;
            Broker.getBroker().event(Enums.Event.AteLeaves);
            leaf = Toolkit.getDefaultToolkit().getImage("");
        }
        bounds.setLocation(xPos,yPos);
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

    private boolean isJumping;
    private boolean isDucking;
    private boolean isRunning;
    private static final Dino singleDino = new Dino();
    private int groundPosition= 335;

    //images
    private final Image runImg = Toolkit.getDefaultToolkit().getImage("Images/runner.gif");
    private final Image standImg = Toolkit.getDefaultToolkit().getImage("Images/standing.png");
    private final Image duckImg = Toolkit.getDefaultToolkit().getImage("Images/duck.png");
    private final Image jumpImg = Toolkit.getDefaultToolkit().getImage("Images/jump.png");
    private Image currImage;

    private Dino() {
        super(40, 335, Enums.GamePiece.Dino);
        currImage=standImg;
        bounds=new Rectangle(xPos+20, yPos, 60, 50);
    }

    public static Dino getDino() {
        return singleDino;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(currImage, xPos,yPos,100, 100,null);
//        graphics.draw3DRect(xPos+20, yPos, 60, 50, false);
    }


    public void tick() {
        //this if is responsible for bringing dino back to ground after jumping
        //we use timer for jumping bc we don't want jump method to be controlled and consistent
        if(isRunning && !isJumping){
            currImage=runImg;
        } else if(!isJumping) {
            currImage=standImg;
        }

        if(isDucking){
            currImage=duckImg;
            yPos=groundPosition;
            bounds.setBounds(xPos, yPos + 50, 100, 50);
        }

        else if(isJumping && yPos==groundPosition){
            yVel=-4;
            currImage=jumpImg;
        }
        else if(isJumping && yPos<=200){
            yVel=4;
            isJumping=false;
            currImage=jumpImg;
        }
        else if(!isJumping && yPos>= groundPosition-5){
            yVel=0;
            yPos=groundPosition;
        }

        if(!isDucking){
            bounds.setBounds(xPos+20, yPos, 60, 50);
        }

        xPos += xVel;
        yPos += yVel;

    }

    public void jump(){
        isJumping = true;
    }

    public void duck() {
        isDucking=true;
    }

    public void stand() {
        isDucking=false;
    }
    public void running(){
        isRunning=true;
    }
    public void stopping(){
        isRunning=false;
    }

}