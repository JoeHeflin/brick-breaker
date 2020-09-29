package breakout;

import javafx.scene.paint.Color;

public class SlowMotion extends PowerUp{
    SlowMotion(double x, double y) {
        super(x, y);
        setColor(Color.PURPLE);
    }

    public void usePower(Ball ball, Paddle paddle){
        ball.setXVel(ball.getXVel() * 0.75);
        ball.setYVel(ball.getYVel() * 0.75);
        super.usePower(ball, paddle);
    }
}
