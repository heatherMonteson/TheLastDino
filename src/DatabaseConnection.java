package src;

import com.sun.source.tree.BreakTree;

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

    //TODO: change password and user name to match your local MySQL database if you want to make a connection
    private final String password = "Sillygoos123!";
    private final String username = "root";

    private Connection connection;
    private String playerId;

    //creates connection in constructor
    private DatabaseConnection(){

        //Citation for creating the DB connection:
        //https://www.youtube.com/watch?v=e8g9eNnFpHQ
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/the_last_dino";
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
                PreparedStatement insert = connection.prepareStatement("INSERT INTO `player_info`(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                insert.setString(1, name);//place the name in the ? for values
                insert.executeUpdate();
                ResultSet resultID = insert.getGeneratedKeys();//returns the player primary key/ID

                if (resultID.next()) {
                    playerId= String.valueOf(resultID.getInt(1));
                    System.out.println("Name: " +name+ " ID:" +playerId);
                }
            }
            catch (Exception e){
                System.out.println("error with database connection in adding a new player");
                System.out.println(e);
            }
        }

    }

    /*
     * getTop3Players: query's the database and returns the top 3 players with the highest scores
     *
     * @param nothing
     * @return ResultSet
     */
    public ResultSet getTop3Players() throws SQLException {

        ResultSet top3=null;
        if(isConnected){
            try{
                Statement stmt = connection.createStatement();
                top3 = stmt.executeQuery("SELECT * FROM `player_info` ORDER BY `score` DESC LIMIT 3");
            }
            catch (Exception e){
                System.out.println("error with database connection in retrieving top 3 players"+ e);
            }
        }

        return top3;
    }

    /*
     * update: updates the current players stats in the database as events occur during game play
     *
     * @param Enums.Event
     * @return noting
     */
    public void update(Enums.Event event) {
        if(isConnected){
            try{
                PreparedStatement update = null;

                //adding points to stored players score
                if(event==Enums.Event.AteLeaves){
                    String points=String.valueOf(GameController.level.getPoints()); //get the levels point value
                    update=connection.prepareStatement("UPDATE `player_info` SET `score` = `score` + (?) WHERE `player_id`=(?)");
                    update.setString(1, points); //insert the point value to the first ? index
                    update.setString(2, playerId);//insert the player ID set at signup value to the second ? index
                }
                //reducing lives stored for player, do not go under 0 lives
                else if(event==Enums.Event.LostLife){
                    update=connection.prepareStatement("UPDATE `player_info` SET `lives` = `lives` - 1 WHERE `player_id`=(?) AND `lives`>0");
                    update.setString(1, playerId);//insert the player ID
                }
                //increasing levels completed by player, do not go over 3
                else if(event==Enums.Event.LevelCompleted){
                    update=connection.prepareStatement("UPDATE `player_info` SET `level` = `level` + 1 WHERE `player_id`=(?) AND `level`<3");
                    update.setString(1, playerId);//insert the player ID
                }
                if(update!=null)
                    update.executeUpdate();
            }
            catch (Exception e){
                System.out.println("error with database connection in updating player stats"+ e);
            }
        }

    }


}


