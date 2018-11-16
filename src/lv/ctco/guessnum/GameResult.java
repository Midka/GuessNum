package lv.ctco.guessnum;

/**
 * Created by m.troushnikova on 11/13/2018.
 */
public class GameResult {
    String name;
    int triesCount;
    long duration;

    public GameResult() {

    }

    public GameResult(String name, int triesCount, long duration) {
        this.name = name;
        this.triesCount = triesCount;
        this.duration = duration;
    }
}
