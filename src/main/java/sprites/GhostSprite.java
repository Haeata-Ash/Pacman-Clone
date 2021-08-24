package sprites;

import processing.core.PImage;

/**
* Containers for sprites used by Ghost objects.
*/
public enum GhostSprite {
  Whim,
  Chaser,
  Ignorant,
  Ambusher,
  Frightened;

  /**
  * The sprite.
  * @see PImage
  */
  public PImage sprite;

  /**
  * Returns a sprite corresponding to the ghosts mode and whether it is alive.
  * @param isFrightened Whether the ghost is frightened.
  * @param isAlive Whether the ghost is alive.
  * @param isInvis Whether the ghost is invisible.
  * @return A sprite or null
  * @see PImage
  */
  public PImage update(boolean isFrightened, boolean isAlive, boolean isInvis) {
    if (!isAlive || isInvis) {
      return null;
    }

    if (isFrightened && !isInvis) {
      return Frightened.sprite;
    } else {
      return this.sprite;
    }
  }

}
