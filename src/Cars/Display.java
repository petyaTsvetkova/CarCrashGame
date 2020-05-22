package Cars;

import javax.swing.*;

public class Display extends JFrame {

    public Display() {
        Road road = new Road();
        add(road);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800);
        setVisible(true);


    }

}
