package breakout;

import javafx.scene.paint.Color;

public class GrowPaddle extends PowerUp{
    GrowPaddle(double x, double y) {
        super(x, y);
        setColor(Color.YELLOW);
    }

    @Override
    public void usePower(Ball ball, Paddle paddle){
        if(paddle.getWidth() < Game.STAGE_WIDTH/2) {
            paddle.setWidth(paddle.getWidth() * 1.25);
        }
        super.usePower(ball, paddle);
    }


}
