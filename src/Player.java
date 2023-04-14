package src;

import javax.xml.crypto.Data;
import java.security.PublicKey;

//OO Pattern: Eager installation of singleton
public class Player implements Observer{

    private static final Player player =new Player();
    private String name;
    int lives;
    int score;

    private Player(){
        this.lives=3;
        this.score =0;
        this.name="";
    }

    public static Player getPlayer(){
        return player;
    }

    public void registerPlayer(String name) {
        this.name=name;
        DatabaseConnection db = DatabaseConnection.getDbSingleton();
        db.addPlayerToDB(name);
        Broker.getBroker().register(this);
    }

    public String getPlayerName(){
        return name;
    }

    @Override
    public void update(Enums.Event event) {
        if(event==Enums.Event.AteLeaves)
            score+= GameController.level.getPoints();
        else if(event==Enums.Event.LostLife)
            lives-=1;
        if(lives==0)
            GameController.playerDied();
    }
}
