package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Objects;
//All popup windows to display in game
public abstract class PopUp extends JFrame {
    @Serial
    private static final long serialVersionUID = 6285365765445429917L;
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
        while (Objects.equals(name, "")) {
            name = JOptionPane.showInputDialog("Player Name");
            System.out.println("Player Name:" + name);
        }

        Player player = Player.getPlayer();
        player.registerPlayer(name);
    }

}

    class EndOfGame extends PopUp {
        @Serial
        private static final long serialVersionUID = -4241358101917272246L;

        public EndOfGame() {
        }

        @Override
        public void pop() {

        }
    }


