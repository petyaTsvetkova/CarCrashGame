package Cars;

public class Controls{
    private int moveStep = 20;

    public void moveUp(Road road) {
        if (road.getCar().y - moveStep > 0) {
            road.getCar().y -= moveStep;
            if (road.getCar().y < (Road.PANEL_HEIGHT / 2 - Road.CAR_HEIGHT / 2)) {
                road.getCar().y = Road.PANEL_HEIGHT / 2 - Road.CAR_HEIGHT / 2;
            }
        }
    }

    public void moveDown(Road road) {
        if (road.getCar().y + moveStep + road.getCar().height < Road.PANEL_HEIGHT - 1) {
            road.getCar().y += moveStep;
            if (road.getCar().y > Road.PANEL_HEIGHT - 2 * Road.CAR_HEIGHT) {
                road.getCar().y = Road.PANEL_HEIGHT - 2 * Road.CAR_HEIGHT;
            }
        }
    }

    public void moveLeft(Road road) {
        if (road.getCar().x - moveStep > Road.PANEL_WIDTH / 2 - 200) {
           road.getCar().x -= moveStep;
            if (road.getCar().x < Road.PANEL_WIDTH / 2 - 200) {
                road.getCar().x = Road.PANEL_WIDTH / 2 - 200;
            }
        }
    }

    public void moveRight(Road road) {
        if (road.getCar().x + moveStep < Road.PANEL_WIDTH / 2 + 160) {
            road.getCar().x += moveStep;
            if (road.getCar().x > Road.PANEL_WIDTH / 2 + 160) {
                road.getCar().x = Road.PANEL_WIDTH / 2 + 160;
            }
        }
    }
}
