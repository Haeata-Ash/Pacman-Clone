package movement;

import game.Game;
import gameobjects.cells.Cell;
import gameobjects.characters.Character;
import game.Game;

/**
* This interface should be used to give functionality pertaining to the movement of a Character
* object.
*/
public interface Movement {

  /**
  * List of valid directions, where 37 corresponds to left, 38 to up, 39 to right and 40 to down.
  */
  public static final int[] DIRECTIONS = {
    37, // left
    38, // up
    39, // right
    40  // down
  };

  /**
  * Given a pair of coordinates representing a position, the function produces a new set of
  * coordinates given a direction and the size of the change.
  * @param xCoord A x-coordinate.
  * @param yCoord A y-coordinate.
  * @param increment The size the increase in a given direction.
  * @param direction The direction to apply the increment.
  * @return A (x,y) coordinate array representing the changed position.
  */
  default int[] changePosition(int xCoord, int yCoord, int increment, int direction) {
    int[] coords = new int[2];
    switch(direction) {
      case 37:
        coords[0] = xCoord - increment;
        coords[1] = yCoord;
        return coords;
      case 38:
        coords[1] = yCoord - increment;
        coords[0] = xCoord;
        return coords;
      case 39:
        coords[0] = xCoord + increment;
        coords[1] = yCoord;
        return coords;
      case 40:
        coords[1] = yCoord + increment;
        coords[0] = xCoord;
        return coords;
      default:
        return coords;
      }
  }

  /**
  * Ensures a set of coordinates is within the bounds of the game grid (not the pixel grid).
  * @param position A (x,y) coordinate array.
  * @return An (x,y) coordinate array that is within the bounds of the game grid.
  */
  default int[] snapToMap(int[] position) {
    if (position == null) {
      return null;
    }
    if (position[0] < 0) {
      position[0] = 0;
    } else if (position[0] > 432) {
      position[0] = 432;
    } else {}

    if (position[1] < 0) {
      position[1] = 0;
    } else if (position[1] > 560) {
      position[1] = 560;
    } else {}

    return position;
  }

  /**
  * Finds the reverse direction of a given direction. e.g. Left is the reverse of Right.
  * @param direction A direction as defined in the DIRECTIONS array.
  * @return The reverse direction.
  * @see DIRECTIONS
  */
  default int getReverseDirection(int direction) {
    int reverse = direction + 2;
    if (reverse > 40) {
      reverse -= 4;
    }
    return reverse;
  }

  /**
  * Checks if an integer corresponds to a valid direction.
  * @param dir A potential direction.
  * @return Whether it is a direction.
  */
  default boolean isDirection(int dir) {
    if (dir >= 37 && dir <= 40) {
      return true;
    }
    return false;
  }

  /**
  * Tries to move the object that implements the method.
  */
  public void tryMove();
}
