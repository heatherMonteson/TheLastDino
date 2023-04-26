package src;
/*
TODO general checklist:
 -Need another pattern: All classes with render
    -can set behavior/strategy pattern??
 -GamePiece:
    -Obstacles/leaves:
          - Rectangle bounds for collisions
          -finish adding/rendering all level pieces
    -Dino:
          - Rectangle bounds for collisions
          - code ducking
 */
public class Main {
    public static void main(String args[]){
        new GameController();
    }
}

