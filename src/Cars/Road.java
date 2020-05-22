package Cars;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, KeyListener {
    private int space;
    private int width = 50;
    private int height = 100;
    private int speed;
    private int WIDTH = 500;
    private int HEIGHT = 800;
    private int moveStep = 20;
    private ArrayList<Rectangle> oppositeCars;
    private Rectangle car;
    private Random random;
    Timer t;

    public Road() {
        t = new Timer(20, this);
        random = new Random();
        oppositeCars = new ArrayList<Rectangle>();
        car = new Rectangle(WIDTH / 2 - 90, HEIGHT - 150, width, height);
        space = 300;
        speed = 2;
        addKeyListener(this);
        setFocusable(true);
        addOppositeCars(true);
        addOppositeCars(true);
        t.start();
    }

    public void addOppositeCars(boolean hasCarAppeared) {
        int positionX = random.nextInt() % 2;
        int x = 0;
        int y = 0;
        int Width = width;
        int Height = height;
        if (positionX == 0) {
            x = WIDTH / 2 - 90;
        } else {
            x = WIDTH / 2 + 10;
        }
        if (hasCarAppeared) {
            oppositeCars.add(new Rectangle(x, y = 100 - (oppositeCars.size() * space), Width, Height));
        } else {
            oppositeCars.add(new Rectangle(x, oppositeCars.get(oppositeCars.size() - 1).y - 300, Width, Height));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.green);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(WIDTH / 2 - 100, 0, 200, HEIGHT);
        g.setColor(Color.red);
        g.fillRect(car.x, car.y, car.width, car.height);
        g.setColor(Color.blue);
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        g.setColor(Color.magenta);
        for (Rectangle rect : oppositeCars)
            g.fillRect(rect.x, rect.y, rect.width, rect.height);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
        for (int i = 0; i < oppositeCars.size(); i++) {
            rect = oppositeCars.get(i);
            rect.y += speed;
        }
        for (Rectangle r : oppositeCars) {
            if (r.intersects(car)) {
                car.y = r.y + height;
            }
        }

        for (int i = 0; i < oppositeCars.size(); i++) {
            rect = oppositeCars.get(i);
            if (rect.y + rect.height > HEIGHT) {
                oppositeCars.remove(rect);
                addOppositeCars(false);
            }

        }
        repaint();

    }

    public void moveUp() {
        if (car.y - moveStep > 0) {
            car.y -= moveStep;
            if (car.y < 0) {
                car.y = 0;
            }
        }
    }

    public void moveDown() {
        if (car.y + moveStep + car.height < HEIGHT - 1) {
            car.y += moveStep;
            if (car.y + car.height > HEIGHT) {
                car.y = HEIGHT - 20 - car.height;
            }
        }
    }

    public void moveLeft() {
        if (car.x - moveStep > WIDTH / 2 - 100) {
            car.x -= moveStep;
            if (car.x < WIDTH / 2 - 100) {
                car.x = WIDTH / 2 - 100;
            }
        }
    }

    public void moveRight() {
        if (car.x + moveStep < WIDTH/2 + 50) {
            car.x += moveStep;
            if (car.x > WIDTH/2 + 50) {
                car.x = WIDTH/2 + 50;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
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

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
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
