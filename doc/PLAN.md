# Game Plan
## NAMEs


### Breakout Variation Ideas

### Interesting Existing Game Variations

 * Ballz - This variant involves the player picking the direction at which to release a series of "balls" at blocks from
 the bottom of the screen. These randomly spaced blocks and have different values indicating how many times a ball
 must collide with it before it disappears. Each iteration the blocks move down further towards the bottom of the screen.
 The player loses when a block hits the bottom of the screen without being destroyed.

 * Vortex - This variant is interesting because it takes normal brick breaker and warps the stage so that the paddle
 rotates around layers of concentric brick rings. The color and appearance of each brick indicates how many more hits it
 takes to destroy it. Another interesting geometric feature of vortex is that it appears three dimensional, with bricks
 of higher levels partially covering those beneath them. The game also gives the impression that the ball is falling 
 downward towards the bricks. 


#### Block Ideas

 * Variable hits - certain blocks require more collisions for the ball to destroy them

 * Explosive - if destroyed, does damage to blocks within a certain radius

 * Protected - A certain side of the brick is 
 
 * Speed change - increases or decreases the speed at which the ball travels
 
 * Bounce tweaker - changes the physics of how the ball bounces off of blocks/walls/paddles

 * Paddle size - shrinks paddle


#### Power Up Ideas

 * Paddle size - Increases paddle size

 * Extra ball - adds an extra ball that the paddle can launch

 * Double damage - each collision of the ball causes twice the damage to a brick as before
 
 * Slow motion - slows down the speed of the ball


#### Cheat Key Ideas

 * Can't Lose - the ball bounces off of every side of the screen

 * Freeze Bricks - the bricks stop moving down the screen

 * Skip to Level - skip to a desired level without playing prior ones

 * Slow motion - slows down the speed of the ball


#### Level Descriptions

 * Level 1
   * Block Configuration - A few rows of blocks near the top of the screen, rows are spaced out

   * Variation features - single damage, layered, no special features

 * Level 2
   * Block Configuration - Concentirc squares in the center of the screen

   * Variation features - paddle size power up, variable hits block

 * Level 3
   * Block Configuration - Blocks are closer to the bottom of the screen, closer to the paddle

   * Variation features - double damage power up, speed change
   
 * Level 4
   * Block Configuration - Inside out: use protected blocks to make it so you have to destroy formation from the inside 
   out. Maybe some polygon in the center.

   * Variation features - protected blocks, extra ball power up, explosive blocks

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
