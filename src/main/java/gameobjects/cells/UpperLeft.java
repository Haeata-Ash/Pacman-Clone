package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class UpperLeft extends Wall {

  /**
  * Creates a upper left corner wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public UpperLeft(int x, int y) {
    super(x, y);
    sprite = CellSprite.LeftUpperCorner.sprite;
  }
}
