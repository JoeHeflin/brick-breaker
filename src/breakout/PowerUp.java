package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public abstract class PowerUp extends Circle {

    private boolean active;
    private double spd;

    PowerUp(double x, double y){
        super(x, y, Game.POWERUP_RADIUS);
        active = true;
        spd = 130;
    }

    public boolean getActive(){
        return active;
    }

    public void usePower(Ball ball, Paddle paddle){
        setInactive();
    }

    public void setInactive(){
        active = false;
        setColor(Game.BACKGROUND_COLOR);
    }

    public void setColor(Color color){
        this.setFill(color);
    }

    void updatePosition(double elapsedTime) {
        setCenterY(getCenterY() + this.spd * elapsedTime);
    }
    
}
