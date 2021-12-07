import java.io.IOException;
import java.util.Scanner;

public class Game {

    public static final int END_STAGE = 5;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int currentStage = 1;

        Map map = start(currentStage);
        map.viewMapInfo();

        while (!exit) {
            System.out.print("SOKOBAN> ");
            String control = sc.next();

            for (int i = 0; i < control.length(); i++) {
                if (control.charAt(i) == 'q') {
                    System.out.println("Bye~");
                    exit = true;
                    break;
                }

                if (control.charAt(i) == 'r') {
                    System.out.println("스테이지를 초기화 합니다.\n");
                    map = start(currentStage);
                    map.viewMapInfo();
                    break;
                }

                Controller controller = new Controller(map, control.charAt(i));
                map = controller.getMap();
                boolean success = controller.isSuccess();

                if (map.getMapInfo().getHallNum() == 0) {
                    System.out.println("빠밤! Stage " + currentStage + "클리어!\n");
                    if (currentStage == END_STAGE) {
                        System.out.println("전체 게임을 클리어 하셨습니다!\n축하 드립니다!");
                        exit = true;
                    } else {
                        currentStage++;
                        map = start(currentStage);
                        map.viewMapInfo();
                    }
                    break;
                }

                map.viewMapInfo();

                if (success) {
                    if (control.charAt(i) == 'w') {
                        System.out.println("W: 위쪽으로 이동합니다.\n");
                        continue;
                    }

                    if (control.charAt(i) == 'a') {
                        System.out.println("A: 왼쪽으로 이동합니다.\n");
                        continue;
                    }

                    if (control.charAt(i) == 's') {
                        System.out.println("S: 아래쪽으로 이동합니다.\n");
                        continue;
                    }

                    if (control.charAt(i) == 'd') {
                        System.out.println("D: 오른쪽으로 이동합니다.\n");
                        continue;
                    }
                } else {
                    System.out.println(Character.toUpperCase(control.charAt(i)) + ": (경고!) 해당 명령을 수행할 수 없습니다!");
                }
            }
        }
    }

    public static Map start(int stage) throws IOException {
        Map map = new Map();
        map.createStageMap(stage);

        System.out.println("Stage " + stage);

        return map;
    }
}
