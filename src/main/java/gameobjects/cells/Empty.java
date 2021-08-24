package gameobjects.cells;

import sprites.CellSprite;
import processing.core.PImage;
import gameobjects.cells.Cell;
import gameobjects.characters.waka.Waka;

public final class Empty extends Cell {

  /**
  * Creates an Empty cell
  *
  * @param x Integer representing the x-grid-coordinate
  * @param y Integer representing the y-grid-coordinate
  */
  public Empty(int x, int y) {
    super(x, y);
    sprite = CellSprite.Empty.sprite;
  }

  public void step(Waka waka) {}

  public boolean canStep() {
    return true;
  }

}
