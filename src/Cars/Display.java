package Cars;

import javax.swing.*;

public class Display extends JFrame {

    public Display() {
        Road road = new Road();
        add(road);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Road.PANEL_WIDTH, Road.PANEL_HEIGHT);
        setVisible(true);
    }

}
