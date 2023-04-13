package src;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;

import java.sql.*;

//Citation for creating the BD connection: https://www.youtube.com/watch?v=e8g9eNnFpHQ
public class DatabaseConnection implements Observer{

    private static final DatabaseConnection dbSingleton = new DatabaseConnection();
    private static boolean isConnected = false;
    private final String url="jdbc:mysql://localhost:3306/the_last_dino";
    private final String password = "<password>";
    private final String username = "root";
    private String playerName;
    private Connection connection;

    private DatabaseConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            isConnected=true;
        }
        catch (Exception e){
            System.out.println("error with database connection in adding a new player");
            System.out.println(e);
            isConnected=false;
        }
    }

    public static DatabaseConnection getDbSingleton() {
        return dbSingleton;
    }

    public void addPlayerToDB(String name){
        if(isConnected){
            try{
                PreparedStatement insert = connection.prepareStatement("INSERT INTO `player_info`(name) VALUES (?)");
                insert.setString(1, name);
                insert.executeUpdate();
                playerName=name;
            }
            catch (Exception e){
                System.out.println("error with database connection in adding a new player");
                System.out.println(e);
            }
        }
        else
            System.out.println("Not connected to a database, cannot add player");
    }

    public ResultSet getTop3Players(){
            try{
                Statement stmt = connection.createStatement();
                ResultSet res = stmt.executeQuery("SELECT * FROM `player_info` ORDER BY `score` DESC LIMIT 3");
                //test code to check results from query
//                while (res.next()){
//                    System.out.println(res.getString(1) + " "+res.getString(2));
//                }
                return res;
            }
            catch (Exception e){
                System.out.println("error with database connection in retrieving top 3 players");
                System.out.println(e);
            }
            return null;
    }



    @Override
    public void update(Enums.Event event) {
//        if(event==Enums.Event.AteLeaves){
//
//        }
//        else if(event==Enums.Event.LostLife){
//
//        }
    }


}


