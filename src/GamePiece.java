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
    private Random rand = new Random();

    public Bush(int xPos, int yPos){ 
        super(xPos, yPos, Enums.GamePiece.Bush);
        this.xPos= xPos; 
        this.yPos=yPos;
        // xPos = 400;
        // yPos = 325;
    }
    public void render(Graphics graphics){
        //can we add two position arguments for this render which allow us to change the xPos and yPos
        //make list bushes and then this renders them 
        //System.out.println( xPos);
        
        // System.out.println("xPos " + xPos);
        // System.out.println("xVel " + xVel);
        // int oops = xPos + xVel;
        // System.out.println("tick " + oops);
        graphics.drawImage(bush, xPos,yPos,110, 70, null); 
       
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Icicle extends GamePiece{
    public Icicle(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Icicle);
    }

    public void render(Graphics graphics){
       
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Snowball extends GamePiece{
    public Snowball(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Snowball);
    }
    public void render(Graphics graphics){}

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Fireball extends GamePiece{
    public Fireball(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Fireball);
    }
    public void render(Graphics graphics){

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Leaf extends GamePiece{
    public Leaf(){
        super(GameController.width, GameController.height/2, Enums.GamePiece.Leaf);
    }
    public void render(Graphics graphics){

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Cloud extends GamePiece{

    //TODO: randomize positions
    public Cloud() {
        super(GameController.width-50, GameController.height/2, Enums.GamePiece.Cloud);
    }

    public void render(Graphics graphics) {

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class SmokeCloud extends GamePiece{

    //TODO: randomize positions
    public SmokeCloud() {
        super(GameController.width-50, GameController.height/2, Enums.GamePiece.SmokeCloud);
    }

    public void render(Graphics graphics) {

    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Dino extends GamePiece{

    public boolean isJumping;
    private static final Dino singleDino = new Dino();
    public Image gif = Toolkit.getDefaultToolkit().getImage("Images/runner.gif");

    private Dino(){
        super(0, 335, Enums.GamePiece.Dino);
        isJumping=false;
    }
    public static Dino getDino(){
        return singleDino;
    }

    public void render(Graphics graphics){

        graphics.drawImage(gif, xPos, yPos,100, 100, null); //correct dino coordinates to get him on the ground
        //graphics.drawImage(dinoImage, xPos,yPos,imagewidth, imageheight,null);
    }
    public void tick(){

        if(isJumping == true){ //if dino is jumping, we wait a moment and then readjust his position back down
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    yPos =335;
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
        System.out.println(yVel);
        System.out.println(yPos);

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
