package breakout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LevelBuilder {

    //TODO: Return list of Bricks in the level
    Brick[][] brickLayout;

    public LevelBuilder(String levelName){
        try {
            init(levelName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void init(String levelName) throws FileNotFoundException {
        File localStream = new File(levelName);

        BufferedReader br = new BufferedReader(new FileReader(localStream));
    }
}
