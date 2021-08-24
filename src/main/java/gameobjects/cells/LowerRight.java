package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class LowerRight extends Wall {

  /**
  * Creates a lower right corner wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public LowerRight(int x, int y) {
    super(x, y);
    sprite = CellSprite.RightLowerCorner.sprite;
  }

}
