package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle{

    int radius;
    float xPos;
    float yPos;

    float up;
    float down;
    float left;
    float right;
    float speed;
    float xVel;
    float yVel;
    int damage;


    public Ball(float startingX, float startingY, float spd, int r){
        this.radius = r;
        this.xPos = startingX;
        this.yPos = startingY;
        this.up = yPos + radius;
        this.down = yPos - radius;
        this.left = xPos - radius;
        this.right = xPos + radius;
        this.xVel = 0;
        this.yVel = 0;
        this.speed = spd;
        this.damage = 1;
    }

    //Makes the ball start moving // stop moving
    void startStop(boolean go, float launchAngle){
        if(go){
            this.xVel = (float) (speed * Math.cos(Math.toRadians(launchAngle)));
            this.yVel = (float) (speed * Math.sin(Math.toRadians((launchAngle))));
        }
        else{
            this.xVel = 0;
            this.yVel = 0;
        }
    }

    void moveAndDetectStage(){

        if(left < 0){
            bounce(true);
            xPos = 0;
        }
        if(right > Game.STAGE_WIDTH){
            bounce(true);
            xPos = Game.STAGE_WIDTH;
        }
        if(up < 0){
            bounce(false);
            yPos = 0;
        }

        xPos += xVel * (float)(1/Game.FRAMES_PER_SECOND);
        yPos += yVel * (float)(1/Game.FRAMES_PER_SECOND);
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
