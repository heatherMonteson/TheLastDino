package src;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.security.PublicKey;

//OO Pattern: Eager installation of singleton
//Handles all the player stats: Level, timer, lives, score and activates the database to add a new player
public class Player implements Observer{

    private static final Player player =new Player();
    private String name;
    private int lives;
    private int score;
    private int level;
    private Image heart1;
    private Image heart2;
    private Image heart3;
    private final Image deadHeart = Toolkit.getDefaultToolkit().getImage("Images/deadHeart.png");

    private Player(){
        lives=3;
        score =0;
        name="";
        level=1;
        Image heart = Toolkit.getDefaultToolkit().getImage("Images/heart.png");
        heart1= heart;
        heart2= heart;
        heart3= heart;
        Broker.getBroker().register(this);
    }

    public static Player getPlayer(){
        return player;
    }

    public void render(Graphics g, int timer){

        //set all the background colors behind the score and lives
        Graphics2D graphics = (Graphics2D)g;
        graphics.setColor(Color.black);
        graphics.fillRect(480,15,320, 140);
        graphics.setColor(Color.yellow);
        graphics.fillRect(490,65,300, 80);

        //All Text
        textRender(graphics, Color.pink, String.valueOf(score),500, 55, 40); //score text display
        textRender(graphics,  Color.white,"Level: "+String.valueOf(level),25, 40, 20); //level text display
        textRender(graphics, Color.white, "Time: "+String.valueOf(GameController.levelLength-timer),25, 65, 20); //timer text display

        //Lives
        graphics.drawImage(heart1, 700,70,80, 70, null);
        graphics.drawImage(heart2, 600,70,80, 70, null);
        graphics.drawImage(heart3, 500,70,80, 70, null);
    }

    private void textRender(Graphics2D graphics, Color color, String text, int x, int y, int size){
        //rendering text in graphics:
        //http://www.java2s.com/Code/Java/2D-Graphics-GUI/Draw2DText.htm
        FontRenderContext frc = graphics.getFontRenderContext();
        Font fontSelection = new Font("Courier", Font.BOLD, size);
        TextLayout textGraphics = new TextLayout(text, fontSelection, frc);
        graphics.setColor(color);
        textGraphics.draw(graphics, x, y);
    }

    public void registerPlayer(String name) {
        this.name=name;
        DatabaseConnection db = DatabaseConnection.getDbSingleton();
        db.addPlayerToDB(name);
    }

    @Override
    public void update(Enums.Event event) {
        if(event==Enums.Event.AteLeaves)//increase score
            score+= GameController.level.getPoints();
        else if(event==Enums.Event.LostLife){ //when life lost switch out a heart for a dead heart
            lives-=1;
            if(lives==2)
                heart1=deadHeart;
            else if(lives==1)
                heart2=deadHeart;
            else if(lives==0)
                heart3=deadHeart;
            if(lives==0) //game ends if all lives were lost
                GameController.playerDied();
        }
        else if(event==Enums.Event.LevelCompleted)
            level+=1;
    }
}
