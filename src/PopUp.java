package src;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

/*
 * PopUp: all game popup windows (not main window, see GameWindow class)
 */
public abstract class PopUp extends JFrame {
    @Serial
    private static final long serialVersionUID = 6285365765445429917L;

    /*
     * pop: activates the popup window
     *
     * @param nothing
     * @return nothing
     */
    public abstract void pop();

}

class Instructions extends PopUp{
    @Serial
    private static final long serialVersionUID = 1684904189252759222L;

    public Instructions() {
    }

    @Override
    public void pop() {
        JDialog window = new JDialog();
        window.setResizable(false);
        window.setSize(490, 490);
        window.setLocationRelativeTo(null);
        window.add(new JLabel(new ImageIcon("Images/Instructions.png")));
        window.setModal(true);
        window.setVisible(true);
    }
}

class PlayerSignUp extends PopUp {
    @Serial
    private static final long serialVersionUID = -3484377113140366613L;

    private String name;
    public PlayerSignUp() {
        name = "";
    }

    @Override
    public void pop() {
        //using premade pop up to get user text input
        while (Objects.equals(name, "")) {
            name = JOptionPane.showInputDialog("Player Name");
            System.out.println("Player Name:" + name);
        }

        //send to the player to create a new player
        Player player = Player.getPlayer();
        player.registerPlayer(name);
    }

}


