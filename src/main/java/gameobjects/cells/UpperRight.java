package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class UpperRight extends Wall {

  /**
  * Creates a upper right corner wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public UpperRight(int x, int y) {
    super(x, y);
    sprite = CellSprite.RightUpperCorner.sprite;
  }
}
