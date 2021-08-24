package utils;

import java.lang.Math;

/**
* This interface gives the user functionality to manipulate (x,y) coordinates.
*/
public interface CoordUtils {

  /**
  * Takes an x and y coordinate and packs them into an array.
  * @param x X value corresponding to horizontal position.
  * @param y Y value corresponding to vertical position.
  * @return An array of form (x,y).
  */
  default int[] pack(int x, int y) {
    int[] coords = {x, y};
    return coords;
  }

  /**
  * Gets the Euclidean distance between two sets of coordinates.
  * @param startPosition The start point.
  * @param targetPosition The end point.
  * @return The distance between the two points.
  */
  default double getDistance(int[] startPosition, int[] targetPosition) {
    double a = (startPosition[0] - targetPosition[0]);
    double b = (startPosition[1] - targetPosition[1]);
    double hypotenuse = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    return hypotenuse;
  }

  /**
  * Turns Grid coordinate into a pixel coordinate.
  * @param val A coordinate.
  * @return A pixel coordinate.
  */
  default int toPixelCoords(int val) {
    return val * 16;
  }

  /**
  * Turns pixel coordinate into a grid coordinate.
  * @param val A coordinate.
  * @return A grid coordinate.
  */
  default int toGridCoords(int val) {
    return val / 16;
  }

  /**
  * Checks if a set of pixel coordinates correspond to a set of grid coordinates.
  * @param x X value corresponding to horizontal position.
  * @param y Y value corresponding to vertical position.
  * @return Whether they correspond to a grid coordinates
  */
  default boolean isGridSpace(int x, int y) {
    if (x < 0 || y < 0) {
      return false;
    }

    if (x % 16 == 0 & y % 16 == 0) {
      return true;
    }

    return false;
  }
}
