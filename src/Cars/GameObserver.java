package Cars;

public class GameObserver {

    private int scoreListener;

    private long start;
    private long finish;

    public void setStart(long start) {
        this.start = start;
    }

    public void setFinish(long finish) {
        this.finish = finish;
    }

    public int getScoreListener() {
        return scoreListener;
    }

    public void setScoreListener(int scoreListener) {
        this.scoreListener = scoreListener;
    }

    public GameObserver() {
    }

    public void endGameMessage(){
        System.out.println("Score: " + this.getScoreListener() + " points.");
        System.out.println("Time: " + measureElapsedTime(this.finish, this.start) + " seconds.");
        System.exit(0);
    }

    public double measureElapsedTime(long finish, long start){
        return ((finish - start) / 1000.0);
    }
}
