package src;

import java.awt.*;

public abstract class GamePiece {
    protected int xPos, yPos;
    protected  int xVel=0, yVel=0;
    protected Enums.GamePiece type;
    private Rectangle bounds;

    public GamePiece(int xPos, int yPos, Enums.GamePiece type)
    {
        this.xPos= xPos;
        this.yPos=yPos;
        this.type = type;
    }

    public abstract void render(Graphics graphics);
    public abstract void tick();

    public Enums.GamePiece getType(){
        return type;
    }

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

class Bush extends GamePiece{
    public Bush(){
        //Todo: change starting GameController.width to width, just testing
        super(GameController.width-50, GameController.height/2, Enums.GamePiece.Bush);
    }
    public void render(Graphics graphics){
        //todo: remove test code after graphics are made
        graphics.setColor(Color.red);
        graphics.fillRect(xPos,yPos,32,32);

    }    public void tick(){}
}

class Icicle extends GamePiece{
    public Icicle(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Icicle);
    }
    public void render(Graphics graphics){}
    public void tick(){}
}

class Snowball extends GamePiece{
    public Snowball(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Snowball);
    }
    public void render(Graphics graphics){}
    public void tick(){}
}

class Fireball extends GamePiece{
    public Fireball(){
        super(GameController.width, GameController.height/2,Enums.GamePiece.Fireball);
    }
    public void render(Graphics graphics){}
    public void tick(){}
}

class Leaf extends GamePiece{
    public Leaf(){
        super(GameController.width, GameController.height/2, Enums.GamePiece.Leaf);
    }
    public void render(Graphics graphics){}
    public void tick(){}
}

class Dino extends GamePiece{
    public Dino(){
        super(0, GameController.height/2, Enums.GamePiece.Dino);

    }
    public void render(Graphics graphics){
        //todo: remove test code after graphics are made
        graphics.setColor(Color.white);
        graphics.fillRect(xPos,yPos,32,32);

    }
    public void tick(){
        //todo: update object with player key input to move, remove test code
        xPos+=xVel;
        yPos+=yVel;
    }
}
