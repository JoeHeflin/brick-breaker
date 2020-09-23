package breakout;

import java.awt.*;
import java.util.logging.Level;

import javafx.scene.paint.Color;

//BombBrick breaks all bricks around it upon death
public class BombBrick extends Brick{
    BombBrick(double x, double y) {
        super(x, y);
        health = 1;
        color = Color.ORANGE;
    }

    @Override
    void takeDamage(Ball ball, MenuBar menuBar, LevelBuilder layout){
        super.takeDamage(ball, menuBar, layout);
        if(health <= 0) {
            int colPos = (int) (x / width);
            int rowPos = (int) (y / height);
            Brick[][] bricks = layout.getBrickLayout();
            for (int r = rowPos - 1; r < rowPos + 2; r++) {
                for (int c = colPos - 1; c < colPos + 2; c++) {
                    if (0 < r && r < bricks.length){
                        if(0 < c && c < bricks[r].length){
                            bricks[r][c].takeDamage(ball, menuBar,layout);// switched c and r
                        }
                    }
                }
            }
            //layout.setBrickLayout(bricks);
        }
    }
}
