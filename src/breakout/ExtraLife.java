package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ExtraLife extends PowerUp{

    private MenuBar myMenu;

    ExtraLife(double x, double y, MenuBar menu) {
        super(x, y);
        setColor(Color.GREEN);
        myMenu = menu;
    }

    @Override
    public void usePower(Ball ball, Paddle paddle){
        myMenu.addLife();
    }


}
