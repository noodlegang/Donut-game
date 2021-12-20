import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import java.awt.event.MouseEvent;

public class Game extends JPanel implements ActionListener{
    private long startTime;
    private final int frogSizeX = 100; //ширина лягушки-мишени
    private final int rectSizeX = 100;
    private final int frogSizeY = 30; //длина лягушки-мишени
    private final int rectSizeY = 30;
    private int expX;
    private int expY;
    private int score = 0;
    private int frogs = 3; //кол-во всех лягушек
    private int milisec = 300;
    private BufferedImage donut;
    private int[] frogX = new int[frogs];
    private int[] frogY = new int[frogs];
    private String playerName;
    private Timer timer;
    private boolean left = true;
    private boolean right = false;
    private boolean up = true;
    private boolean down = false;
    private boolean exp = false;
    private rect rect2 = new rect();
    private rect rect1 = new rect();
    private rect rect3 = new rect();
    private boolean don = false;
    private boolean inGame = true; /*находимся ли мы в игре сейчас или нет. Так как мы в игре с самого
    появления окна, по дефолту это значение true until мы не проиграем*/

    public Game() {
        this.addMouseListener(new mouseClicked());
        GUI menu = new GUI();
        menu.setVisible(true);
        menu.start.addActionListener(new startGameLoh());
        this.setBackground(Color.BLACK);
        try {
            donut = ImageIO.read(new File("res/donute.jpg"));
        } catch (IOException e){
            System.out.println("Image missing");
            e.printStackTrace();
        }
        initiateGame();
        playerName = menu.name.getText();

    }

    public static void main(String[] args) {
        new Game();
    }

    public void initiateGame() {
        for (int i=0; i<frogs; i++) {
            frogX[i] = new Random().nextInt(11)*frogSizeX;
            frogY[i] = new Random().nextInt(21)*frogSizeY;
        }
        startTime = System.currentTimeMillis();
        timer = new Timer(milisec, this);
        timer.start();
        Timer gameTime = new Timer(60*1000, new brrr());
        gameTime.start();
    }

    class brrr implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            inGame=false;
        }
    }

    class rect {
        int rectX = new Random().nextInt(11)*rectSizeX;
        int rectY = new Random().nextInt(11)*rectSizeY;
    }

    public void moveLeft() {
        if (left) {
            rect3.rectX -= 50;
        } else {
            rect3.rectX += 50;
        }
    }

    public void moveUp() {
        if (up) {
            rect2.rectY -= rectSizeY;
            rect1.rectY -= rectSizeY;
        } else {
            rect2.rectY += rectSizeY;
            rect1.rectY += rectSizeY;
        }
    }

    public void checkYBounds () {
        if (rect1.rectY==0||rect2.rectY==0) {
            up = false;
            down = true;
        } else if (rect1.rectY>=610||rect2.rectY>=610) {
            up=true;
            down=false;
        }
    }

    public void checkXBound () {
        if (rect3.rectX==0) {
            right = true;
            left = false;
        } else if (rect3.rectX>=1090) {
            right = false;
            left=true;
        }
    }



        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(donut, 450, 300, 286, 298,this);
        g.setColor(Color.RED);
        g.drawString("Score: " + String.valueOf(score), 500, 30);
        if (inGame) {
                int red = new Random().nextInt(255);
                int blue = new Random().nextInt(255);
                int green = new Random().nextInt(255);
                g.setColor(new Color(red, blue, green));
                g.fillRect(rect2.rectX, rect2.rectY, 50, 50);
                int re = new Random().nextInt(255);
                int bl = new Random().nextInt(255);
                int gr = new Random().nextInt(255);
                g.setColor(new Color(re, bl, gr));
                g.fillRect(rect1.rectX, rect1.rectY, 50, 50);
                int red3 = new Random().nextInt(255);
                int blue3 = new Random().nextInt(255);
                int gre = new Random().nextInt(255);
                g.setColor(new Color(red3, blue3, gre));
                g.fillRect(rect3.rectX, rect3.rectY, 50, 50);
                if (don) {
                    g.drawString("You have touched the donut", 450, 50);
                }
        }
    }

    public void actionPerformed(ActionEvent e) { //выполняется каждый раз когда тикает таймер
        if (inGame) {
            checkXBound();
            moveLeft();
            checkYBounds();
            moveUp();
            repaint();
            long endTime = System.currentTimeMillis();
            long measuredTime=endTime-startTime;
            if (measuredTime==(15*1000)) {
                startTime=endTime;
                milisec=milisec-50;
            }
        }
    }

    class mouseClicked extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            Point point = e.getPoint();
                if (point.x>=rect1.rectX&&point.x<=(rect1.rectX+50)) {
                    if (point.y>=rect1.rectY&&point.y<=(rect1.rectX+50)) {
                        expX= point.x-25;
                        expY= point.y-25;
                        score++;
                    }
                } if (point.x>=rect2.rectX&&point.x<=(rect2.rectX+50)) {
                if (point.y>=rect2.rectY&&point.y<=(rect2.rectX+50)) {
                    expX= point.x-25;
                    expY= point.y-25;
                    score++;
                }
            } if (point.x>=rect3.rectX&&point.x<=(rect3.rectX+50)) {
                if (point.y>=rect3.rectY&&point.y<=(rect3.rectX+50)) {
                    expX= point.x-25;
                    expY= point.y-25;
                    score++;
                }
            } else if (point.x>=450&&point.x<=(450+286)) {
                    if (point.y>=300&&point.y<=(300+298)) {
                        don=true;
                    }

            } else {
                    score--;
            }
        }
    }

    class startGameLoh implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLog window = new gameLog(Game.this);
            window.setVisible(true);
        }
    }
}
