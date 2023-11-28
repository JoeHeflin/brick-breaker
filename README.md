game
====

This project implements the game of Breakout.

Name: Robert Hellinga, Joseph Heflin

### Timeline

Start Date: September 13, 2020

Finish Date: September 28, 2020

Hours Spent: 25-30

### Resources Used

design.cs.duke.edu


### Running the Program

Main class: Game.java

Data files needed: level1.txt, level2.txt, etc... (found in levelFormats folder)

Key/Mouse inputs: left and right to move, mouse to fire the ball. Use mouse to navigate
the menu

Cheat keys: 

| key | action  |
|:---------:|:----------------|
|    R      |     Reset ball and paddle     | 
|  SPACE    |     Pause/Unpause     | 
|    L      |     Add a Life     | 
|    P      |     Spawn powerup     | 
|    N      |     procede to next level     | 
|    D      |     Destroy brick     | 
|    W      |     Win game     | 
|    1      |     Go to Level 1     | 
|    2      |     Go to Level 2     | 
|    3      |     Go to Level 3     | 

Known Bugs: Very occasionally, the ball will phase through a brick instead of bouncing off.
The bug is hard to accurately replicate and rarely occurs, but it does happen.

Extra credit:

Cut screens (opening/ending, win/loss)

Notable bonus features: 

•dynamic bouncing off of the paddle

•Easily expandable level pool (until the level select text wraps off the 
screen)


### Notes/Assumptions
INSTRUCTIONS:

• Use the arrow keys to move and the mouse to fire the ball 

BRICK TYPES:

-Broken brick (empty) ("0")

-Single brick (basic) ("1")

-Double brick (2x health) ("2")

-Bomb brick (blows up bricks around it in a 1x1 grid) ("3")

POWER UPS:

-Slow Motion (Purple)

-Paddle Grower (Yellow)

-Extra life [rare!] (Pink)


### Impressions

The implementation of Brick Breaker here is relatively basic, but the structure of the
code allows for easy expansion/customization towards whatever direction a programmer 
seeks to take it. We chose to focus on building a strong framework for our code that was
easy to add new bricks power ups and levels to.