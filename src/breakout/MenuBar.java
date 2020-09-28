package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuBar {
    private static final int POINTS_PER_BRICK = 10;
    private Text myLivesCountText;
    private Group myMenuBarGroup;
    private int myLives;
    private Text myPointsText;
    private int myPoints;

    public MenuBar() {
        myMenuBarGroup = new Group();
        myLives = Game.INITIAL_LIVES_COUNT + 1;
        myPoints = 0;
    }

    public void init() {
        // "Lives: " text in menu bar
        // Could reduce duplicated code TODO constants for magic numbers
        Text livesText = new Text(Game.STAGE_WIDTH - 50, 15, "Lives: ");
        livesText.setFont(new Font("Verdana", 10));
        livesText.setFill(Color.BLACK);
        myMenuBarGroup.getChildren().add(livesText);
        // The number of lives left listed in the menu bar
        myLivesCountText = new Text(Game.STAGE_WIDTH - 15, 15, Integer.toString(myLives));
        myLivesCountText.setFont(new Font("Verdana", 10));
        myLivesCountText.setFill(Color.BLACK);
        myMenuBarGroup.getChildren().add(myLivesCountText);
        // "Points: " text in menu bar
        Text pointsText = new Text(5, 15, "Points: ");
        pointsText.setFont(new Font("Verdana", 10));
        pointsText.setFill(Color.BLACK);
        myMenuBarGroup.getChildren().add(pointsText);
        //
        myPointsText = new Text(50, 15, Integer.toString(myPoints));
        myPointsText.setFont(new Font("Verdana", 10));
        myPointsText.setFill(Color.BLACK);
        myMenuBarGroup.getChildren().add(myPointsText);
    }

    public Group getMenuBar() {
        return myMenuBarGroup;
    }

    public int getLives() {
//        String ret = Integer.toString(lives);
        return myLives;
    }

    public void loseLife() {
        myLives --;
    }

    public void addPoints() {
        myPoints += POINTS_PER_BRICK;
    }

    public boolean noMoreLives() {
        return myLives <= 0;
    }

    public void updateText(){
        myLivesCountText.setText(Integer.toString(myLives));
        myPointsText.setText(Integer.toString(myPoints));
    }

    public String getLivesText() {
        return myLivesCountText.getText();
    }

    public String getPointsText() {
        return myPointsText.getText();
    }
}
