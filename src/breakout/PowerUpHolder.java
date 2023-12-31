package breakout;

import javafx.scene.Group;

import java.util.ArrayList;

public class PowerUpHolder {
    private static final int POWERUP_ODDS = 100;

    MenuBar myMenu;
    Group myRoot;
    ArrayList<PowerUp> activePowerUps;

    PowerUpHolder(Group root, MenuBar menu){
        myMenu = menu;
        myRoot = root;
        activePowerUps = new ArrayList<>();
    }

    public void movePowerUps(double elapsedTime){
        for(PowerUp p : activePowerUps){
            p.updatePosition(elapsedTime);
        }
    }

    public void reset(){
        for(PowerUp p : activePowerUps){
            p.setInactive();
        }
    }



    public void newPowerUp(PowerUp power){
        myRoot.getChildren().add(power);
        activePowerUps.add(power);
    }

    public ArrayList<PowerUp> getActivePowerUps(){
        return activePowerUps;
    }

    public void checkPowerUps(){
        ArrayList<PowerUp> inactive = new ArrayList<>();
        for(PowerUp p : activePowerUps){
            if(!p.getActive()){
                myRoot.getChildren().remove(p);
                inactive.add(p);
            }
        }
        activePowerUps.removeAll(inactive);
    }

    public void powerUpChance(double x, double y) {
        int spawnChance = (int) (Math.random() * POWERUP_ODDS);
        if (0 < spawnChance && spawnChance < 5) {
            PowerUp power = new SlowMotion(x, y);
            newPowerUp(power);
        }
        else if (10 < spawnChance && spawnChance < 20){
            PowerUp power = new GrowPaddle (x, y);
            newPowerUp(power);
        }

        else if (30 < spawnChance && spawnChance < 33){
            PowerUp power = new ExtraLife (x, y, myMenu);
            newPowerUp(power);
        }
    }
}
