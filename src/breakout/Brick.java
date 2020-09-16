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
    //boolean alive;
    //int lives;

    //TODO: Make the bricks look distinct
    //TODO: Add more types of bricks
    //Constructor
    Brick(double x, double y, String type){
        super(x, y, Game.BRICK_WIDTH, Game.BRICK_HEIGHT);
        this.x = x;
        this.y = y;
        this.type = type;
//        alive = true;
        width = Game.BRICK_WIDTH;
        height = Game.BRICK_HEIGHT;

    }

    //makes brick based off of 'type'
    //types: b = basic,
    void init(){
        if(type.equals("0")){
            health = 0;
            actOnDeath = "";
            //alive = false;
        }
        if(type.equals("1")){
            health = 1;
            actOnDeath = "";
            setColor();
        }
        else {System.out.print("INVALID LEVEL FORMAT ERROR");}

    }

    void setColor(){ // TODO CHANGED NAME BC MISLEADING
//        if(health == 1){
//            color = Color.RED;
//        }
        switch (health) {
            case 0 -> color = Game.BACKGROUND_COLOR;
            case 1 -> color = Color.RED;
            //default -> color =
        }
        this.setFill(color);
    }

    void takeDamage(Ball ball){
        health = health - ball.damage;
        checkIfAlive();
        setColor();
    }

    boolean checkIfAlive(){
        if(this == null || health <= 0) {
            return false;
            //alive = false;
        }
        else{
            return true;
        }
//        return false;
    }


}
