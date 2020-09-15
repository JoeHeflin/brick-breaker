package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {

    int xPos;
    int yPos;
    int health;
    int sizeX;
    int sizeY;
    String type;
    String actOnDeath;
    Color color;
    boolean alive;


    //Constructor
    Brick(int x, int y, String type){
        super(x, y, Game.BRICK_WIDTH, Game.BRICK_HEIGHT);
        this.xPos = x;
        this.yPos = y;
        this.type = type;
        alive = true;
        sizeX = Game.BRICK_WIDTH;
        sizeY = Game.BRICK_HEIGHT;
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
