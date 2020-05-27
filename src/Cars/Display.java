package Cars;

import javax.swing.*;

public class Display extends JFrame {

    private String name = "Car game ver.1.0.";

    public Display() {
        Road road = new Road();
        add(road);
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Road.PANEL_WIDTH, Road.PANEL_HEIGHT);
        setVisible(true);
    }

}
