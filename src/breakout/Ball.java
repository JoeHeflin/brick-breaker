package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {

    float xPos;
    float yPos;
    float xVel;
    float yVel;
    float speed;
    int radius;
    private Circle myBall;

    public Ball(float startingX, float startingY, float spd, int r){
        radius = r;
        xPos = startingX;
        yPos = startingY;
        myBall = new Circle(xPos, yPos, radius);
        speed = spd;
        xVel = 0;
        yVel = 0;
    }

    //Makes the ball start moving // stop moving
    void startStop(boolean go, float launchAngle){
        if(go){
            this.xVel = (speed * Math.cos(Math.toRadians(launchAngle));
            this.yVel = (speed * Math.sin(Math.toRadians((launchAngle)));
        }
        else{
            this.xVel = 0;
            this.yVel = 0;
        }
    }

    void moveAndDetectStage(Stage stage){
        float up = yPos + radius;
        float down = yPos - radius;
        float left = xPos - radius;
        float right = xPos + radius;

        if(left < 0){
            bounce(true);
            xPos = 0;
        }
        if(right > stage.sizeX){
            bounce(true);
            xPos = stage.sizeX;
        }
        if(up < 0){
            bounce(false);
            yPos = 0;
        }

        xPos += xVel;
        yPos += yVel;
    }

    void bounce(Boolean xOtherwiseY){
        if(xOtherwiseY){
            this.xVel = -this.xVel;
        }
        else{
            this.yVel = -this.yVel;
        }
    }


}
