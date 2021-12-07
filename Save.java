import java.util.ArrayList;

public class Save {
    int currentStage;
    int countTurn;
    int backCount;
    ArrayList<Map> log;
    Map map;

    public Save() {
    }

    public Save(int currentStage, int countTurn, int backCount, ArrayList<Map> log, Map map) {
        this.currentStage = currentStage;
        this.countTurn = countTurn;
        this.backCount = backCount;
        this.log = log;
        this.map = map;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public int getCountTurn() {
        return countTurn;
    }

    public int getBackCount() {
        return backCount;
    }

    public ArrayList<Map> getLog() {
        return log;
    }

    public Map getMap() {
        return map;
    }
}
