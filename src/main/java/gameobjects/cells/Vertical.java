package gameobjects.cells;

import gameobjects.cells.Wall;
import sprites.CellSprite;

public final class Vertical extends Wall {

  /**
  * Creates a vertical wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public Vertical(int x, int y) {
    super(x, y);
    sprite = CellSprite.Vertical.sprite;
  }
}
