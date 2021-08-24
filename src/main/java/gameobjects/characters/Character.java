package gameobjects.characters;

import java.util.HashMap;
import movement.Movement;
import utils.CoordUtils;
import utils.Config;
import gameobjects.GameObject;
import processing.core.PImage;
import gameobjects.cells.Cell;
import game.Game;

public abstract class Character extends GameObject implements Movement, CoordUtils, Config {
  /**
  * Speed of the character in pixels per second.
  */
  public final int speed;

  /**
  * The characters current direction.
  * @see Movement
  */
  protected int currentDirection;
  /**
  * The characters next direction it will take where possible.
  * @see Movement
  */
  protected int nextDirection;
  /**
  * The characters intial position.
  */
  protected int[] intialPosition = new int[2];
  /**
  * Game object to which the character belongs.
  * @see Game
  */
  protected final Game game;


  /**
  * Creates a Character.
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed in pixels per second that a character moves.
  * @param game Game instance to which the character belongs.
  * @param xOffset Horizontal number of pixels to offset displayed image by.
  * @param yOffset Vertical number of pixels to offset displayed image by.
  */
  public Character(int x, int y, int speed, Game game, int xOffset, int yOffset) {
    super(x, y, xOffset, yOffset);
    this.speed = speed;
    this.game = game;
    this.intialPosition[0] = x*16;
    this.intialPosition[1] = y*16;
  }

  /**
  * Updates a character object each frame.
  */
  public abstract void tick();

  /**
  * Kills a character.
  */
  public abstract void kill();

  /**
  * Attempts to move in the next direction. If it can't it tries to move in the current direction.
  * If neither are possible, the object won't move.
  */
  public void tryMove() {
    if (currentDirection != nextDirection) {

      if (game.isValidMove(this, nextDirection)) {

        if (isDirection(nextDirection)) {

          currentDirection = nextDirection;
        }
      }
    }

    if (game.isValidMove(this, currentDirection)) {
      int[] newCoords = changePosition(x, y, speed, currentDirection);
      x = newCoords[0];
      y = newCoords[1];
    }
  }

  /**
  * Gets the characters speed.
  * @return speed in pixels per second.
  */
  public int getSpeed() {
    return this.speed;
  }

  /**
  * Gets the characters intial position.
  * @return (x,y) coordinate array.
  */
  public int[] getInitalPos() {
    return this.intialPosition;
  }

  /**
  * Gets a characters current direction.
  * @return A direction.
  */
  public int getCurrentDirection() {
    return this.currentDirection;
  }

  /**
  * Gets a charaters next direction.
  * @return A direction.
  */
  public int getNextDirection() {
    return this.nextDirection;
  }

  /**
  * Retrieves characters location
  *
  * @return location array consisting of the x and y coordinates, with x at
  *  index 0 and y at index 1
  */
  public int[] getLocation() {
    return pack(x, y);
  }

  /**
  * Sets a characters next direction.
  * @param code Keycode corresponding to a keyboard event.
  */
  public void setNextDirection(int code) {
    if (code >= 37 && code <= 40) {
      this.nextDirection = code;
    }
  }

  /**
  * Resets a characters location and direction to its original state.
  */
  public void reset() {
    x = intialPosition[0];
    y = intialPosition[1];
    currentDirection = 37;
    nextDirection = -1;
  }
}
