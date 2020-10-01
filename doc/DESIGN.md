# Simulation Design Final
### Names

## Team Roles and Responsibilities

 * Joseph Heflin
 - Game flow
 - Cut screen features
 - Cheat Keys
 - Level Formatting

 * Team Member #2


## Design goals

#### What Features are Easy to Add
Level Formats, Buttons, Brick types, and PowerUp types are easy features to add.

## High-level Design

#### Core Classes
- Game
- LevelBuilder
- Detector
- Ball
- Brick
- Button

## Assumptions that Affect the Design

#### Features Affected by Assumptions
It is assumed that the formats of the level files are all valid.

## New Features HowTo

#### Easy to Add Features
- Level Formats
1. Copy the file levelFormats/template.txt and replace a zero in the location you'd like to place a brick with one of the following characters:

|Character|Description|
|:-------:|:---------|
|0        |Empty space|
|1        |Single hit to destroy|
|2        |Two hits to destroy|
|3        |Bomb brick|

2. Add the filename to the LEVELS list in the constants in the Game class.
NOTE: make sure to maintain single space gaps between characters.

- Buttons
1. Create a new class who's name indicateds the function of the button.
2. Make the class extend the Button superclass.
3. Call super in the constructor, entering the text you want displayed on the button as the first argument.
4. Override the reactToClick method.
5. Declare a new instance of the class somewhere in the project, passing it the game object and the FlowPane it's added to.

- Brick Types
1. 
2. 
- Powerup Types
1.
2.


#### Other Features not yet Done

