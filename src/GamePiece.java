package src;

import java.awt.*;

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
        xPos+=xVel;
        yPos+=yVel;
    }

    public Enums.GamePiece getType(){
        return type;
    }
    public Rectangle getBounds(){return bounds;}
    //piece location/velocity getters (just setting now to play with input)
    public void setXpos(int x){
        this.xPos=x;
    }
    public void setYpos(int y){
        this.yPos=y;
    }
    public void setXvel(int x){
        this.xVel=x;
    }
    public void setYvel(int y){
        this.yVel=y;
    }
    //piece location/vel getters
    public int getXpos(){
        return xPos;
    }
    public int getYpos(){
        return yPos;
    }
    public int getXvel(){
        return xVel;
    }
    public int getYvel(){
        return yVel;
    }


}
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Bush extends GamePiece{
    public Bush(){
        //Todo: change starting GameController.width to width, just testing
        super(GameController.width-50, GameController.height/2, Enums.GamePiece.Bush);
    }
    public void render(Graphics graphics){
        //todo: remove test code after graphics are made
        graphics.setColor(Color.green);
        graphics.fillRect(xPos,yPos,32,32);
    }

}

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
class Icicle extends GamePiece{
    public Icicle(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Icicle);
    }

    public void render(Graphics graphics){
        //todo: remove test code after graphics are made
        graphics.setColor(Color.blue);
        graphics.fillRect(xPos,yPos,32,32);
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

    private Dino(){
        super(5, GameController.height/2, Enums.GamePiece.Dino);
        isJumping=false;
    }
    public static Dino getDino(){
        return singleDino;
    }

    public void render(Graphics graphics){
        //todo: remove test code after graphics are made
        graphics.setColor(Color.white);
        graphics.fillRect(xPos,yPos,32,32);

        
        //graphics.drawImage(dinoImage, xPos,yPos,imagewidth, imageheight,null);

    }
    public void tick(){
        xPos+=xVel;
        yPos+=yVel;

        //check collisions with the dino
        for(GamePiece piece: GamePieceHandler.gamePieces){
            //collision with leaf
            if(piece.getType()== Enums.GamePiece.Leaf && piece.getBounds() == this.bounds)
                Broker.getBroker().event(Enums.Event.AteLeaves);
                //collision with obstacle
            else if(piece.getType()!= Enums.GamePiece.Cloud && piece.getType()!= Enums.GamePiece.SmokeCloud && piece.getBounds() == this.bounds)
                Broker.getBroker().event(Enums.Event.LostLife);
        }
    }

    public void resetDinoPosition(){
        xPos=5;
        yPos= GameController.height/2;
    }

    public void jump(){

    }

    public void duck() {
    }

    public void stand() {
    }
}