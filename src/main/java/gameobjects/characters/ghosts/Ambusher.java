package gameobjects.characters.ghosts;

import processing.core.PImage;
import gameobjects.characters.ghosts.Ghost;
import game.Game;
import java.util.HashMap;
import sprites.GhostSprite;

/**
* Ambusher Ghost.
*/
public final class Ambusher extends Ghost{

  /**
  * Creates an Ambusher Ghost object.
  *
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed of the ghost in pixels per second.
  * @param game Game object to which the ghost belongs.
  *
  * @see Game
  * @see Ghost
  */
  public Ambusher(int x, int y, int speed, Game game) {
    super(x, y, speed, game);
    ghostSprite = GhostSprite.Ambusher;
    // set top right corner as scatter target
    scatterTarget[0] = toPixelCoords(27);
    scatterTarget[1] = 0;
  }

  /**
  * Retrieves an Ambusher ghosts target coordinates. Ambusher targets four grid spaces ahead
  * of Waka (based on Waka’s current direction).
  * @return (x,y) coordinates of target grid position.
  */
  public int[] getTarget() {
    int[] wakaLocation = game.waka.getLocation();
    int wakaDirection = game.waka.getCurrentDirection();
    int[] target = wakaLocation;
    // shift the target location for grid spaces in direction of waka's movement
    for (int i = 0; i < 4; i++) {
      target = changePosition(target[0], target[1], 16, wakaDirection);
    }

    // make sure target is on map (will snap to edge of map)
    target = snapToMap(target);
    return target;
  }
}
