package game;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

import applet.App;
import gameobjects.cells.Cell;
import gameobjects.cells.Empty;
import gameobjects.cells.Horizontal;
import gameobjects.cells.Vertical;
import gameobjects.cells.UpperRight;
import gameobjects.cells.UpperLeft;
import gameobjects.cells.LowerRight;
import gameobjects.cells.LowerLeft;
import gameobjects.cells.Fruit;
import gameobjects.cells.SuperFruit;
import gameobjects.cells.Soda;
import gameobjects.characters.Character;
import gameobjects.characters.waka.Waka;
import gameobjects.characters.ghosts.Ghost;
import gameobjects.characters.ghosts.Ambusher;
import gameobjects.characters.ghosts.Whim;
import gameobjects.characters.ghosts.Ignorant;
import gameobjects.characters.ghosts.Chaser;
import utils.Config;
import utils.CoordUtils;

/**
* Object that holds the configuration and game state information,
* including all Character objects, and the Cell grid.
*/
public class Game implements Config, CoordUtils {
  private JSONObject config;

  private boolean gameStatus = true;

  /**
  * game message displayed after a win or loss
  */
  public String gameMessage;

  /**
  * Player object
  * @see Waka
  */
  public Waka waka;

  /**
  * List of all Antagonist characters in the game
  * @see Ghost
  */
  public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

  /**
  * An arbitrary chaser ghost to be used with Whim ghosts for targetting
  */
  public Chaser aChaser = null; // an arbitrary chaser

  private Cell[][] grid;

  private int gameOverIter = 0;

  /**
  * Returns a game object that holds all game state information including
  * character objects and a Cell grid.
  *
  * @param confFile Path to a valid config file
  */
  public Game(String confFile) {
    if (confFile == null) {
      confFile = "config.json";
    }
    this.config = parseConfig(confFile);
    this.grid = createGrid(confToString(config, "map"));
  }

  /**
  * Returns a game object that holds all game state information including
  * character objects and a Cell grid.
  *
  */
  public Game() {
    this("config.json");
  }

  /**
  * Tick method used to update the game object every frame.
  */
  public void tick() {
    // check if the game has been win or lost
    if (!gameStatus) {
      // check if the game must be restarted (10 sec)
      if (gameOverIter == 600) {
        // recreate grid
        waka = null;
        ghosts.clear();
        grid = createGrid(confToString(config, "map"));
        Fruit.resetTotalFruits();

        //reset game attributes
        gameStatus = true;
        gameMessage = null;
        gameOverIter = 0;

      } else {
        // keep waiting
        gameOverIter += 1;
      }
    }

    // check if player has lost
    if (!this.waka.hasLives()) {
      this.gameStatus = false;
      this.gameMessage = "GAME OVER";
    }

    // check for collision
    this.checkCharacterCollision(waka, ghosts);
  }

  /**
  * Draws all game sprites onto display window.
  *
  * @param app Display window instance.
  * @see PApplet
  */
  public void draw(PApplet app) {
    this.tick();

    // draw cells
    if (this.gameStatus) {
      for (int y = 0; y < 36; y++) {
        for (int x = 0; x < 28; x++) {
          grid[y][x].draw(app);
        }
      }

      //draw characters
      this.waka.draw(app);
      for (Ghost g: this.ghosts) {
        g.draw(app);
      }

    //draw message
    } else {
      app.text(this.gameMessage, 180, 288);
    }

  }

  /**
  * Takes a keycode corresponding to a keyboard event (key pressed) and
  * updates relevant objects. If the spacebar is pressed debugging lines are
  * created. If the arrow keys are pressed, waka's next direction is updated.
  *
  * @param keyCode integer value corresponding to a key event
  */
  public void queueMove(int keyCode) {
    // spacebar was pressed
    if (keyCode == 32) {
      for (Ghost g: this.ghosts) {
        g.debug = !g.debug;
      }
    //anything else was pressed
    } else {
      this.waka.setNextDirection(keyCode);
    }

  }

  /**
  * Retrieves an arbitrary Chaser ghosts location if one exists.
  *
  * @return array of an x and y coordinate, or null if no Chaser exists.
  */
  public int[] getAChaserLocation() {
    if (aChaser == null) {
      return null;
    }

    return pack(aChaser.getX(), aChaser.getY());
  }

  /**
  * Checks if a characters move in a direction is valid.
  * @param character A character object.
  * @param direction The direction of the move.
  * @return Whether or not the move is valid.
  * @see Character
  */
  public boolean isValidMove(Character character, int direction) {
    //get characters current direction and location
    int currentDirection = character.getCurrentDirection();
    int xCoord = character.getX();
    int yCoord = character.getY();

    // checks if either coordinate lies on the grid (i.e not in between cells)
    boolean xCoordIsOnGrid = ((xCoord % 16) == 0);
    boolean yCoordIsOnGrid = ((yCoord % 16) == 0);
    int xDiff = 0;
    int yDiff = 0;

    // if not on vertical grid space then can't travel left or right
    if (!yCoordIsOnGrid & (direction == 37 || direction == 39)) {
      return false;
    }

    //if not on horizontal grid space then can't travel up or down
    if (!xCoordIsOnGrid & (direction == 38 || direction == 40)) {
      return false;
    }

    // if not on horizontal grid coordinate and it is already travelling horizontalally,
    // it can keep going
    if (!xCoordIsOnGrid && (direction == 37 || direction == 39) && direction == currentDirection) {
      return true;
    }

    // if not on vertical grid coordinate and it is already travelling vertically,
    // it can keep going
    if (!yCoordIsOnGrid && (direction == 38 || direction == 40) && direction == currentDirection) {
      return true;
    }

    // get neighbouring grid coordinates of cell in specified direction
    if (direction == 37) {
      xDiff = -1;
    }

    if (direction == 39) {
      xDiff = 1;
    }

    if (direction == 38) {
      yDiff = -1;
    }

    if (direction == 40) {
      yDiff = 1;
    }

    int xGrid = xCoord/16 + xDiff;
    int yGrid = yCoord/16 + yDiff;

    //check if they are in the grid bounds
    if ((xGrid >= 28 || xGrid < 0) || (yGrid >= 36 || yGrid < 0)) {
      return false;
    }
    // get the cell at the future grid position and see if it is stepable.
    Cell futureCell = getCell(xGrid, yGrid);
    return futureCell.canStep();
  }


  /**
  * Retrieves Cell at a grid location
  *
  * @param x integer representing an x-axis/horizontal grid coordinate
  * @param y integer representing an y-axis/vertical grid coordinate
  * @return Cell object residing at location (x,y)
  * @see Cell
  */
  public Cell getCell(int x, int y) {
    return this.grid[y][x];
  }

  /**
  * Checks if waka has collided with a ghost. If the ghost is frightened, the
  * the ghost dies. Else, waka loses a life.
  * @param waka The player.
  * @param ghosts List of ghosts to check against.
  * @see Waka
  * @see Ghost
  * @see Cell
  */
  public void checkCharacterCollision(Waka waka, ArrayList<Ghost> ghosts) {
    // get waka's position
    int[] wakaPos = pack(waka.getX(), waka.getY());
    double distance;
    int[] ghostPos;

    for (Ghost ghost : ghosts) {
      //ghost location
      ghostPos = pack(ghost.getX(), ghost.getY());
      // get istance between waka and ghost
      distance = getDistance(wakaPos, ghostPos);

      // if the distance is smaller than 5 they have collided
      if (distance < 5 && ghost.isAlive()) {
        if (ghost.isFrightened()) {
          ghost.kill();

        } else {
          waka.kill();
          ghost.reset();
        }
      }
    }
  }

  /**
  * Frightens all ghosts. While frightened, ghosts can be killed by waka.
  *
  * @see Ghost
  */
  public void frightenGhosts() {
    for (Ghost ghost : ghosts) {
      ghost.frightenGhost();
    }
  }

  /**
  * Game ends. Sets winning message
  */
  public void win() {
    this.gameStatus = false;
    this.gameMessage = "YOU WIN";
  }

  /**
  * Resets all ghosts to their original states
  *
  * @see Ghost
  */
  public void resetGhosts() {
    for (Ghost ghost : ghosts) {
      ghost.reset();
    }
  }

  /**
  * Gets the config
  *
  * @return json object representing configuration
  */
  public JSONObject getConfig() {
    return this.config;
  }

  /**
  * Turns a string character into its corresponding Cell object or Character.
  *
  * @param cellString A string character representing a Cell object
  * @param x Int representing an x-grid-coordinate
  * @param y Int representing an y-grid-coordinate
  *
  * @return Cell object
  *
  * @see Cell
  * @see Character
  */
  public Cell strToCell(String cellString, int x, int y) {
    if (cellString == null) {
      return null;
    }

    //get speed from config
    int speed = confToInteger(config, "speed");

    //match string to appropriate Cell/Character
    if (cellString.equals("0")) {
      return new Empty(x, y);

    } else if (cellString.equals("1")) {
      return new Horizontal(x, y);

    } else if (cellString.equals("2")) {
      return new Vertical(x, y);

    } else if (cellString.equals("3")) {
      return new UpperLeft(x, y);

    } else if (cellString.equals("4")) {
      return new UpperRight(x, y);

    } else if (cellString.equals("5")) {
      return new LowerLeft(x, y);

    } else if (cellString.equals("6")) {
      return new LowerRight(x, y);

    } else if (cellString.equals("7")) {
      return new Fruit(x, y);

    } else if (cellString.equals("8")) {
      return new SuperFruit(x, y);

    } else if (cellString.equals("s")) {
      return new Soda(x, y);

    } else if (cellString.equals("p")) {
      waka = new Waka(x, y, speed, confToInteger(config, "lives"), this);
      return new Empty(x, y);

    } else if (cellString.equals("a")) {
      ghosts.add(new Ambusher(x, y, speed, this));
      return new Empty(x, y);

    } else if (cellString.equals("i")) {
      ghosts.add(new Ignorant(x, y, speed, this));
      return new Empty(x, y);

    } else if (cellString.equals("c")) {
      aChaser = new Chaser(x, y, speed, this);
      ghosts.add(aChaser);
      return new Empty(x, y);

    } else if (cellString.equals("w")) {
      ghosts.add(new Whim(x, y, speed, this));
      return new Empty(x, y);

    } else {
      return null;
    }
  }

  /**
  * Reads a file and creates a 2D array of Cell objects representing the game grid
  *
  * @param filename A relative path to a map file
  *
  * @return A 2D Cell object array
  *
  * @see Cell
  */
  public Cell[][] createGrid(String filename) {
    if (filename == null) {
      return null;
    }

    File map = new File(filename);
    Cell[][] cellMap = new Cell[36][28];

    try {
      //create scanner for map file
      Scanner scan = new Scanner(map);

      int y = 0;
      String ln;
      String[] cellString;

      // iterate through files lines
      while (scan.hasNextLine()) {
        ln = scan.nextLine();

        //split line into array of string characters
        cellString = ln.split("");
        for (int x = 0; x < cellString.length; x++) {
          // retrieve corresponding Cell and add it to cellMap at matching y,x index
          cellMap[y][x] = this.strToCell(cellString[x], x, y);
        }

        y++;
      }
    } catch (FileNotFoundException e) {
      // no map file foudn
      System.out.println("File not found");
      System.exit(1);
    }
    return cellMap;
  }

}
