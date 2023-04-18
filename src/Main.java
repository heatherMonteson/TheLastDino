package src;
/*
TODO List by class (general checklist):
 -DatabaseConnection:
    -add the update SQL statements to update values in the database
 -GameController:
    -Handel stopping game if player dies
    -Switch levels (currently handled in the stop method of GC but need to determine how/when to call)
    -Pull out the buffer render currently set in the render method and move updates to the render methods in the levels
 -Level/child classes:
    - render methods for graphics
 -GamePiece:
    -All: graphics, tweaking tick/motion from key input
    -Obstacles/leaves:-Randomize positions and when they appear in the window
                      ** can do from either the factory or by passing in various values from the levels-->factory-->piece constructor, just update params
                      - Rectangle bounds for collisions
                      - Verify velocity/movement once everything is updated
    -Dino: -Code jumping (figured out up but it just jettisons off the window =] )
          - Rectangle bounds for collisions
    -Background pieces: Randomize positions (from either the factory or by passing in various values from the levels-->factory-->piece classes)
 -Player: create player score and lives lost graphics
 -EndOFGame:
        -Create Display
        -Get player info from player to display
        -Get top 3 players info from database to display (query set and working)
        -Make is so that the window will still display player stats even if there is not a database connection (there is a bool value in the DB connection to check this)
 */
public class Main {
    public static void main(String args[]){
        new GameController();
    }
}
//javac src/*.java   
//Java -cp . src.Main
