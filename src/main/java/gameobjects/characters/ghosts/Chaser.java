package gameobjects.characters.ghosts;

import processing.core.PImage;
import gameobjects.characters.ghosts.Ghost;
import game.Game;
import java.util.HashMap;
import sprites.GhostSprite;

public class Chaser extends Ghost{
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
  public Chaser(int x, int y, int speed, Game game) {
    super(x, y, speed, game);
    ghostSprite = GhostSprite.Chaser;
    // set top left corner as scatter target
    scatterTarget[0] = 0;
    scatterTarget[1] = 0;
  }

  /**
  * Retrieves an Chaser ghosts target coordinates. Chaser targets Waka's location.
  * @return (x,y) coordinates of target grid position.
  */
  public int[] getTarget() {
    int[] wakaLocation = game.waka.getLocation();
    return wakaLocation;
  }
}
