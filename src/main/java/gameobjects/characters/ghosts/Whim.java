package gameobjects.characters.ghosts;

import processing.core.PImage;
import gameobjects.characters.ghosts.Ghost;
import game.Game;
import java.util.HashMap;
import sprites.GhostSprite;

public final class Whim extends Chaser {

  /**
  * Creates an Whim Ghost object.
  *
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed of the ghost in pixels per second.
  * @param game Game object to which the ghost belongs.
  *
  * @see Game
  * @see Ghost
  */
  public Whim(int x, int y, int speed, Game game) {
    super(x, y, speed, game);
    ghostSprite = GhostSprite.Whim;
    // set bottom left corner as scatter target
    scatterTarget[0] = toPixelCoords(27);
    scatterTarget[1] = toPixelCoords(35);
  }

  /**
  * Retrieves an Whim ghosts target coordinates. Whim targets the location which is double
  * the vector from Chaser to 2 grid spaces ahead of Waka. If no Chaser was ever initialized in
  * the game, whim targets Waka's location.
  * @return (x,y) coordinates of target grid position.
  */
  public int[] getTarget() {
      int[] wakaLocation = game.waka.getLocation();
      int wakaDirection = game.waka.getCurrentDirection();

      // get grid position two cells ahead of waka
      int[] twoAheadWaka = changePosition(wakaLocation[0], wakaLocation[1], 2*16, wakaDirection);
      int[] arbitraryChaserLocation = game.getAChaserLocation();
      // if a chaser has not existed, target waka
      if (arbitraryChaserLocation == null) {
        return wakaLocation;
      }

      // get vector length
      int xDiff = arbitraryChaserLocation[0] - twoAheadWaka[0];
      int yDiff = arbitraryChaserLocation[1] - twoAheadWaka[1];
      // double vector and get new target
      int[] target = {twoAheadWaka[0] - (2*xDiff), twoAheadWaka[1] - (2*yDiff)};
      return snapToMap(target);
  }
}
