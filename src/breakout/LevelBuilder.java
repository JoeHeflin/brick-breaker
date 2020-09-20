package breakout;

import java.io.*;

public class LevelBuilder {

    //TODO: Return list of Bricks in the level
    int row;
    int col;
    int brickHeight;
    int brickWidth;
    Brick[][] brickLayout;
    int myBrickCount;
    String nextLevel;

    public LevelBuilder(){
        brickHeight = Game.BRICK_HEIGHT;
        brickWidth = Game.BRICK_WIDTH;
        row = 0;
        col = 0;
        brickLayout = new Brick[Game.STAGE_HEIGHT/brickHeight][Game.STAGE_WIDTH/brickWidth];
        myBrickCount = 0;
    }

    public void removeBrickFromCount() {
        myBrickCount --;
    }

    void init(String levelName) throws IOException { //TODO Why init?
        row = Game.MENU_NUMBER_OF_ROWS;
        col = 0;
        File localStream = new File(levelName);
        String line;
        BufferedReader br = new BufferedReader(new FileReader(localStream));
        while ((line = br.readLine()) != null) {
            String[] brickSymbols = line.split(" ");
            for (String symbol : brickSymbols) {
                if (symbol.compareTo(Game.BLANK_SYMBOL) != 0) {
                    Brick newBrick = new Brick(col * brickWidth, row * brickHeight, symbol);
                    newBrick.init();
                    brickLayout[row][col] = newBrick;
                    myBrickCount ++;
                }
                col++;
            }
            col = 0;
            row++;
            //ERROR call
            if(row > brickLayout.length){
                System.out.println("ERROR: Level file has too many rows " + row + brickLayout.length);
                break;
            }
        }
    }

    Brick[][] getBrickLayout(){
        return brickLayout;
    }

    public boolean noMoreBricks() {
        return myBrickCount == 0;
    }

    public String getNextLevel() {
        return nextLevel;
    }
}