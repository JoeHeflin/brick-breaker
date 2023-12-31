package breakout;


import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest extends ApplicationTest {
    private final Game myGame = new GameTester();
    private Scene myScene;
    private Ball myBall;
    private Paddle myPaddle;
    private Brick myBrick1;
    private Brick myBrick2;
    private Brick myBrick3;

    @Override
    public void start (Stage stage) throws Exception {
        myGame.testStartAgain(stage);
        myGame.startGamePlay();

        myBall = lookup("#Ball").query();
        myPaddle = lookup("#Paddle").query();
        myBrick1 = lookup("#brick1").query();
        myBrick2 = lookup("#brick2").query();
        myBrick3 = lookup("#brick3").query();
    }

    @Test
    void ballCharacteristics() {
        //myGame.startGamePlay();
        assertEquals(0, myGame.getMyBall().getXVel());
        assertEquals(5, myGame.getMyBall().getRadius());
        assertEquals(180, myGame.getMyBall().getCenterX());
        assertEquals(250, myGame.getMyBall().getCenterY());
    }
    @Test
    void paddleCharacteristics() {
        assertEquals(72, myPaddle.getWidth());
        assertEquals(10, myPaddle.getHeight());
        assertEquals(144, myPaddle.getX());
        assertEquals(490, myPaddle.getY());
    }

    @Test
    void blockLocation() {
        assertEquals(216, myBrick1.getX());
        assertEquals(60, myBrick1.getY());
        assertEquals(1, myBrick1.health);
        assertEquals(108, myBrick2.getX());
        assertEquals(100, myBrick2.getY());
        assertEquals(1, myBrick2.health);
        assertEquals(324, myBrick3.getX());
        assertEquals(20, myBrick3.getY());
        assertEquals(1, myBrick3.health);
    }

    @Test
    void cornerBounce() throws IOException {
        myGame.startGamePlay();
        myBall.setCenterX(10);
        myBall.setCenterY(10);
        myBall.start(135, myPaddle);
        for(int i = 0; i < 20; i++) {
            myGame.testStep();
        }

        final double EPSILON = 0.00000001;

        assertTrue(Math.sqrt(2) / 2 - EPSILON < myBall.getXVel() &&
                myBall.getXVel() < Math.sqrt(2) / 2 + EPSILON);
        assertTrue(Math.sqrt(2) / 2 - EPSILON < myBall.getYVel() &&
                myBall.getYVel() < Math.sqrt(2) / 2 + EPSILON);
    }
//
    @Test
    void objectBounce() throws IOException {
        myGame.startGamePlay();
        myGame.getMyBall().setCenterX(220);
        myGame.getMyBall().setCenterY(70);
        myBall.start(90, myPaddle);
        for(int i = 0; i < 20; i++) {
            myGame.testStep();
        }

        final double EPSILON = 0.00000001;
        assertEquals(0, myBrick1.health);
        assertTrue(-1 * EPSILON < myBall.getXVel() &&
                myBall.getXVel() < EPSILON);
        assertTrue(1 - EPSILON < myBall.getYVel() &&
                myBall.getYVel() < 1 + EPSILON);
        assertEquals(0, myBrick1.health);
    }

    @Test
    void offScreenReset() throws IOException {

        myGame.startGamePlay();
        myGame.getMyBall().setCenterX(Game.STAGE_WIDTH - 10);
        myGame.getMyBall().setCenterY(3 * Game.STAGE_HEIGHT / 4);
        myGame.getMyBall().start(-90, myPaddle);

        for(int i = 0; i < 100; i++) {
            myGame.testStep();
            System.out.println(myGame.getMyBall().getXVel());
        }

        ballCharacteristics();
        paddleCharacteristics();
    }

    @Test
    void livesDisplay() throws IOException {

        myGame.startGamePlay();
        int lives = myGame.getMyMenuBar().getLives();
        myGame.getMyBall().setCenterX(Game.STAGE_WIDTH - 10);
        myGame.getMyBall().setCenterY(3 * Game.STAGE_HEIGHT / 4);
        myGame.getMyBall().start(-90, myPaddle);
        for(int i = 0; i < 50; i++) {
            myGame.testStep();
        }
        String text = myGame.getMyMenuBar().getLivesText();
        lives--;
        assertEquals(text.compareTo(Integer.toString(lives)), 1);
    }

    @Test
    void pointsDisplay() throws IOException {
        myGame.startGamePlay();
        myGame.getMyBall().setCenterX(220);
        myGame.getMyBall().setCenterY(70);
        myBall.start(90, myPaddle);
        for(int i = 0; i < 20; i++) {
            myGame.testStep();
        }
        String text = myGame.getMyMenuBar().getPointsText();
        assertEquals(text.compareTo("10"), 0);
        myGame.getMyBall().setCenterX(112);
        myGame.getMyBall().setCenterY(104);
        myBall.start(90, myPaddle);
        for(int i = 0; i < 20; i++) {
            myGame.testStep();
        }
        text = myGame.getMyMenuBar().getPointsText();
        assertEquals(text.compareTo("20"), 0);
    }




}


