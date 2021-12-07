public class Controller {

    private Map map;
    private Character control;
    private boolean success;

    public Controller(Map map, Character control) {
        this.map = map;
        this.control = control;
        controlPad(this.control);
    }

    public Map getMap() {
        return map;
    }

    public boolean isSuccess() {
        return success;
    }

    public void controlPad(Character control) {
        String[] arr = this.map.getMapInfo().getPlayerPosition().split(", ");

        int xPosition = Integer.parseInt(arr[0].substring(1)) - 1;
        int yPosition = Integer.parseInt(arr[1].substring(0, arr[1].length() - 1)) - 1;

        if (control == 'w') {
            if (setPlayerMoveSpace(xPosition - 1, yPosition)) {
                setAfterPlayerMove(xPosition, yPosition);
                this.map.getMapInfo().setPlayerPosition("(" + xPosition + ", " + (yPosition + 1) + ")");
                this.success = true;
                return;
            }
            this.success = false;
            return;
        }

        if (control == 'a') {
            if (setPlayerMoveSpace(xPosition, yPosition - 1)) {
                setAfterPlayerMove(xPosition, yPosition);
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + yPosition + ")");
                this.success = true;
                return;
            }
            this.success = false;
            return;
        }

        if (control == 's') {
            if (setPlayerMoveSpace(xPosition + 1, yPosition)) {
                setAfterPlayerMove(xPosition, yPosition);
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 2) + ", " + (yPosition + 1) + ")");
                this.success = true;
                return;
            }
            this.success = false;
            return;
        }

        if (control == 'd') {
            if (setPlayerMoveSpace(xPosition, yPosition + 1)) {
                setAfterPlayerMove(xPosition, yPosition);
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + (yPosition + 2) + ")");
                this.success = true;
                return;
            }
            this.success = false;
            return;
        }
    }

    public boolean setPlayerMoveSpace(int xPosition, int yPosition) {
        if (this.map.getMap()[xPosition][yPosition] == -1) { // destination is Empty
            this.map.getMap()[xPosition][yPosition] = 3;
            this.map.getMapInfo().setPlayerPosition("(" + xPosition + ", " + (yPosition + 1) + ")");
            return true;
        }
        if (this.map.getMap()[xPosition][yPosition] == 1) { // destination is hall
            this.map.getMap()[xPosition][yPosition] = 6;
            this.map.getMapInfo().setPlayerPosition("(" + xPosition + ", " + (yPosition + 1) + ")");
            return true;
        }
        return false;
    }

    public void setAfterPlayerMove(int xPosition, int yPosition) {
        if (this.map.getMap()[xPosition][yPosition] == 6) { // player in hall
            this.map.getMap()[xPosition][yPosition] = 1;
        } else { // not in hall
            this.map.getMap()[xPosition][yPosition] = -1;
        }
    }
}
