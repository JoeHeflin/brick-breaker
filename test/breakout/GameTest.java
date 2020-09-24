package breakout;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest extends ApplicationTest {
    private final Game myGame = new Game();
    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private Brick myBrick1;
    private Brick myBrick2;
    private Brick myBrick3;

    @Override
    public void start (Stage stage) throws IOException {
            myScene = myGame.setUpLevelScene();
            myGame.reset(myScene);
            stage.setScene(myScene);
            stage.setTitle(Game.TITLE);
            stage.show();

            myBall = lookup("#Ball").query();
            myPaddle = lookup("#Paddle").query();
            myBrick1 = lookup("#brick1").query();
            myBrick2 = lookup("#brick2").query();
            myBrick3 = lookup("#brick3").query();
    }
    @Test
    void ballCharacteristics() {
        assertEquals(0, myBall.getXVel());
        assertEquals(5, myBall.getRadius());
        assertEquals(180, myBall.getCenterX());
        assertEquals(385, myBall.getCenterY());
    }
    @Test
    void paddleCharacteristics() {
        assertEquals(60, myPaddle.getWidth());
        assertEquals(10, myPaddle.getHeight());
        assertEquals(150, myPaddle.getX());
        assertEquals(390, myPaddle.getY());
    }

    @Test
    void blockLocation() {
        assertEquals(0, myBrick1.getX());
        assertEquals(0, myBrick1.getY());
        assertEquals(0, myBrick2.getX());
        assertEquals(10, myBrick2.getY());
        assertEquals(72, myBrick3.getX());
        assertEquals(0, myBrick3.getY());
//        assertThrows(Exception.class, e -> {myBrick3;});
    }

    @Test
    void cornerBounce() throws IOException {
        myBall.setCenterX(Game.STAGE_WIDTH - 10);
        myBall.setCenterY(10);
        myBall.start(45, myPaddle);
        for(int i = 0; i < 10; i++) {
            myGame.testStep();
        }

        final double EPSILON = 0.0000000000001;
        assertTrue(-1 * 15 * Math.sqrt(2) / 2 - EPSILON < myBall.getXVel() &&
                myBall.getXVel() < -1 * 15 * Math.sqrt(2) / 2 + EPSILON);
        assertTrue(-1 * 15 * Math.sqrt(2) / 2 - EPSILON < myBall.getYVel() &&
                myBall.getYVel() < -1 * 15 * Math.sqrt(2) / 2 + EPSILON);
    }
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


