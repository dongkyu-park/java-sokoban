import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static final int END_STAGE = 5;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int currentStage = 1;
        int countTurn = 0;
        int backCount = 0;
        ArrayList<Map> log = new ArrayList<>();
        Save[] saveSlot = new Save[5];

        Map map = start(currentStage);
        map.viewMapInfo();
        log.add(countTurn, map.clone());

        while (!exit) {
            System.out.print("SOKOBAN> ");
            String control = sc.next();

            for (int i = 0; i < control.length(); i++) {
                if (control.length() >= 2 && control.charAt(1) == 'S') {
                    System.out.println(control.charAt(0) + "번 세이브에 진행상황을 저장합니다.");
                    saveSlot[Character.getNumericValue(control.charAt(0)) - 1] = new Save(currentStage, countTurn, backCount, (ArrayList<Map>) log.clone(), map.clone());
                    map.viewMapInfo();
                    break;
                }

                if (control.length() >= 2 && control.charAt(1) == 'L') {
                    try {
                        map = saveSlot[Character.getNumericValue(control.charAt(0)) - 1].getMap().clone();
                        System.out.println(control.charAt(0) + "번 세이브에서 진행상황을 불러옵니다.");
                    } catch (NullPointerException e) {
                        System.out.println("해당 세이브 슬롯에 데이터가 존재하지 않습니다.");
                        break;
                    }
                    log = (ArrayList<Map>) saveSlot[Character.getNumericValue(control.charAt(0)) - 1].getLog().clone();
                    backCount = saveSlot[Character.getNumericValue(control.charAt(0)) - 1].getBackCount();
                    countTurn = saveSlot[Character.getNumericValue(control.charAt(0)) - 1].getCountTurn();
                    currentStage = saveSlot[Character.getNumericValue(control.charAt(0)) - 1].getCurrentStage();
                    map.viewMapInfo();
                    break;
                }

                if (control.charAt(i) == 'q') {
                    System.out.println("Bye~");
                    exit = true;
                    break;
                }

                if (control.charAt(i) == 'r') {
                    System.out.println("스테이지를 초기화 합니다.\n");
                    map = start(currentStage);
                    map.viewMapInfo();

                    countTurn = 0;
                    backCount = 0;
                    log = new ArrayList<>();
                    log.add(countTurn, map.clone());

                    break;
                }

                if (control.charAt(i) == 'u') {
                    if (countTurn <= 0) {
                        System.out.println("더 이상 되돌릴 수 없습니다!");
                        map.viewMapInfo();
                        break;
                    } else {
                        System.out.println("이전 상황으로 돌아갑니다.\n");
                        backCount++;
                        countTurn--;
                        map = log.get(countTurn).clone();
                        map.viewMapInfo();
                        break;
                    }
                }

                if (control.charAt(i) == 'U' && backCount > 0) {
                    System.out.println("되돌리기를 취소합니다.\n");
                    backCount--;
                    countTurn++;
                    map = log.get(countTurn).clone();
                    map.viewMapInfo();
                    break;
                } else if (control.charAt(i) == 'U' && backCount <= 0) {
                    System.out.println("취소할 되돌리기가 없습니다.\n");
                    map.viewMapInfo();
                    continue;
                }

                Controller controller = new Controller(map, control.charAt(i));
                map = controller.getMap();
                boolean success = controller.isSuccess();

                if (map.getMapInfo().getHallNum() == 0) {
                    countTurn++;
                    System.out.println("빠밤! Stage " + currentStage + " 클리어!");
                    System.out.println("턴수: " + countTurn + "\n");

                    countTurn = 0;
                    backCount = 0;
                    log = new ArrayList<>();

                    if (currentStage == END_STAGE) {
                        System.out.println("전체 게임을 클리어 하셨습니다!\n축하 드립니다!");
                        exit = true;
                    } else {
                        currentStage++;
                        map = start(currentStage);
                        map.viewMapInfo();
                        log.add(countTurn, map.clone());
                    }
                    break;
                }

                map.viewMapInfo();

                if (success) {
                    countTurn++;
                    backCount = 0;
                    log.add(countTurn, map.clone());
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

    public static Map start(int stage) throws Exception {
        Map map = new Map();
        map.createStageMap(stage);

        System.out.println("Stage " + stage);

        return map;
    }
}
