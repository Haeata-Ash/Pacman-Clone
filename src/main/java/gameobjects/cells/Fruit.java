package gameobjects.cells;

import sprites.CellSprite;
import processing.core.PImage;
import processing.core.PApplet;
import gameobjects.cells.Cell;
import gameobjects.characters.waka.Waka;

public class Fruit extends Cell {

  /**
  * Number of fruit cells created.
  */
  protected static int totalFruits;

  /**
  * True if the Cell has fruit, false if Waka has already eaten it.
  */
  protected boolean hasFruit = true;

  /**
  * Creates a Fruit cell object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  * @param xOffset Integer representing the horizontal pixels to offset the rendered image by.
  * @param yOffset Integer representing the vertical pixels to offset the rendered image by.
  */
  public Fruit(int x, int y, int xOffset, int yOffset) {
    super(x, y, xOffset, yOffset);
    sprite = CellSprite.Fruit.sprite;
    //increase total no. of fruits by 1.
    Fruit.totalFruits += 1;
  }

  /**
  * Creates a Fruit cell object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public Fruit(int x, int y) {
    this(x, y, 0, 0);
  }

  /**
  * Increments the number of fruit eaten by Waka if the cell has fruit.
  * @param waka Waka object.
  */
  public void step(Waka waka) {
    if (this.hasFruit) {
      waka.eatFruit();
      hasFruit = false;
      sprite = null;
    }
  }

  /**
  * Checks if the Cell still has fruit.
  * @return Whether the cell has fruit.
  */
  public boolean hasFruit() {
    return hasFruit;
  }

  public boolean canStep() {
    return true;
  }

  /**
  * Getter method for the nuber of total Fruits attribute
  * @return Number of fruits
  */
  public static int getTotalFruits() {
    return Fruit.totalFruits;
  }

  /**
  * Sets the number of fruits created to 0.
  */
  public static void resetTotalFruits() {
    Fruit.totalFruits = 0;
  }
}
