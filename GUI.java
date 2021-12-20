import javax.swing.*;

public class GUI extends JFrame{
    JLabel label = new JLabel("Please type your name: ");
    JTextField name = new JTextField("", 15);
    JButton start = new JButton("Start");
    SpringLayout sLayout = new SpringLayout();

    public GUI() {
        super("Menu");
        this.add(label);
        this.add(name);
        this.add(start);
        this.setLayout(sLayout);
        this.setSize(330, 150);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        sLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
        sLayout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, this);
        sLayout.putConstraint(SpringLayout.NORTH, name, 5, SpringLayout.NORTH, this);
        sLayout.putConstraint(SpringLayout.WEST, name, 5, SpringLayout.EAST, label);
        sLayout.putConstraint(SpringLayout.NORTH, start, 50, SpringLayout.SOUTH, label);
        sLayout.putConstraint(SpringLayout.WEST, start, 120, SpringLayout.WEST, this);

    }
}
