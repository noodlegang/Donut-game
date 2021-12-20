import javax.swing.*;

public class gameLog extends JFrame {

    public gameLog (Game game) {
        super("2d shooter");
        this.setSize(1200, 680);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(game);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
