package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.awt.*;

public class ExtraLife extends PowerUp{

    MenuBar myMenu;

    ExtraLife(double x, double y, MenuBar menu) {
        super(x, y);
        myMenu = menu;
        setColor(Color.DEEPPINK);
    }

    @Override
    public void usePower(Ball ball, Paddle paddle){
        myMenu.addLife();
        super.usePower(ball, paddle);
    }


}
