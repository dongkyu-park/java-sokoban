public class MapInfo {

    private int width;
    private int height;
    private int hallNum;
    private int ballNum;
    private String playerPosition;

    public MapInfo() {
        this.width = 0;
        this.height = 0;
        this.hallNum = 0;
        this.ballNum = 0;
        this.playerPosition = "";
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHallNum() {
        return hallNum;
    }

    public int getBallNum() {
        return ballNum;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHallNum(int hallNum) {
        this.hallNum = hallNum;
    }

    public void setBallNum(int ballNum) {
        this.ballNum = ballNum;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }
}
