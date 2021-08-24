package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class Horizontal extends Wall {
  /**
  * Creates a horizontal wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public Horizontal(int x, int y) {
    super(x, y);
    sprite = CellSprite.Horizontal.sprite;
  }
}
