package sprites;

import processing.core.PImage;

/**
* Containers for sprites used by Cell objects.
*/
public enum CellSprite {
  Empty,
  Fruit,
  Horizontal,
  Vertical,
  LeftUpperCorner,
  LeftLowerCorner,
  RightUpperCorner,
  RightLowerCorner,
  Soda;

  /**
  * The sprite.
  * @see PImage
  */
  public PImage sprite;
}
