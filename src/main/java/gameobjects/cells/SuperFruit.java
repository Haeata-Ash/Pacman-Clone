package gameobjects.cells;

import sprites.CellSprite;
import processing.core.PImage;
import processing.core.PApplet;
import gameobjects.cells.Fruit;
import gameobjects.characters.waka.Waka;

public final class SuperFruit extends Fruit {

  private float width = 0;
  private float height = 0;

  /**
  * Creates a Super Fruit object.
  *
  * @param x Integer representing the x-grid-coordinate.
  * @param y Integer representing the y-grid-coordinate.
  */
  public SuperFruit(int x, int y) {
    super(x, y, -7, -7);
    sprite = CellSprite.Fruit.sprite;
    if (CellSprite.Fruit.sprite != null) {
      width = CellSprite.Fruit.sprite.width; //width of fruit PImage
      height = CellSprite.Fruit.sprite.height; // height of fruit PImage
    }
  }


  public void draw(PApplet app) {
    if (sprite != null) {
      // draw image at twice the size of regular fruit
      app.image(sprite, x + xOffset, y + yOffset, width*2, height*2);
    }
  }

  /**
  * Of the cell has fruit, it increments the number of fruit eaten by Waka, makes Waka invincible
  * and frightens ghosts.
  *
  * @param waka Waka object.
  */
  public void step(Waka waka) {
    if (hasFruit) {
      super.step(waka);
      // make waka invincible
      waka.superFruitMode();
    }

  }
}
