package gameobjects.cells;

import sprites.CellSprite;
import processing.core.PImage;
import processing.core.PApplet;
import gameobjects.cells.Cell;
import gameobjects.characters.waka.Waka;

public class Soda extends Cell {


  /**
  * True if the Cell has soda, false if Waka has already drunken it.
  */
  private boolean hasSoda = true;

  /**
  * Creates a Soda cell object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  * @param xOffset Integer representing the horizontal pixels to offset the rendered image by.
  * @param yOffset Integer representing the vertical pixels to offset the rendered image by.
  */
  public Soda(int x, int y, int xOffset, int yOffset) {
    super(x, y, xOffset, yOffset);
    sprite = CellSprite.Soda.sprite;
  }

  /**
  * Creates a Soda cell object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public Soda(int x, int y) {
    this(x, y, 0, 0);
  }

  /**
  * Makes ghost invisible if not stepped on before, otherwise does nothing.
  * @param waka Waka object.
  */
  public void step(Waka waka) {
    if (this.hasSoda) {
      waka.drinkSoda();
      hasSoda = false;
      sprite = null;
    }
  }

  public boolean canStep() {
    return true;
  }
}
