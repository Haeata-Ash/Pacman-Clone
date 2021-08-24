package gameobjects.cells;

import sprites.CellSprite;
import processing.core.PImage;
import gameobjects.cells.Cell;
import gameobjects.characters.waka.Waka;

public abstract class Wall extends Cell {
  /**
  * Creates a horizontal wall object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public Wall(int x, int y) {
    super(x, y);
  }

  public boolean canStep() {
    return false;
  }

  public void step(Waka waka) {}

}
