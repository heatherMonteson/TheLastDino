package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/*
 * GameWindow: creates the main game window that will contain the main game pieces and graphics
 *
 * Citation
 * https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 * https://docs.oracle.com/javase/7/docs/api/java/awt/Canvas.html
 * just used for documentation
 */

public class GameWindow extends Canvas {
    @Serial
    private static final long serialVersionUID = 6560049321439293750L;

    GameWindow(int width, int height,GameController game){
        super();
        JFrame  gameWindow;
        gameWindow = new JFrame("The Last Dino");
        //Window will exit when closed
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //don't allow for resizing, fewer cases to handle
        gameWindow.setResizable(false);
        //set sizes and position
        gameWindow.setSize(width, height);
//        gameWindow.setLocation(120, 60);
        //auto center
        gameWindow.setLocationRelativeTo(null);
        //run game controller to the window
        gameWindow.add(game);
        gameWindow.setVisible(true);
        game.start();
    }

}
