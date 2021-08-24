package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class LowerLeft extends Wall {

  /**
  * Creates a lower left corner wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public LowerLeft(int x, int y) {
    super(x, y);
    sprite = CellSprite.LeftLowerCorner.sprite;
  }
}
