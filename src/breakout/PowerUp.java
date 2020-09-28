package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public abstract class PowerUp extends Circle {

    private boolean active;
    private Color color;

    PowerUp(double x, double y){
        super(x, y, Game.POWERUP_RADIUS);
        active = true;
    }

    public boolean getActive(){
        return active;
    }

    public void usePower(){
        setInactive();
    }

    public void setInactive(){
        active = false;
        color = Game.BACKGROUND_COLOR;
        setColor();
    }

    public void setColor(){
        this.setFill(color);
    }




}
