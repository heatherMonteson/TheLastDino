package src;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/*
 *GamePiece: all moving game pieces
 *
 */

public abstract class GamePiece {
    protected int xPos, yPos;
    protected  int xVel, yVel;
    protected Enums.GamePiece type;
    protected Rectangle bounds;
    public boolean collision1;
    protected MovementSorter movement;
    

    //public static Rectangle bushRect;
    public GamePiece(int xPos, int yPos, Enums.GamePiece type, MovementSorter movement)
    {
        this.xPos= xPos;
        this.yPos=yPos;
        this.type = type;
        this.movement = movement;
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

    //bounds used for collision
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

    private final Image bush = Toolkit.getDefaultToolkit().getImage("Images/bush1.png");

    public Bush(int xPos, int yPos, MovementSorter movement){
        super(xPos, yPos, Enums.GamePiece.Bush, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
        collision1 = false;
        bounds=new Rectangle(xPos+10, yPos, 80, 50);

    }

    public void render(Graphics graphics){
        graphics.drawImage(bush, xPos,yPos,100, 60, null);
    }

    public void tick(){
        movement.tick(this);
        bounds.setBounds(xPos+10, yPos, 80, 50);
    }
}


//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Icicle extends GamePiece{

    private final Image icicle = Toolkit.getDefaultToolkit().getImage("Images/icicle2.png");

    public Icicle(int xPos, int yPos, MovementSorter movement){
        super(xPos, yPos, Enums.GamePiece.Icicle, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
        this.bounds=new Rectangle(xPos, yPos, 80, 50);
    }

    public void render(Graphics graphics){
        graphics.drawImage(icicle, xPos,yPos,90, 60, null);
    }

    public void tick(){
        movement.tick(this);
        bounds.setBounds(xPos, yPos, 80, 50);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Snowball extends GamePiece{
    private final Image snowball = Toolkit.getDefaultToolkit().getImage("Images/snowBall1.png");

    public Snowball(int xPos, int yPos,MovementSorter movement){
        super(xPos, yPos, Enums.GamePiece.Snowball, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4;
        bounds=new Rectangle(xPos + 10, yPos, 100, 70);
    }
    public void render(Graphics graphics){
        graphics.drawImage(snowball, xPos, yPos,150, 75, null);
    }

    public void tick(){
        movement.tick(this);
        bounds.setBounds(xPos + 10, yPos, 100, 70);
    }
}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Fireball extends GamePiece{

    private final Image fireball = Toolkit.getDefaultToolkit().getImage("Images/fireball1.png");

    public Fireball(int xPos, int yPos, MovementSorter movement){
        super(xPos, yPos, Enums.GamePiece.Fireball, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4;
        bounds=new Rectangle(xPos, yPos, 100, 70);
    }
    public void render(Graphics graphics){
        graphics.drawImage(fireball, xPos,yPos,130, 80, null); //leaf is currently stagnant
    }

    public void tick(){
        movement.tick(this);
        bounds.setBounds(xPos, yPos, 100, 70);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Leaf extends GamePiece{
    //private TickStrategy tickLeaf;
    private final Image leaf = Toolkit.getDefaultToolkit().getImage("Images/leaf.png");

    public Leaf(int xPos, int yPos, MovementSorter movement){
        super(xPos, yPos, Enums.GamePiece.Leaf, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
        this.xVel = -4; //if this is set to -5, it moves at same rate as bushes
        this.bounds= new Rectangle(xPos, yPos, 70, 40);

    }
    public void render(Graphics graphics){
        graphics.drawImage(leaf, xPos,yPos,80, 50, null); //leaf is currently stagnant
    }

    public void tick(){
        movement.tick(this);
        bounds.setBounds(xPos, yPos, 70, 40);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Cloud extends GamePiece{

    private final Image cloud1 = Toolkit.getDefaultToolkit().getImage("Images/cloud1.png");
    private final Image cloud2 = Toolkit.getDefaultToolkit().getImage("Images/cloud2.png");

    public Cloud(int xPos, int yPos, MovementSorter movement) {
        super(xPos, yPos, Enums.GamePiece.Cloud, movement);
        this.movement = movement;
        this.xPos= xPos;
        this.yPos=yPos;
    }

    public void render(Graphics graphics) {
        int xtemp = xPos + 120;
        int ytemp = yPos - 75;
        graphics.drawImage(cloud1, xPos,yPos,150, 70, null);
        graphics.drawImage(cloud2, xtemp ,ytemp ,150, 70, null);
    }

    public void tick(){
        movement.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class SmokeCloud extends GamePiece{

    private final Image SmokeCloud1 = Toolkit.getDefaultToolkit().getImage("Images/SmokeCloud.png");
    private final Image SmokeCloud2 = Toolkit.getDefaultToolkit().getImage("Images/smokeCloud1.png");

    public SmokeCloud(int xPos, int yPos, MovementSorter movement) {
        super(xPos, yPos, Enums.GamePiece.SmokeCloud, movement);
        this.movement = movement;
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
        movement.tick(this);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

class Dino extends GamePiece {

    public boolean isJumping;
    public static boolean isStand;
    public boolean isDucking;
    public static boolean isRunning;

    static MovementSorter movement;
    static {
        // Create a new MovementSorter object and set its strategy to TickStratDino
        movement = new MovementSorter();
        movement.setStrategy(new DinoMove());
    }
    
    private static final Dino singleDino = new Dino(movement);

    //images
    public Image gif = Toolkit.getDefaultToolkit().getImage("Images/runner.gif");
    public Image dinoStop = Toolkit.getDefaultToolkit().getImage("Images/dinoT1.png");
    public Image dinoDuck = Toolkit.getDefaultToolkit().getImage("Images/duck.png");

    private Dino(MovementSorter movement) {
        super(40, 335, Enums.GamePiece.Dino, movement);
        isJumping = false;
        isStand = true;
        isDucking = false;
        isRunning = false;
    }

    public static Dino getDino() {
        return singleDino;
    }

    public void render(Graphics graphics) {

        if (isDucking) { //down arrow==duck
            graphics.drawImage(dinoDuck, xPos, yPos, 100, 100, null); //correct dino coordinates to get him on the ground
        } else if (isRunning) { //space bar ==run
            //x is the width, y is height
            graphics.drawImage(gif, xPos, yPos, 100, 100, null); //correct dino coordinates to get him on the ground
        } else { //base case (standing)
            graphics.drawImage(dinoStop, xPos - 58, yPos - 50, 280, 180, null); //correct dino coordinates to get him on the ground
        }
    }

    public Rectangle getBounds() {
        if (isDucking) {
            return new Rectangle(xPos, yPos + 50, 100, 50);
        } else if (isRunning) {
            return new Rectangle(xPos+ 15 , yPos + 5 , 80, 90);
        } else { //if he is standing the bounds are different
            return new Rectangle(xPos+ 15 , yPos + 5 , 80, 90);
        }

    }

    public void tick() {
        movement.tick(this);
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