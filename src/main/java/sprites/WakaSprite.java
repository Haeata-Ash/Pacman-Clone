package sprites;

import processing.core.PImage;

/**
* Containers for sprites used by Waka objects.
*/
public enum WakaSprite {
  Up(38),
  Closed(-1),
  Down(40),
  Right(39),
  Left(37);

  /**
  * Pairs a WakaSprite with a direction or -1 for closed.
  */
  WakaSprite(int spriteDirection) {
    this.spriteDirection = spriteDirection;
  }

  /**
  * The sprite.
  * @see PImage
  */
  public PImage sprite;
  private int spriteDirection;
  private int closedCount = 0;

  /**
  * Returns the corresponding WakaSprite based on a direction while alternating to and from a Closed
  * sprite every 8 calls to the method.
  * @param dir A direction.
  * @return The corresponding WakaSprite.
  */
  public WakaSprite update(int dir) {
    if (this==Closed && closedCount == 8) {
      closedCount = 0;
      return getDirectionalSprite(dir);

    } else if (this != Closed && closedCount == 8) {
      closedCount = 0;
      return Closed;

    } else if (this == Closed && closedCount < 8) {
      closedCount += 1;
      return Closed;

    } else {
      closedCount += 1;
      return getDirectionalSprite(dir);
    }
  }

  /**
  * Retrieves a WakaSprite corresponding to a direction.
  * @param dir A direction.
  * @return A WakaSprite.
  */
  public WakaSprite getDirectionalSprite(int dir) {
    switch(dir) {
      case 38:
        return Up;
      case 40:
        return Down;
      case 39:
        return Right;
      case 37:
        return Left;
      default:
        return null;
    }
  }


}
