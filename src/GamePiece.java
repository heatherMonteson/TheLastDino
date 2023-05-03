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
    protected TickSorter tickSort;
    

    //public static Rectangle bushRect;

    public GamePiece(int xPos, int yPos, Enums.GamePiece type, TickSorter tickSort)
    {
        this.xPos= xPos;
        this.yPos=yPos;
        this.type = type;
        this.tickSort = tickSort;
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
    //private TickStrategy tickBush;
    private TickSorter tickSort;

    public Bush(int xPos, int yPos, TickSorter tickSort){
        super(xPos, yPos, Enums.GamePiece.Bush, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
        this.collision1 = false;

    }
    public void render(Graphics graphics){
        graphics.drawImage(bush, xPos,yPos,100, 60, null);
        //THIS CODE IS TO GET A VISUAL OF BOUND SIZE
        // graphics.setColor(Color.green);
        // graphics.fillRect(xPos + 10, yPos ,80, 60);

    }
    public Rectangle getBounds(){
        return new Rectangle(xPos+10, yPos, 80, 50);
    }

    public void tick(){
        //tick changes xPos
        // xPos+=xVel;
        // yPos+=yVel;
        // if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
        //     collision1=true;
        //     Broker.getBroker().event(Enums.Event.LostLife);
        // }
        tickSort.tick(this);
    }
}


//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Icicle extends GamePiece{
    private TickSorter tickSort;
    public Image icicle = Toolkit.getDefaultToolkit().getImage("Images/icicle2.png");

    public Icicle(int xPos, int yPos, TickSorter tickSort){
        super(xPos, yPos, Enums.GamePiece.Icicle, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
    }

    public void render(Graphics graphics){
        graphics.drawImage(icicle, xPos,yPos,90, 60, null);
        // graphics.setColor(Color.blue);
        // graphics.fillRect(xPos , yPos ,80, 50);

    }
    public Rectangle getBounds(){
        return new Rectangle(xPos, yPos, 80, 50);
    }
    public void tick(){
        //tick changes xPos
        // xPos+=xVel;
        // yPos+=yVel;
        // if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
        //     collision1=true;
        //     Broker.getBroker().event(Enums.Event.LostLife);
        // }
        tickSort.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Snowball extends GamePiece{
    public Image snowball = Toolkit.getDefaultToolkit().getImage("Images/snowBall1.png");

    public Snowball(int xPos, int yPos,TickSorter tickSort){
        super(xPos, yPos, Enums.GamePiece.Snowball, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes
    }
    public void render(Graphics graphics){
        graphics.drawImage(snowball, xPos, yPos,150, 75, null);
        // graphics.setColor(Color.blue);
        // graphics.fillRect(xPos + 10 , yPos ,100, 70);

    }
    public Rectangle getBounds(){
        return new Rectangle(xPos + 10, yPos, 100, 70);
    }
    public void tick(){
        //tick changes xPos
        // xPos+=xVel;
        // yPos+=yVel;
        // if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
        //     collision1=true;
        //     Broker.getBroker().event(Enums.Event.LostLife);
        // }
        tickSort.tick(this);
    }
}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Fireball extends GamePiece{
    // private TickStrategy tickFire;
    public Image fireball = Toolkit.getDefaultToolkit().getImage("Images/fireball1.png");

    public Fireball(int xPos, int yPos, TickSorter tickSort){
        super(xPos, yPos, Enums.GamePiece.Fireball, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes
    }
    public void render(Graphics graphics){
        graphics.drawImage(fireball, xPos,yPos,130, 80, null); //leaf is currently stagnant
        // graphics.setColor(Color.orange);
        // graphics.fillRect(xPos , yPos ,100, 70);
    }
    public Rectangle getBounds(){
        return new Rectangle(xPos, yPos, 100, 70);
        
    }
    public void tick(){
        //tick changes xPos
        // xPos+=xVel;
        // yPos+=yVel;
        // if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
        //     collision1=true;
        //     Broker.getBroker().event(Enums.Event.LostLife);
        // }
        tickSort.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Leaf extends GamePiece{
    //private TickStrategy tickLeaf;
    public Image leaf = Toolkit.getDefaultToolkit().getImage("Images/leaf.png");

    public Leaf(int xPos, int yPos, TickSorter tickSort){
        super(xPos, yPos, Enums.GamePiece.Leaf, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes
    }
    public void render(Graphics graphics){
        graphics.drawImage(leaf, xPos,yPos,80, 50, null); //leaf is currently stagnant
        // graphics.setColor(Color.green);
        // graphics.fillRect(xPos , yPos ,70, 40);

    }
    public Rectangle getBounds(){
        return new Rectangle(xPos, yPos, 70, 40);
    }
    public void tick(){
        //tick changes xPos
        // xPos+=xVel;
        // yPos+=yVel;
        // if(!collision1 && (getBounds().intersects(Dino.getDino().getBounds()))){
        //     collision1=true;
        //     Broker.getBroker().event(Enums.Event.AteLeaves);
        //     leaf = Toolkit.getDefaultToolkit().getImage("");
        // }
        tickSort.tick(this);
    }
    

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Cloud extends GamePiece{
    //private TickStrategy tickCloud;
    public Image cloud1 = Toolkit.getDefaultToolkit().getImage("Images/cloud1.png");
    public Image cloud2 = Toolkit.getDefaultToolkit().getImage("Images/cloud2.png");
    //TODO: randomize positions
    public Cloud(int xPos, int yPos, TickSorter tickSort) {
        super(xPos, yPos, Enums.GamePiece.Cloud, tickSort);
        this.tickSort = tickSort;
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

    public void tick(){
        tickSort.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class SmokeCloud extends GamePiece{
    //private TickStrategy tickSmoke;
    public Image SmokeCloud1 = Toolkit.getDefaultToolkit().getImage("Images/SmokeCloud.png");
    public Image SmokeCloud2 = Toolkit.getDefaultToolkit().getImage("Images/smokeCloud1.png");

    //TODO: randomize positions
    public SmokeCloud(int xPos, int yPos, TickSorter tickSort) {
        super(xPos, yPos, Enums.GamePiece.SmokeCloud, tickSort);
        this.tickSort = tickSort;
        this.xPos= xPos;
        this.yPos=yPos;
    }

    public void render(Graphics graphics) {
        int xtemp = xPos + 120;
        int ytemp = yPos - 70;
        graphics.drawImage(SmokeCloud1, xPos,yPos,150, 70, null);
        graphics.drawImage(SmokeCloud2, xtemp ,ytemp ,150, 70, null);
    }
    public void tick(){
        tickSort.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

class Dino extends GamePiece {

    public boolean isJumping;
    public static boolean isStand;
    public boolean isDucking;
    public static boolean isRunning;
    public int xTemp;
    static TickSorter tickSort;
    static {
        // Create a new TickSorter object and set its strategy to TickStratDino
        tickSort = new TickSorter();
        tickSort.setStrategy(new TickDino());
    }
    
    private static final Dino singleDino = new Dino(tickSort);

    //images
    public Image gif = Toolkit.getDefaultToolkit().getImage("Images/runner.gif");
    public Image dinoStop = Toolkit.getDefaultToolkit().getImage("Images/dinoT1.png");
    public Image dinoDuck = Toolkit.getDefaultToolkit().getImage("Images/duck.png");

    public Rectangle bounds;

    private Dino(TickSorter tickSort) {
        super(40, 335, Enums.GamePiece.Dino, tickSort);
        isJumping = false;
        isStand = true;
        isDucking = false;
        isRunning = false;
        //xTemp = xPos - 58; //this is for second dino image to be in same position as gif

    }

    public static Dino getDino() {
        return singleDino;
    }

    public void render(Graphics graphics) {

        //graphics.drawImage(dinoImage, xPos,yPos,imagewidth, imageheight,null);
        if (isDucking == true) { //down arrow
            graphics.drawImage(dinoDuck, xPos, yPos, 100, 100, null); //correct dino coordinates to get him on the ground
            // graphics.setColor(Color.red);
            // graphics.fillRect(xPos, yPos + 50, 100, 50);
        } else if (isRunning == true) { //space bar
            //x is the width, y is height
            graphics.drawImage(gif, xPos, yPos, 100, 100, null); //correct dino coordinates to get him on the ground
            // graphics.setColor(Color.red);
            // graphics.fillRect(xPos + 15, yPos+ 5, 80, 90);
        } else { //base case (standing)
            graphics.drawImage(dinoStop, xPos - 58, yPos - 50, 280, 180, null); //correct dino coordinates to get him on the ground
            // graphics.setColor(Color.red);
            // graphics.fillRect(xPos+ 15 , yPos + 5 , 80, 90);
        }


    }

    public Rectangle getBounds() {
        if (isDucking == true) {
            return new Rectangle(xPos, yPos + 50, 100, 50);
        } else if (isRunning == true) {
            return new Rectangle(xPos+ 15 , yPos + 5 , 80, 90);
        } else { //if he is standing the bounds are different
            return new Rectangle(xPos+ 15 , yPos + 5 , 80, 90);
        }

    }

    public void tick() {
        //this if is responsible for bringing dino back to ground after jumping
        //we use timer for jumping bc we dont want jump method to be controlled and consistent
        // if (isJumping == true) {
        //     Timer timer = new Timer();
        //     timer.schedule(new TimerTask() {
        //         @Override
        //         public void run() {
        //             yPos = 335;
        //             isJumping = false;
        //         }
        //     }, 700); //this num is the ms delay
        // }
        // xPos += xVel;
        // yPos += yVel;
        //tickDino.tick(this);
        tickSort.tick(this);

    }

    public void jump() {
        isJumping = true; //set our bool to true
        yPos = 160; //change the yPos so he jumps on the screen

    }

    public void duck() {
        isDucking = true;
    }

    public void stopDucking() {
        isDucking = false;
    }

    public void stand() {
        isRunning = false; //stop running
        isStand = true; //start standing
    }

    public void isRunning() { // new method to resume running
        isStand = false; //stop standing
        isRunning = true; //start running
    }

}