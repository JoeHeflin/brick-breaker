# Game Plan
## NAMEs


### Breakout Variation Ideas

### Interesting Existing Game Variations

 * Game 1

 * Game 2


#### Block Ideas

 * Block 1

 * Block 2

 * Block 3


#### Power Up Ideas

 * Power Up 1

 * Power Up 2

 * Power Up 3


#### Cheat Key Ideas

 * Cheat Key 1

 * Cheat Key 2

 * Cheat Key 3

 * Cheat Key 4


#### Level Descriptions

 * Level 1
   * Block Configuration

   * Variation features

 * Level 2
   * Block Configuration

   * Variation features

 * Level 3
   * Block Configuration

   * Variation features


### Possible Classes

 * Class 1 - Level
   * Purpose - Creates the level the stage is on

   * Method - layout: arranges desired bricks in their appropriate locations

 * Class 2 - Paddle
   * Purpose - creates paddle object

   * Method - movePaddle(): Moves paddle along x axis with left/right arrow key input at the
   desired speed

 * Class 3 - Brick
   * Purpose - create brick object that stores the number of hits it takes, and any
   special interactions it does when it dies

   * Method - breakBrick(): (Call this method when the brick's life <= 0) - perform the Brick
   object's special property when it breaks (if it has any), then turn the brick into 
   empty space

 * Class 4 - PowerUp
   * Purpose - create power-up object that performs its power when the ball touches it 

   * Method - newBall(int x, int y, int vel) - Create a new ball moving in the same direction as the one that
   activated the power-up

 * Class 5 - Ball
   * Purpose - create the ball

   * Method - checkForHit() - See if the ball is touching any Brick/Paddle object, if it
   hits a brick then call method doDamage, change the direction of the ball based off of
   the angle it hit at. 
