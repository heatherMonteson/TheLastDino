package src;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

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
        window.setSize(510, 410);
        window.setLocationRelativeTo(null);
        window.add(new JLabel(new ImageIcon("Images/Instructions.png")));

        //set modal prevents game from proceeding until the modal is closed
        window.setModal(true);

        window.setVisible(true);
    }
}

class PlayerSignUp extends PopUp{
    @Serial
    private static final long serialVersionUID = -3484377113140366613L;
    public PlayerSignUp() {
//        //window is the window window
//        JFrame  window = new JFrame("Instructions");
//
//        JButton button = new JButton("Let's Play!");
//
//        //panel is the grid where buttons/text entry etc. goes
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
//        panel.setLayout(new GridLayout(0,1));
//
//        window.add(panel, BorderLayout.CENTER);
//        window.setResizable(false);
//        //set sizes and position
//        window.setSize(510, 410);
////        window.setLocation(120, 60);
//        //auto center
//        window.setLocationRelativeTo(null);
//        window.add(new JLabel(new ImageIcon("Images/Instructions.png")));
//        window.setVisible(true);
    }

    @Override
    public void pop() {

    }
}

class EndOfGame extends PopUp{
    @Serial
    private static final long serialVersionUID = -4241358101917272246L;

    public EndOfGame() {
    }

    @Override
    public void pop() {

    }
}
