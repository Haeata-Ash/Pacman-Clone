package gameobjects;

import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math;

/**
* Object in the Waka game.
*/
public abstract class GameObject {

  /**
  * The sprite to be drawn onto the display window.
  * @see PImage
  */
  protected PImage sprite;

  /**
  * Horizontal pixel coordinate.
  */
  protected int x;
  /**
  * Vertical pixel coordinate.
  */
  protected int y;
  /**
  * Number of horizontal pixels to offset the drawn image by.
  */
  protected int xOffset;
  /**
  * Number of vertical pixels to offset the drawn image by.
  */
  protected int yOffset;

  /**
  * Creates a GameObject instance.
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  */
  public GameObject(int x, int y) {
    this.x = x*16;
    this.y = y*16;
  }

  /**
  * Creates a GameObject instance.
  * @param x Horizontal grid coordinate.
  * @param y Vertical grid coordinate.
  * @param xOffset Number of horizontal pixels to offset the drawn image by.
  * @param yOffset Number of vertical pixels to offset the drawn image by.
  */
  public GameObject(int x, int y, int xOffset, int yOffset) {
    this(x, y);
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }

  /**
  * Gets the horizontal pixel coordinate.
  * @return x-coordinate.
  */
  public int getX() {
    return this.x;
  }

  /**
  * Gets the vertical pixel coordinate.
  * @return y-coordinate.
  */
  public int getY() {
    return this.y;
  }

  /**
  * Draws a game objecyt to the display window.
  * @param app display window instance.
  */
  public void draw(PApplet app) {
    if (sprite != null) {
      app.image(sprite, x + xOffset, y + yOffset);
    }
  }
}
