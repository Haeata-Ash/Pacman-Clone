package gameobjects.cells;

import sprites.CellSprite;
import applet.App;
import processing.core.PApplet;
import processing.core.PImage;
import gameobjects.GameObject;
import gameobjects.characters.waka.Waka;

/**
* Cell object that corresponds to a grid coordinate
*/
public abstract class Cell extends GameObject {

  /**
  * Creates a Cell object
  *
  * @param x Integer representing the x-grid-coordinate
  * @param y Integer representing the y-grid-coordinate
  */
  public Cell(int x, int y) {
    super(x, y);
  }

  /**
  * Creates a Cell object
  *
  * @param x Integer representing the x-grid-coordinate
  * @param y Integer representing the y-grid-coordinate
  * @param xOffset Integer representing the number of horizontal pixels to offset the image by
  * @param yOffset Integer representing the number of vertical pixels to offset the image by
  */
  public Cell(int x, int y, int xOffset, int yOffset) {
    super(x, y, xOffset, yOffset);
  }

  /**
  * Decides whether or not a Character may move onto the Cell
  *
  * @return True if it may be moved onto, False otherwise
  * @see Character
  */
  public abstract boolean canStep();

  /**
  * Updates and acts on the Waka object when the cell is stepped on, depending on the type of Cell
  *
  * @param waka Waka object (the player)
  * @see Waka
  */
  public abstract void step(Waka waka);
}
