package breakout;

import javafx.scene.paint.Color;

public class DoubleBrick extends Brick{

    DoubleBrick(double x, double y) {
        super(x, y);
        color = Color.BLUE;
        health = 2;
    }

    @Override
    void takeDamage(Ball ball, MenuBar menuBar, LevelBuilder layout){
        super.takeDamage(ball, menuBar, layout);
        if(health == 1){
            color = Color.RED;
            setColor();
        }

    }
}
