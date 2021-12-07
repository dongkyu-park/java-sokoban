import java.io.*;

public class Map {

    public static final int X = 500;
    public static final int Y = 500;

    private int stage = 1;
    private int[][] map = new int[Y][X];
    private MapInfo mapInfo = new MapInfo();

    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public int[][] getMap() {
        return map;
    }

    private void passStage(String str) {
        if (str.startsWith("=")) {
            stage++;
        }
    }

    private BufferedReader getMapData() throws FileNotFoundException {
        String directoryName = System.getProperty("user.dir");
        return new BufferedReader(new FileReader(directoryName + "\\map.txt"));
    }

    public void createStageMap(int currentStage) throws IOException {
        BufferedReader reader = getMapData();
        String str = "";
        boolean endStage = false;

        while ((str = reader.readLine()) != null) {
            if (stage < currentStage) {
                passStage(str);
                continue;
            }

            if (stage == currentStage) {
                int i = mapInfo.getHeight();

                for (int j = 0; j < str.length(); j++) {
                    if (str.startsWith("S")) {
                        break;
                    }

                    mapInfo.setWidth(Math.max(str.length(), mapInfo.getWidth()));

                    if (str.charAt(j) == ' ') {
                        map[i][j] = -1;
                        mapInfo.setHeight(i + 1);
                    }
                    if (str.charAt(j) == '#') {
                        map[i][j] = 0;
                        mapInfo.setHeight(i + 1);
                    }
                    if (str.charAt(j) == 'O') {
                        map[i][j] = 1;
                        mapInfo.setHeight(i + 1);
                        mapInfo.setHallNum(mapInfo.getHallNum() + 1);
                    }
                    if (str.charAt(j) == 'o') {
                        map[i][j] = 2;
                        mapInfo.setHeight(i + 1);
                        mapInfo.setBallNum(mapInfo.getBallNum() + 1);
                    }
                    if (str.charAt(j) == 'P') {
                        map[i][j] = 3;
                        mapInfo.setPlayerPosition("(" + (i + 1) + ", " + (j + 1) + ")");
                    }
                    if (str.startsWith("=")) {
                        endStage = true;
                        break;
                    }
                }

                if (endStage) {
                    break;
                }
            }
        }

        if (stage < currentStage || currentStage <= 0) {
            throw new IllegalArgumentException("해당 스테이지는 존재하지 않습니다.");
        }
    }

    public void viewMapInfo() {
        System.out.println();
        for (int i = 0; i < mapInfo.getHeight(); i++) {
            for (int j = 0; j < mapInfo.getWidth(); j++) {
                if (map[i][j] == -1) {
                    System.out.print(" ");
                }

                if (map[i][j] == 0) {
                    System.out.print("#");
                }

                if (map[i][j] == 1) {
                    System.out.print("O");
                }

                if (map[i][j] == 2) {
                    System.out.print("o");
                }

                if (map[i][j] == 3 || map[i][j] == 6) {
                    System.out.print("P");
                }

                if (map[i][j] == 5) {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
