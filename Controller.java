public class Controller {

    private Map map;
    private Character control;
    private boolean success;

    public Controller(Map map, Character control) {
        this.map = map;
        this.control = control;

        String[] arr = this.map.getMapInfo().getPlayerPosition().split(", ");

        int xPosition = Integer.parseInt(arr[0].substring(1)) - 1;
        int yPosition = Integer.parseInt(arr[1].substring(0, arr[1].length() - 1)) - 1;

        if (this.control == 'w') {
            if (this.map.getMap()[xPosition - 1][yPosition] == -1) {
                this.map.getMap()[xPosition - 1][yPosition] = 3;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + xPosition + ", " + (yPosition + 1) + ")");
                this.success = true;
            } else if (this.map.getMap()[xPosition - 1][yPosition] == 1) {
                this.map.getMap()[xPosition - 1][yPosition] = 6;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + xPosition + ", " + (yPosition + 1) + ")");
                this.success = true;
            } else {
                this.success = false;
            }
        } else if (this.control == 'a') {
            if (this.map.getMap()[xPosition][yPosition - 1] == -1) {
                this.map.getMap()[xPosition][yPosition - 1] = 3;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + yPosition + ")");
                this.success = true;
            } else if (this.map.getMap()[xPosition][yPosition - 1] == 1) {
                this.map.getMap()[xPosition][yPosition - 1] = 6;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + yPosition + ")");
                this.success = true;
            } else {
                this.success = false;
            }
        } else if (this.control == 's') {
            if (this.map.getMap()[xPosition + 1][yPosition] == -1) {
                this.map.getMap()[xPosition + 1][yPosition] = 3;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 2) + ", " + (yPosition + 1) + ")");
                this.success = true;
            } else if (this.map.getMap()[xPosition + 1][yPosition] == 1) {
                this.map.getMap()[xPosition + 1][yPosition] = 6;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 2) + ", " + (yPosition + 1) + ")");
                this.success = true;
            } else {
                this.success = false;
            }
        } else if (this.control == 'd') {
            if (this.map.getMap()[xPosition][yPosition + 1] == -1) {
                this.map.getMap()[xPosition][yPosition + 1] = 3;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + (yPosition + 2) + ")");
                this.success = true;
            } else if (this.map.getMap()[xPosition][yPosition + 1] == 1) {
                this.map.getMap()[xPosition][yPosition + 1] = 6;
                if (this.map.getMap()[xPosition][yPosition] == 6) {
                    this.map.getMap()[xPosition][yPosition] = 1;
                } else {
                    this.map.getMap()[xPosition][yPosition] = -1;
                }
                this.map.getMapInfo().setPlayerPosition("(" + (xPosition + 1) + ", " + (yPosition + 2) + ")");
                this.success = true;
            } else {
                this.success = false;
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public boolean isSuccess() {
        return success;
    }
}
