package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Brick extends Rectangle {

    double x;
    double y;
    int health;
    int width;
    int height;
    Color color;

    //TODO: Make the bricks look distinct

    //Constructor
    Brick(double x, double y){
        super(x, y, Game.BRICK_WIDTH, Game.BRICK_HEIGHT);
        this.x = x;
        this.y = y;
        width = Game.BRICK_WIDTH;
        height = Game.BRICK_HEIGHT;
    }


    void setColor(){
        this.setFill(color);
    }

    void takeDamage(Ball ball, MenuBar menuBar, LevelBuilder layout, PowerUpHolder powerUps){
        if (checkIfAlive()) { //HERE
            health = health - ball.getDamage();
            if (!checkIfAlive()) {
                actOnDeath(powerUps, menuBar, layout);
            }
        } //HERE
    }



    boolean checkIfAlive(){
        return health > 0;
    }

    void actOnDeath(PowerUpHolder powerUps,MenuBar menuBar, LevelBuilder layout){
        health = 0;
        color = Game.BACKGROUND_COLOR;
        setColor();
        menuBar.addPoints();
        layout.removeBrickFromCount();
        powerUps.powerUpChance(this.getX() + (0.5*this.getWidth()),
                this.getY() + (0.5*this.getHeight()));
    }

    double rightEdge() {
        return this.x + this.width;
    }

    double leftEdge() {
        return this.x;
    }
}
