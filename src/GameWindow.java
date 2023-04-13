package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/*
From Canvas documentation:
A Canvas component represents a blank rectangular area of the screen onto which the application
can draw or from which the application can trap input events from the user.
An application must subclass the Canvas class in order to get useful functionality such as creating a
custom component. The paint method must be overridden in order to perform custom graphics on the canvas.
https://docs.oracle.com/javase/7/docs/api/java/awt/Canvas.html

From JFrame documentation:
https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html

* */

public class GameWindow extends Canvas {
    @Serial
    private static final long serialVersionUID = 6560049321439293750L;

    GameWindow(int width, int height,GameController game){
        super();
        JFrame  gameWindow = new JFrame("The Last Dino");
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
