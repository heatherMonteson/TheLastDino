package src;

import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Top3Parser: gets the top 3 players from the database connection and parses their values to return
 * individual stats by player (player/score/lives/level). Gets data on instantiation
 *
 */
public class Top3Parser {


    private ArrayList<String> players,scores,lives,level;
    private boolean ableToParse;

    public Top3Parser(){
        players=new ArrayList<>();
        scores=new ArrayList<>();
        lives=new ArrayList<>();
        level=new ArrayList<>();
        try {
            ResultSet top3= DatabaseConnection.getDbSingleton().getTop3Players();
            while (top3.next()){

                players.add(top3.getString(2));
                scores.add(top3.getString(3));
                lives.add(top3.getString(4));
                level.add(top3.getString(5));
            }
            ableToParse=true;

        }catch (Exception e){
            ableToParse=false;
        }
    }


    public boolean isAbleToParse(){
        return ableToParse;
    }

    /////Names//////
    public String getPlayer1Name(){
        try{
            return players.get(0);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer2Name(){
        try{
            return players.get(1);
        }
        catch (Exception e){
            return "";
        }

    }
    public String getPlayer3Name(){
        try{
            return players.get(2);
        }
        catch (Exception e){
            return "";
        }
    }
    /////scores//////
    public String getPlayer1Score(){
        try{
            return scores.get(0);
        }
        catch (Exception e){
            return "";
        }

    }
    public String getPlayer2Score(){
        try{
            return scores.get(1);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer3Score(){
        try{
            return scores.get(2);
        }
        catch (Exception e){
            return "";
        }
    }
    /////Lives//////
    public String getPlayer1Lives(){
        try{
            return lives.get(0);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer2Lives(){
        try{
            return lives.get(1);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer3Lives(){
        try{
            return lives.get(2);
        }
        catch (Exception e){
            return "";
        }
    }
    /////levels//////
    public String getPlayer1Level(){
        try{
            return level.get(0);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer2Level(){
        try{
            return level.get(1);
        }
        catch (Exception e){
            return "";
        }
    }
    public String getPlayer3Level(){
        try{
            return level.get(2);
        }
        catch (Exception e){
            return "";
        }
    }

}
