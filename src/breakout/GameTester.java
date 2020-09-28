package breakout;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTester extends Game{
    private static final String LEVELS_DIR = "testdata/";
    private static final ArrayList<String> LEVELS = new ArrayList<>(Arrays.asList("threeBricks.txt"));

    private int myLevel = 0;

    @Override
    public LevelBuilder buildBrick(String filePath) {
        return new LevelBuilder(LEVELS_DIR + LEVELS.get(myLevel));
    }

    @Override
    public void setBrickIds() {
        Brick brick1 = getBricks().getBrickLayout()[3][6];
        brick1.setId("brick1");
        Brick brick2 = getBricks().getBrickLayout()[5][3];
        brick2.setId("brick2");
        Brick brick3 = getBricks().getBrickLayout()[1][9];
        brick3.setId("brick3");
    }

}
