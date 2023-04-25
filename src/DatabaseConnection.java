package src;

import java.sql.*;

/*
 * DatabaseConnection: Connecting to MySQL database, however, you do not need to connect, game will run regardless.
 * To work with database update your login info in the DatabaseConnection class. Change your username and password to
 * connect to local MYSQL database, table create statement for set up:

    CREATE TABLE player_info (
    player_id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    score int NOT NULL DEFAULT '0',
    lives int NOT NULL DEFAULT '3',
    level int NOT NULL DEFAULT '0',
    PRIMARY KEY (player_id)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 * OO pattern: Observer
 *
 * Citation: https://www.youtube.com/watch?v=e8g9eNnFpHQ
 * used to create MySQL connection
 */
public class DatabaseConnection implements Observer{

    private static final DatabaseConnection dbSingleton = new DatabaseConnection();
    private static boolean isConnected;
    private final String url="jdbc:mysql://localhost:3306/the_last_dino";
    private final String password = "root";
    private final String username = "root";
    private Connection connection;

    //creates connection in constructor
    private DatabaseConnection(){

        //Citation for creating the DB connection:
        //https://www.youtube.com/watch?v=e8g9eNnFpHQ
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            isConnected=true;
            Broker.getBroker().register(this);//register with broker
            System.out.println("Connected to database");
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

    /*
     * addPlayerToDB: if connected to database will insert a new player into the database with name = "name" param
     *
     * @param string
     * @return nothing
     */
    public void addPlayerToDB(String name){
        if(isConnected){
            try{
                PreparedStatement insert = connection.prepareStatement("INSERT INTO `player_info`(name) VALUES (?)");
                insert.setString(1, name);
                insert.executeUpdate();
            }
            catch (Exception e){
                System.out.println("error with database connection in adding a new player");
                System.out.println(e);
            }
        }
        else
            System.out.println("Not connected to a database, cannot add player");
    }

    /*
     * getTop3Players: query's the database and returns the top 3 players with the highest scores
     *
     * @param nothing
     * @return ResultSet
     */
    public ResultSet getTop3Players(){
            try{
                Statement stmt = connection.createStatement();
                ResultSet res = stmt.executeQuery("SELECT * FROM `player_info` ORDER BY `score` DESC LIMIT 3");
                //just test code to check results from query
//                while (res.next()){
//                    System.out.println(res.getString(1) + " "+res.getString(2));
//                }
//                return res;
            }
            catch (Exception e){
                System.out.println("error with database connection in retrieving top 3 players"+ e);
            }
            return null;
    }

    //TODO: add the update SQL statements to update lives, score etc
    /*
     * update: updates the current players stats in the database as events occur
     *
     * @param Enums.Event
     * @return noting
     */
    public void update(Enums.Event event) {
//        if(event==Enums.Event.AteLeaves){
//
//        }
//        else if(event==Enums.Event.LostLife){
//
//        }
//         else if(event==Enums.Event.LevelCompleted){
//
//        }
    }


}


