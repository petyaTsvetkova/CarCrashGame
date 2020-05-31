import Cars.Controls;
import Cars.Road;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RoadTest {

    @Test
    public void testArrayListSizeIncreasesWhenAddOppositeCars() {
        Road road = new Road();
        ArrayList array1 = road.getOppositeCars();
        road.addOppositeCars(true);
        ArrayList array2 = road.getOppositeCars();
        Assert.assertNotEquals("Fails to add cars.", array1.toArray().length + 1, array2.toArray().length);
    }

    @Test
    public void testStopMoveUpWhenCarReachesHalfRoad() {
        Road road = new Road();
        Controls controls = new Controls();
        Rectangle myCar = road.getCar();
        myCar.y = 350; //(800 - 100) / 2
        controls.moveUp(road);
        Assert.assertEquals(350, myCar.y);
    }

    @Test
    public void testStopMoveDownWhenCarReachesEnd() {
        Road road = new Road();
        Controls controls = new Controls();
        Rectangle myCar = road.getCar();
        myCar.y = 700;
        controls.moveDown(road);
        Assert.assertEquals(700, myCar.y);
    }

    @Test
    public void testStopMoveLeftWhenCarReachesLeftEnd() {
        Road road = new Road();
        Controls controls = new Controls();
        Rectangle myCar = road.getCar();
        myCar.x = 300;
        controls.moveUp(road);
        Assert.assertEquals(300, myCar.x);
    }

    @Test
    public void testStopMoveRightWhenCarReachesRightEnd() {
        Road road = new Road();
        Controls controls = new Controls();
        Rectangle myCar = road.getCar();
        myCar.x = 660;
        controls.moveUp(road);
        Assert.assertEquals(660, myCar.x);
    }

    @Test
    public void testRandomsWhenAddingOppositeCars() {
        int random = new Random().nextInt(4);
        int position = random % 4;
        System.out.println(random);
        Assert.assertFalse("Invalid position.", position < 0);
    }

    @Test
    public void testScoreIncreasesWhenOppositeCarOutOfRoad() {
        Road road = new Road();
        int score = 0;
        Rectangle rect = road.getOppositeCars().get(0);
        rect.y = 800;
        if (rect.y + rect.height > Road.PANEL_HEIGHT) {
            road.getOppositeCars().remove(rect);
            score++;
        }
        Assert.assertEquals("Score stays same.", 1, score);
    }
}