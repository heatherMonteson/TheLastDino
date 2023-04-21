package src;
/*
TODO general checklist:
 -All classes with tick and render
    -set behavior
 -DatabaseConnection:
    -add the update SQL statements to update values in the database
 -GamePiece:
    -All: graphics, tweaking tick/motion from key input
    -Obstacles/leaves:-Randomize positions and when they appear in the window
                      ** can do from either the factory or by passing in various values from the levels-->factory-->piece constructor, just update params
                      - Rectangle bounds for collisions
                      - Verify velocity/movement once everything is updated
    -Dino: -Code jumping (figured out up but it just jettisons off the window =] )
          - Rectangle bounds for collisions
    -Background pieces: Randomize positions (from either the factory or by passing in various values from the levels-->factory-->piece classes)
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

