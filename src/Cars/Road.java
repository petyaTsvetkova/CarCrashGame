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
    private int width = 50;
    private int height = 100;
    private int speed;
    public final static int PANEL_WIDTH = 1000;
    public final static int PANEL_HEIGHT = 800;
    private int moveStep = 20;
    private ArrayList<Rectangle> oppositeCars;
    private Rectangle car;
    private Random random;
    boolean gameIsActive = true;
    GameObserver gameObserver = new GameObserver();
    Timer t;

    public Road() {
        gameObserver.setStart(System.currentTimeMillis());
        t = new Timer(2, this);
        random = new Random();
        oppositeCars = new ArrayList<>();
        car = new Rectangle(PANEL_WIDTH / 2 - 90, PANEL_HEIGHT - 200, width, height);
        space = 300;
        speed = 2;
        addKeyListener(this);
        setFocusable(true);
        addOppositeCars(true);
        addOppositeCars(true);
        t.start();
    }

    public void addOppositeCars(boolean hasCarAppeared) {
        int positionX = random.nextInt() % 4;
        int x = 500;
        if (positionX == 0) {
            x = PANEL_WIDTH / 2 - 150;
        } else if (positionX == 1) {
            x = PANEL_WIDTH / 2 - 50;
        } else if (positionX == 2){
            x = PANEL_WIDTH / 2 + 50;
        } else if (positionX == 3){
            x = PANEL_WIDTH / 2 + 150;
        }
        if (hasCarAppeared) {
            oppositeCars.add(new Rectangle(x, 50 - (oppositeCars.size() * space), width, height));
        } else {
            oppositeCars.add(new Rectangle(x, oppositeCars.get(oppositeCars.size() - 1).y - 400, width, height));
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
        drawImage(g,car, "src\\Images\\myCar.JPG");
        for (Rectangle rect : oppositeCars) {
            drawImage(g, rect, "src\\Images\\otherCar.JPG");
        }
    }

    public void drawImage (Graphics g, Rectangle rec, String path) {
        Graphics2D graph = (Graphics2D) g;
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage();
        graph.drawImage(image, rec.x, rec.y, width, height, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
        for (Rectangle oppositeCar : oppositeCars) {
            rect = oppositeCar;
            rect.y += speed;
        }
        for (Rectangle r : oppositeCars) {
            if (r.intersects(car)) {
                this.gameIsActive = false;
                gameObserver.setFinish(System.currentTimeMillis());
            }
        }

        for (int i = 0; i < oppositeCars.size(); i++) {
            rect = oppositeCars.get(i);
            if (rect.y + rect.height > PANEL_HEIGHT) {
                oppositeCars.remove(rect);
                gameObserver.setScoreListener(gameObserver.getScoreListener() + 1);
                addOppositeCars(false);
            }

        }
        if (gameIsActive) {
            repaint();
        } else gameObserver.endGameMessage();
    }

    public void moveUp() {
        if (car.y - moveStep > 0) {
            car.y -= moveStep;
            if (car.y < (PANEL_HEIGHT / 2 - height/2)) {
                car.y = PANEL_HEIGHT/ 2 - height/2;
            }
        }
    }

    public void moveDown() {
        if (car.y + moveStep + car.height < PANEL_HEIGHT - 1) {
            car.y += moveStep;
            if (car.y > PANEL_HEIGHT - 2*height) {
                car.y = PANEL_HEIGHT - 2*height;
            }
        }
    }

    public void moveLeft() {
        if (car.x - moveStep > PANEL_WIDTH / 2 - 200) {
            car.x -= moveStep;
            if (car.x < PANEL_WIDTH / 2 - 210) {
                car.x = PANEL_WIDTH / 2 - 210;
            }
        }
    }

    public void moveRight() {
        if (car.x + moveStep < PANEL_WIDTH /2 + 160) {
            car.x += moveStep;
            if (car.x > PANEL_WIDTH /2 + 160) {
                car.x = PANEL_WIDTH /2 + 160;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
    public void checkKey (int key){
        switch (key) {
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            default:
                break;
        }
    }
}
