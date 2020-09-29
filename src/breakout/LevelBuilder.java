package breakout;

import java.io.*;

public class LevelBuilder {

    //TODO: Return list of Bricks in the level
    private int row;
    private int col;
    private int brickHeight;
    private int brickWidth;
    private int myBrickCount;
    private String myLevel;
    private Brick[][] brickLayout;


    public LevelBuilder(String level) {
        brickHeight = Game.BRICK_HEIGHT;
        brickWidth = Game.BRICK_WIDTH;
        row = 0;
        col = 0;
        brickLayout = new Brick[Game.STAGE_HEIGHT / brickHeight][Game.STAGE_WIDTH / brickWidth];
        myBrickCount = 0;
        myLevel = level;
    }

    void init() throws IOException { //TODO Why init?
        row = Game.MENU_NUMBER_OF_ROWS;
        col = 0;
        File localStream = new File(myLevel);
        String line;
        BufferedReader br = new BufferedReader(new FileReader(localStream));
        while ((line = br.readLine()) != null) {
            String[] brickSymbols = line.split(" ");
            for (String symbol : brickSymbols) {
                Brick newBrick = buildBrick(col * brickWidth, row * brickHeight, symbol);
                newBrick.setColor();
                brickLayout[row][col] = newBrick;
                col++;
            }
            col = 0;
            row++;
            //ERROR call
            if (row > brickLayout.length) {
                System.out.println("ERROR: Level file has too many rows " + row + brickLayout.length);
                break;
            }
        }
    }

    public void removeBrickFromCount() {
        myBrickCount--;
    }

    public Brick[][] getBrickLayout() {
        return brickLayout;
    }

    public void setBrickLayout(Brick[][] newLayout) {
        brickLayout = newLayout;
    }

    public boolean noMoreBricks() {
        return myBrickCount == 0;
    }


    public Brick buildBrick(double x, double y, String type) {
        if (type.equals("1")) {
            myBrickCount++;
            return new BaseBrick(x, y);
        } else if (type.equals("2")) {
            myBrickCount++;
            return new DoubleBrick(x, y);
        } else if (type.equals("3")) {
            myBrickCount++;
            return new BombBrick(x, y);
        } else {
            return new BrokenBrick(x, y);
        }
    }



}
