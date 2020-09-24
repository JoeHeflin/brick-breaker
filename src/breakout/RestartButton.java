package breakout;

import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;


public class RestartButton {

    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;
    private static final Color BUTTON_COLOR = Color.BLACK;

    private double x = Game.STAGE_WIDTH/2 - BUTTON_WIDTH/2;
    private double y = 300;

    public RestartButton(Game game, FlowPane root) {
        Text restartText = new Text("Restart");
        //restartText.setX(x);
        restartText.setFont(new Font("Verdana", 20));
        restartText.setFill(Color.WHITE);
        Rectangle box = new Rectangle(0,0,BUTTON_WIDTH,BUTTON_HEIGHT);
        box.setFill(BUTTON_COLOR);
        StackPane sp = new StackPane();//, restartText);
        sp.setLayoutX(x);
        sp.setLayoutY(y);
//        sp.setAlignment();
//        box.setStroke(Paint.valueOf("BLACK"));
        sp.getChildren().addAll(box,restartText);
        root.getChildren().add(sp);
        sp.setOnMouseClicked(e -> game.restartGame());
    }
}
