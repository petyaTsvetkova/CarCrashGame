package Cars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Road extends JPanel implements ActionListener, KeyListener {
    private int space;
    public final static int CAR_WIDTH = 50;
    public final static int CAR_HEIGHT = 100;
    private int speed;
    public final static int PANEL_WIDTH = 1000;
    public final static int PANEL_HEIGHT = 800;
    private ArrayList<Rectangle> oppositeCars;
    private Rectangle car;

    public Rectangle getCar() {
        return car;
    }

    public ArrayList<Rectangle> getOppositeCars() {
        return oppositeCars;
    }

    private Random random;
    boolean gameIsActive = true;
    GameObserver gameObserver = new GameObserver();
    Timer t;

    public Road() {
        gameObserver.setStart(System.currentTimeMillis());
        t = new Timer(1, this);
        random = new Random();
        oppositeCars = new ArrayList<>();
        car = new Rectangle(PANEL_WIDTH / 2 - 90, PANEL_HEIGHT - 200, CAR_WIDTH, CAR_HEIGHT);
        space = 300;
        speed = 2;
        addKeyListener(this);
        setFocusable(true);
        addOppositeCars(true);
        addOppositeCars(true);
        t.start();
    }

    public void addOppositeCars(boolean hasCarAppeared) {
        int positionX = random.nextInt(5) % 4;
        int x = 500;
        if (positionX == 0) {
            x = PANEL_WIDTH / 2 - 150;
        } else if (positionX == 1) {
            x = PANEL_WIDTH / 2 - 50;
        } else if (positionX == 2) {
            x = PANEL_WIDTH / 2 + 50;
        } else if (positionX == 3) {
            x = PANEL_WIDTH / 2 + 150;
        }
        if (hasCarAppeared) {
            oppositeCars.add(new Rectangle(x, 50 - (oppositeCars.size() * space), CAR_WIDTH, CAR_HEIGHT));
        } else {
            oppositeCars.add(new Rectangle(x, oppositeCars.get(oppositeCars.size() - 1).y - 400, CAR_WIDTH, CAR_HEIGHT));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.green);
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(PANEL_WIDTH / 2 - 200, 0, 400, PANEL_HEIGHT);
        g.setColor(Color.white);
        g.drawLine(PANEL_WIDTH / 2, 0, PANEL_WIDTH / 2, PANEL_HEIGHT);
        g.setColor(Color.magenta);
        drawImage(g, getCar(), "src\\Images\\myCar.JPG");
        for (Rectangle rect : getOppositeCars()) {
            drawImage(g, rect, "src\\Images\\otherCar.JPG");
        }
    }

    public void drawImage(Graphics g, Rectangle rec, String path) {
        Graphics2D graph = (Graphics2D) g;
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        graph.drawImage(image, rec.x, rec.y, CAR_WIDTH, CAR_HEIGHT, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
        for (Rectangle oppositeCar : oppositeCars) { //moving the cars
            rect = oppositeCar;
            rect.y += speed;
        }
        for (Rectangle r : oppositeCars) { // finishing the game
            if (r.intersects(car)) {
                this.gameIsActive = false;
                gameObserver.setFinish(System.currentTimeMillis());
            }
        }

        for (int i = 0; i < oppositeCars.size(); i++) { // remove skipped opposite cars
            rect = oppositeCars.get(i);
            if (rect.y + rect.height > PANEL_HEIGHT) {
                oppositeCars.remove(rect);
                gameObserver.setScoreListener(gameObserver.getScoreListener() + 1);
                addOppositeCars(false);
            }

        }
        if (gameIsActive) { // final end message
            repaint();
        } else gameObserver.endGameMessage();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        checkKey(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        checkKey(key);
    }

    public void checkKey(int key) {
        Controls controls = new Controls();
        switch (key) {
            case KeyEvent.VK_UP:
                controls.moveUp(this);
                break;
            case KeyEvent.VK_DOWN:
                controls.moveDown(this);
                break;
            case KeyEvent.VK_LEFT:
                controls.moveLeft(this);
                break;
            case KeyEvent.VK_RIGHT:
                controls.moveRight(this);
                break;
            default:
                break;
        }
    }
}
