# The Last Dino
**Creators**<br />
Priya Patel and Heather Monteson

**About**<br />
This is a 2D game written with Java 19+ called The Last Dino. The game follows a friendly dinosaur through 
various prehistoric stages from living a happy life, to when the meteor hits, then into the ice age
where they finally, at the end, find another surviving dinosaur. Once you start the first of three levels
starts of his life while avoiding dangerous obstacles like bushes, fireballs, icicles, and snowballs. You also 
can collect points as you play by eating leaves that increase in value as you move through every level. 

**Video Demo**<br />
https://vimeo.com/823530963?share=copy
_note: class names for TickSorter, TickStratagy, TickCloud, TickDino, TickGamePiece and TickLeaves names were changed to MovementSorter, Movement, NoCollision, DinoMove, CollisionPoints, CollisionLoseLife to represent the behaviors_

**Version**<br />
Java 20 and 11

**Database**<br />
Game can run with no connection however to run with connection to MySQL 8+ database set up: <br />
https://www.youtube.com/watch?v=e8g9eNnFpHQ
1) Create schema in local database 
2) Run create table statement: <br />
   CREATE TABLE `player_info` ( <br />
   `player_id` int NOT NULL AUTO_INCREMENT, <br />
   `name` varchar(20) NOT NULL, <br />
   `score` int NOT NULL DEFAULT '0', <br />
   `lives` int NOT NULL DEFAULT '3', <br />
   `level` int NOT NULL DEFAULT '0', <br />
   PRIMARY KEY (`player_id`) <br />
   ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; <br />

3) Change password and username variables found in the DatabaseConnection class to your local MySQL password and username 

**Object Oriented Patterns**<br />
- Singleton (Dino, Broker, Player, DatabaseConnection)
- Factory (GamePieceFactory, Create GamePiece _factory accessed by levels and pieces stored in handler_)
- Strategy (Movement, CollisionLifeLost, CollisionPoints, NoCollision, DinoMove)
- Observer (Broker _subject_, Player _observer_, DatabaseConnection _observer_)

**Running Program From Terminal in VS code for Java version 20**<br />
1) javac src/*.java     
2) Java -cp . src.Main     

Game was also able to be run from the main class using Intellej with Java version 11
