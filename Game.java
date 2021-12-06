import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        Map map = start(2);
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

                Controller controller = new Controller(map, control.charAt(i));
                map = controller.getMap();
                boolean success = controller.isSuccess();

                System.out.println();
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

        System.out.println("Stage " + stage + "\n");

        return map;
    }
}
