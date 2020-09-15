package breakout;

import javafx.scene.paint.Color;

public class Brick {

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
        this.xPos = x;
        this.yPos = y;
        this.type = type;
        alive = true;
        sizeX = 100; //to be filled in
        sizeY = 50; //to be filled in
    }

    //makes brick based off of 'type'
    //types: b = basic,
    void init(){
        if(type.equals("b")){
            health = 1;
            actOnDeath = "";
            getColor();
        }
    }

    void getColor(){
        if(health == 1){
            color = Color.RED;
        }
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
