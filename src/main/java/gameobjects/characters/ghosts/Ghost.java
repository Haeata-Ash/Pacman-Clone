package gameobjects.characters.ghosts;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import game.Game;
import movement.Movement;
import gameobjects.characters.Character;
import movement.Movement;
import utils.CoordUtils;
import utils.Config;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import sprites.GhostSprite;
import java.util.Random;
import java.util.Arrays;


/**
* Abstract class representing all ghosts.
*/
public abstract class Ghost extends Character {
  /**
  * If set to true, debugging lines are drawn from ghost to target location.
  */
  public boolean debug = false;

  /**
  * Ghosts target location as (x,y) coordinates.
  */
  protected int[] targetPosition = new int[2];


  /**
  * Enum responsible for holding and updating the ghosts current sprite to be drawn onto display
  * window.
  */
  protected GhostSprite ghostSprite;


  /**
  * Whether the ghost has been eaten by waka.
  */
  protected boolean isAlive = true;
  /**
  * Whether the ghost is frightened.
  */
  protected boolean isFrightened = false;
  /**
  * Whether the ghost is int scatter or chase mode. True if scatter, false if chase.
  */
  protected boolean scatterChaseToggle = true; //true is scatter, false is chaser


  /**
  * The length of each scatter and chase mode.
  */
  protected ArrayList<Integer> modeLengths = new ArrayList<Integer>();
  /**
  * The length of the frightened mode.
  */
  protected int frightenedLength;
  /**
  * Counter for time spent in frightened mode.
  */
  protected int frightenedIter = 0;
  /**
  * Counter for time spent in scatter or chase mode.
  */
  protected int scatterChaseIter = 0;
  /**
  * Cursor for current mode length.
  * @see modeLengths
  */
  protected int scatterChaseIndex = 0;

  /**
  * Whether the ghost is invisible
  */
  protected boolean isInvis = false;

  /**
  * Coordinates of the corner a ghost targets when in scatter mode.
  */
  protected int[] scatterTarget = new int[2];


  /**
  * Available valid directions the ghost can move in.
  */
  protected ArrayList<Integer> possibleDirections = new ArrayList<Integer>();

  /**
  * Creates ghost object.
  *
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed of the ghost in pixels per second.
  * @param game Game object to which the ghost belongs.
  *
  * @see Game
  */
  public Ghost(int x, int y, int speed, Game game) {
    super(x, y, speed, game, -5, -5);
    this.currentDirection = 37;
    if (game != null) {
      this.modeLengths = confToIntegerArrList(game.getConfig(), "modeLengths");
      this.frightenedLength = confToInteger(game.getConfig(), "frightenedLength") * 60;
    }
  }

  /**
  * Checks if the ghost is alive.
  * @return whether the ghost is alive.
  */
  public boolean isAlive() {
    return this.isAlive;
  }

  /**
  * Kills the ghost.
  */
  public void kill() {
    isAlive = false;
  }

  /**
  * Method defined in child ghost types that retrieves the current target location
  * when in chase mode.
  * @return a location array (x,y).
  */
  public abstract int[] getTarget();

  /**
  * Sets the current mode from scatter, chase or frightened.
  */
  public void setCurrentMode() {

    // is invisible
    if (isInvis) {
      // reached frightened length so turn off frightened and invis
      if (frightenedIter == frightenedLength) {
        isFrightened = false;
        isInvis = false;
        frightenedIter = 0;

      // otherwise stay frightened and invis
      } else {
        frightenedIter += 1;
      }
    }

    // frightened but not invis
    else if (isFrightened && !isInvis) {
      // reached frightened length so turn off frightened
      if (frightenedIter == frightenedLength) {
        isFrightened = false;
        frightenedIter = 0;

      // otherwise stay frightened
      } else {
        frightenedIter += 1;
      }

    // in a normal mode
    } else {
      // reached the end of a mode so switch
      if (scatterChaseIter == modeLengths.get(scatterChaseIndex)) {
        scatterChaseToggle = !scatterChaseToggle;
        scatterChaseIter = 0;
        scatterChaseIndex += 1;

        // increment mode index
        if (scatterChaseIndex >= modeLengths.size() - 1) {
          scatterChaseIndex = 0;
        }

      // otherwise stay in mode
      } else {
        scatterChaseIter += 1;
      }
    }
  }

  /**
  * Sets ghost to frightened mode.
  */
  public void frightenGhost() {
    isFrightened = true;
    frightenedIter = 0;
  }

  /**
  * Checks if ghost is frightened.
  * @return Whether the ghost is frightened.
  */
  public boolean isFrightened() {
    return this.isFrightened;
  }

  /**
  * Updates the ghost object every frame
  */
  public void tick() {
    // set the current mode and update the sprite
    this.setCurrentMode();
    sprite = ghostSprite.update(isFrightened, isAlive, isInvis);

    // set the target position if it isn't frightened
    if (!isFrightened) {
      //scatter
      if (scatterChaseToggle) {
        targetPosition = scatterTarget;

      //chaser
      } else {
        targetPosition = getTarget();
      }
    }

    // find shortest path or pick random direction if frightened
    possibleDirections = possibleDirections();
    nextDirection = nextDirection(possibleDirections, isFrightened);
    this.tryMove();
  }

  /**
  * Finds all possible directions a ghost can move.
  *
  * @return List of possible directions.
  * @see Movement
  */
  public ArrayList<Integer> possibleDirections() {
    ArrayList<Integer> possibleDirections = new ArrayList<Integer>();

    //get reverse direction
    int reverseDirection = getReverseDirection(this.getCurrentDirection());

    for (int direction: Movement.DIRECTIONS) {
      // check if ghost can move in any direction but reverse and add direction to list
      if (direction != reverseDirection) {
        if (game.isValidMove(this, direction)) {
          possibleDirections.add(direction);
        }
      }
    }

    // if no directions in list, add reverse direction
    if (possibleDirections.size() == 0) {
      possibleDirections.add(reverseDirection);
    }
    return possibleDirections;
  }

  /**
  * Chooses the next direction a ghost will move based on the shortest path in Euclidean distance
  * if a ghost is in chase or scatter mode, or a random direction if ghost is in frightened mode.
  * @param directions List of possible directions a ghost can move.
  * @param isFrightened Whether the ghost is frightened.
  * @return Next Direction
  * @see Movement
  */
  public int nextDirection(ArrayList<Integer> directions, boolean isFrightened) {
    if (directions == null) {
      return -1;
    }
    // if it is not a gridspace then the ghost can't turn.
    if (!isGridSpace(x, y)) {
      return -1;
    }

    // get random direction if frightened
    if (isFrightened) {
      Random rand = new Random();
      //selects randomly from possible directions
      int randomDir = directions.get(rand.ints(0, directions.size()).findFirst().getAsInt());
      // set target position to new pos after random
      targetPosition = changePosition(x, y, 16, randomDir);
      return randomDir;
    }

    // select arbitrary direction in list and calculate distance to target from new position
    int nextDir = directions.get(0);
    int[] startCoords = changePosition(x, y, 16, nextDir);
    double shortestPath = getDistance(startCoords, targetPosition);

    int dir;
    double path;

    // iterate through directions and check if distance is shorter
    for (int i = 1; i < directions.size(); i++) {
      dir = directions.get(i);
      startCoords = changePosition(x, y, 16, dir);
      path = getDistance(startCoords, targetPosition);

      //compare to previous shortest path
      if (path < shortestPath) {
        shortestPath = path;
        nextDir = dir;
      }
    }
    // return direction with shortest path.
    return nextDir;
  }

  /**
  * Draws ghost sprite and debugging line to screen.
  * @param app Display window instance.
  */
  public void draw(PApplet app) {
    this.tick();
    if (sprite != null) {
      app.image(sprite, x + xOffset, y + yOffset);
    }

    if (this.debug && isAlive) {
      app.line(x, y, this.targetPosition[0], this.targetPosition[1]);
    }
  }

  /**
  * Resets all ghost timers and location.
  */
  public void reset() {
    super.reset();
    scatterChaseToggle = true;
    scatterChaseIter = 0;
    scatterChaseIndex = 0;
    frightenedIter = 0;
    isFrightened = false;
    isInvis = false;
  }

  /**
  * Turns ghosts invisible and frights them.
  */
  public void invis() {
    isInvis = true;
    frightenGhost();
  }

  /**
  * Checks if a ghost is invisible.
  * @return Whether it is invisible.
  */
  public boolean isInvis() {
    return this.isInvis;
  }
}
