package gameobjects.characters.ghosts;

import processing.core.PImage;
import gameobjects.characters.ghosts.Ghost;
import game.Game;
import java.util.HashMap;
import sprites.GhostSprite;

public final class Ignorant extends Ghost{

  /**
  * Creates an Ignorant Ghost object.
  *
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param speed Speed of the ghost in pixels per second.
  * @param game Game object to which the ghost belongs.
  *
  * @see Game
  * @see Ghost
  */
  public Ignorant(int x, int y, int speed, Game game) {
    super(x, y, speed, game);
    ghostSprite = GhostSprite.Ignorant;
    // set top bottom left as scatter target
    scatterTarget[0] = 0;
    scatterTarget[1] = toPixelCoords(35);
  }


  /**
  * Retrieves an Ignorant ghosts target coordinates. If more than 8 units away from Waka
  * (straight line distance), target location is Waka. Otherwise, target location is the
  * bottom left corner
  * @return (x,y) coordinates of target grid position.
  */
  public int[] getTarget() {
    int[] wakaLocation = game.waka.getLocation();
    // check if distance is greater than 8 grid units
    if (getDistance(pack(x, y), wakaLocation) > 8*16) {
      return wakaLocation;
    } else {
      return scatterTarget;
    }
  }

}
