package src;
/*
*Movement: handles all the tick behaviors for the game pieces so they can update movement/position/collisions etc.
*/
public interface Movement {
    void tick(GamePiece piece);
}


