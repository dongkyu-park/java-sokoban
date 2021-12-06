import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Map map = new Map();
        map.createStageMap(1);
        map.viewMapInfo();

        Map map2 = new Map();
        map2.createStageMap(2);
        map2.viewMapInfo();
    }
}
