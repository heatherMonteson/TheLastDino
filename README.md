# The Last Dino
**Creators**<br />
Priya Patel and Heather Monteson

**About**<br />
This is a 2D game written with Java 19+ called The Last Dino. The game follows a friendly dinosaur through 
various prehistoric stages from living a happy life, to when the meteor hits, then into the ice age
where they finally, at the end, find another surviving dinosaur. Once you start the first of three levels
starts of his life while avoiding dangerous obstacles like bushes, fireballs, icicles, and snowballs. You also 
can collect points as you play by eating leaves that increase in value as you move through every level. 

**Version**<br />
Java 20

**Libraries, Frameworks and Other Tools**
- Swing
- Awt
- JavaFx
- Thread
- Serial (requires Java 14+ for serial in build base)

**Database**<br />
Game can run with no connection however to run with connection to MySQL 8+ database set up: <br />
https://www.youtube.com/watch?v=e8g9eNnFpHQ
1) Create schema in database 
2) Run create table statement: <br />
   CREATE TABLE `player_info` ( <br />
   `player_id` int NOT NULL AUTO_INCREMENT, <br />
   `name` varchar(20) NOT NULL, <br />
   `score` int NOT NULL DEFAULT '0', <br />
   `lives` int NOT NULL DEFAULT '3', <br />
   `level` int NOT NULL DEFAULT '0', <br />
   PRIMARY KEY (`player_id`) <br />
   ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; <br />

3) Change the password and username in the database connection class to your mySQL database password
