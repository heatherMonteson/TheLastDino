package src;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
/*
 * Player: Handles all the player stats and display: Level, timer, lives, score and activates the database to add a new player
 *
 * OO Pattern: Eager installation of singleton and Observer
 *
 */

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
        heart1= heart2= heart3= heart;
        //register for events
        Broker.getBroker().register(this);
    }

    public static Player getPlayer(){
        return player;
    }

    /*
     * render: display all the player elements
     * score, lives, time, level, score
     *
     * @param Graphics, int
     * @return nothing
     */
    public void render(Graphics g, int timer){
        //set all the background colors behind the score and lives
        Graphics2D graphics = (Graphics2D)g;
        graphics.setColor(Color.black);
        graphics.fillRect(480,15,320, 140);
        graphics.setColor(Color.yellow);
        graphics.fillRect(490,65,300, 80);

        //All Text
        Utility.textRender(graphics, Color.pink, String.valueOf(score),500, 55, 40); //score text display
        Utility.textRender(graphics,  Color.white,"Level: "+String.valueOf(level),25, 40, 20); //level text display
        Utility.textRender(graphics, Color.white, "Time: "+String.valueOf(GameController.levelLength-timer),25, 65, 20); //timer text display

        //Lives
        graphics.drawImage(heart1, 700,70,80, 70, null);
        graphics.drawImage(heart2, 600,70,80, 70, null);
        graphics.drawImage(heart3, 500,70,80, 70, null);
    }

    /*
     * registerPlayer: used to register a new player with the database. Game still runs with no database
     *
     * @param String
     * @return nothing
     */
    public void registerPlayer(String name) {
        this.name=name;
        DatabaseConnection db = DatabaseConnection.getDbSingleton();
        db.addPlayerToDB(name);

    }

    /*
     * update:get event notifications from the broker and update the player stats
     *
     * @param Enums.Event
     * @return nothing
     */
    @Override
    public void update(Enums.Event event) {
        if(event==Enums.Event.AteLeaves)//increase score
            score+= GameController.level.getPoints();
        else if(event==Enums.Event.LostLife){ //when life lost switch out a heart for a dead heart
            if(lives>0)
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
        else if(event==Enums.Event.LevelCompleted && level<3)
            level+=1;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int GetLevel() {
        return level;
    }
}
