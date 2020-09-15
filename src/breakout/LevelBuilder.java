package breakout;

import java.io.*;

public class LevelBuilder {

    //TODO: Return list of Bricks in the level
    int row;
    int col;
    int brickHeight;
    int brickWidth;
    Brick[][] brickLayout;

    public LevelBuilder(){
        int brickHeight = Game.BRICK_HEIGHT;
        int brickWidth = Game.BRICK_WIDTH;
        row = 0;
        col = 0;
        brickLayout = new Brick[Game.STAGE_HEIGHT/brickHeight][Game.STAGE_WIDTH/brickWidth];
    }

    void init(String levelName) throws IOException {
        row = 0;
        col = 0;
        File localStream = new File(levelName);
        String line;
        BufferedReader br = new BufferedReader(new FileReader(localStream));
        while ((line = br.readLine()) != null) {
            String[] brickSymbols = line.split(" ");
            for (String symbol : brickSymbols) {
                Brick newBrick = new Brick(col*brickWidth, row*brickHeight, symbol);
                newBrick.init();
                brickLayout[row][col] = newBrick;
                col++;
            }
            col = 0;
            row++;
            //ERROR call
            if(row > brickLayout.length){
                System.out.println("ERROR: Level file has too many rows");
                break;
            }
        }
    }

    Brick[][] getBrickLayout(){
        return brickLayout;
    }
}
