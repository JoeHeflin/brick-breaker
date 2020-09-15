package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {

    double x;
    double y;
    int health;
    int width;
    int height;
    String type;
    String actOnDeath;
    Color color;
    boolean alive;

    //TODO: Make the bricks look distinct
    //TODO: Add more types of bricks
    //Constructor
    Brick(double x, double y, String type){
        super(x, y, Game.BRICK_WIDTH, Game.BRICK_HEIGHT);
        this.x = x;
        this.y = y;
        this.type = type;
        alive = true;
        width = Game.BRICK_WIDTH;
        height = Game.BRICK_HEIGHT;
    }

    //makes brick based off of 'type'
    //types: b = basic,
    void init(){
        if(type.equals("1")){
            health = 1;
            actOnDeath = "";
            getColor();
        }

    }

    void getColor(){
        if(health == 1){
            color = Color.RED;
        }
        this.setFill(color);
    }

    void takeDamage(Ball ball){
        health = health - ball.damage;
        checkIfAlive();

    }

    void checkIfAlive(){
        if(health <= 0) {
            health = 0;
            alive = false;
        }
        else{
            alive = true;
        }
    }


}
