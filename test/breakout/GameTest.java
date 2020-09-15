package breakout;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest extends ApplicationTest {
    private final Game myGame = new Game();
    public Scene myScene;
    public Ball myBall;
    //public Paddle myPaddle;

    @Override
    public void start (Stage stage) throws IOException {
            myScene = myGame.setUpScene(Game.LEVEL1_LAYOUT);
            stage.setScene(myScene);
            stage.setTitle(Game.TITLE);
            stage.show();

            myBall = lookup("#Ball").query();
            //myPaddle = lookup("#Paddle").query();


    }
    @Test
    void ballCharacteristics() {
        assertEquals(10, myBall.xVel);
        assertEquals(5, myBall.getRadius());
        assertEquals(180, myBall.getCenterX());
        assertEquals(400, myBall.getCenterY());
    }
//    @Test TODO
//    void paddleCharacteristics() {
//        assertEquals(10, myPaddle.xVel);
//        assertEquals(5, myPaddle.getRadius());
//        assertEquals(180, myPaddle.getCenterX());
//        assertEquals(400, myPaddle.getCenterY());
//    }

//    @Test TODO
//    void blockLocation() {
//        assertEquals();
//        assertEquals();
//        assertEquals();
//    }

//    @Test TODO
//    void cornerBounce() {
//        assertEquals();
//        assertEquals();
//        assertEquals();
//    }
//
//    @Test TODO
//    void objectBounce() {
//        assertEquals();
//        assertEquals();
//        assertEquals();
//    }
//    @Test TODO
//    void offScreenReset() {
//        assertEquals();
//        assertEquals();
//        assertEquals();
//    }

}


