import Cars.GameObserver;
import org.junit.Assert;
import org.junit.Test;

public class GameObserverTest {
    @Test
    public void testCheckWhenElapsedTimeIsZero (){
        GameObserver gameObserver = new GameObserver();
        double time = gameObserver.measureElapsedTime(0,0);
        Assert.assertEquals("Does not return expected value", "Time: 0.0 seconds.",
                "Time: " + time + " seconds.");
    }

    @Test
    public void testCheckWhenScoreIsZero (){
        GameObserver gameObserver = new GameObserver();
        gameObserver.setScoreListener(0);
        Assert.assertEquals("Does not return expected value", "Score: 0 points.",
                "Score: " + gameObserver.getScoreListener() + " points.");
    }
}
