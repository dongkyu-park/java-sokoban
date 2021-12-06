import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

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

    private BufferedReader getMapData() {
        String mapData = "Stage 1\n" +
                "#####\n" +
                "#OoP#\n" +
                "#####\n" +
                "=====\n" +
                "Stage 2\n" +
                "  #######  \n" +
                "###  O  ###\n" +
                "#    o    #\n" +
                "# Oo P oO #\n" +
                "###  o  ###\n" +
                "  #  O  #  \n" +
                "  #######  ";

        return new BufferedReader(new StringReader(mapData));
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
        System.out.println("Stage " + stage + "\n");

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

                if (map[i][j] == 3) {
                    System.out.print("P");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("가로크기: " + mapInfo.getWidth());
        System.out.println("세로크기: " + mapInfo.getHeight());
        System.out.println("구멍의 수: " + mapInfo.getHallNum());
        System.out.println("공의 수: " + mapInfo.getBallNum());
        System.out.println("플레이어 위치: " + mapInfo.getPlayerPosition() + "\n");
    }
}
